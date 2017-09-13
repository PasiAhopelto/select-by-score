package github.pasiahopelto.scorelib.reader;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import github.pasiahopelto.scorelib.model.Election;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;

public class EntityJoiner {

	public class IntegrityException extends Exception {
		private static final long serialVersionUID = 1L;

		public IntegrityException(String entityName, String message) {
			super(entityName + ": " + message);
		}
	};
	
	public Election populateWithIds(List<Party> parties, List<Vote> votes, List<Voting> votings) throws IntegrityException {
		generatePartyIds(parties);
		generateVotingIds(votings);
		populateWithVotingsAndOptions(votes, votings);
		return makeElection(parties, votes, votings);
	}

	private void populateWithVotingsAndOptions(List<Vote> votes, List<Voting> votings) throws IntegrityException {
		Map<Integer, Map<String, VotingOption>> votingOptionsByVotingIdAndOptionName = Maps.newHashMap();
		Map<String, Voting> votingsByName = Maps.newHashMap();
		for(Voting voting : votings) {
			assertNotDuplicate(votingsByName, voting.getName(), "voting");
			votingsByName.put(voting.getName(), voting);
			votingOptionsByVotingIdAndOptionName.put(voting.getId(), collectVotingOptions(voting));
		}
		for(Vote vote : votes) {
			Voting voting = getAndAssertVoting(votingsByName, vote);
			vote.setVoting(voting);
			vote.setVotingOption(getAndAssertVotingOption(votingOptionsByVotingIdAndOptionName.get(voting.getId()), vote));
		}
	}

	private VotingOption getAndAssertVotingOption(Map<String, VotingOption> map, Vote vote) throws IntegrityException {
		VotingOption votingOption = map.get(vote.getOptionName());
		if(votingOption == null) {
			throw new IntegrityException("vote", "unknown votingOptionName " + vote.getOptionName());
		}
		return votingOption;
	}

	private Map<String, VotingOption> collectVotingOptions(Voting voting) {
		Map<String, VotingOption> votingOptionsByName = Maps.newHashMap();
		for(VotingOption votingOption : voting.getOptions()) {
			votingOptionsByName.put(votingOption.getName(), votingOption);
		}
		return votingOptionsByName;
	}

	private Voting getAndAssertVoting(Map<String, Voting> votingsByName, Vote vote) throws IntegrityException {
		Voting voting = votingsByName.get(vote.getVotingName());
		if(voting == null) {
			throw new IntegrityException("vote", "unknown voting " + vote.getVotingName());
		}
		return voting;
	}

	private void assertNotDuplicate(Map<String, ?> votingsByName, String name, String entityName) throws IntegrityException {
		if(votingsByName.containsKey(name)) {
			throw new IntegrityException(entityName, name + " duplicated");
		}
	}

	private Election makeElection(List<Party> parties, List<Vote> votes, List<Voting> votings) {
		Election election = new Election();
		election.setParties(parties);
		election.setVotes(votes);
		election.setVotings(votings);
		return election;
	}

	private void generateVotingIds(List<Voting> votings) {
		int votingId = 1;
		for(Voting voting : votings) {
			voting.setId(votingId++);
			generateOptionIds(voting.getId(), voting.getOptions());
		}
	}

	private void generateOptionIds(Integer votingId, List<VotingOption> options) {
		int position = 1;
		for(VotingOption option : options) {
			option.setPosition(position++);
			option.setVotingId(votingId);
		}
	}

	private void generatePartyIds(List<Party> parties) {
		int partyId = 1;
		for(Party party : parties) {
			party.setId(partyId++);
		}
	}
}
