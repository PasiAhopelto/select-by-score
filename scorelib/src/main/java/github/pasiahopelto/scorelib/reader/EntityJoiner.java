package github.pasiahopelto.scorelib.reader;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Election;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;

public class EntityJoiner {

	public class IntegrityException extends Exception {
		private static final long serialVersionUID = 1L;

		public IntegrityException(String entityName, String duplicatedName) {
			super("Duplicated " + entityName + ": " + duplicatedName);
		}
	};
	
	public Election populateWithIds(List<Party> parties, List<Vote> votes, List<Voting> votings) throws IntegrityException {
		generatePartyIds(parties);
		generateVotingIds(votings);
		populateWithVotings(votes, votings);
		populateWithCandidates(votes, parties);
		return makeElection(parties, votes, votings);
	}

	private void populateWithCandidates(List<Vote> votes, List<Party> parties) throws IntegrityException {
		Map<String, Candidate> candidatesByName = Maps.newHashMap();
		for(Party party : parties) {
			for(Candidate candidate : party.getCandidates()) {
				assertNotDuplicate(candidatesByName, candidate.getName(), "candidate");
				candidatesByName.put(candidate.getName(), candidate);
			}
		}
		for(Vote vote : votes) {
			vote.setCandidate(candidatesByName.get(vote.getCandidateName()));
		}
	}

	private void populateWithVotings(List<Vote> votes, List<Voting> votings) throws IntegrityException {
		Map<String, Voting> votingsByName = Maps.newHashMap();
		for(Voting voting : votings) {
			assertNotDuplicate(votingsByName, voting.getName(), "voting");
			votingsByName.put(voting.getName(), voting);
		}
		for(Vote vote : votes) {
			vote.setVoting(votingsByName.get(vote.getVotingName()));
		}
	}

	private void assertNotDuplicate(Map<String, ?> votingsByName, String name, String entityName) throws IntegrityException {
		if(votingsByName.containsKey(name)) {
			throw new IntegrityException(entityName, "Entity " + name + " duplicated");
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
		}
	}

	private void generatePartyIds(List<Party> parties) {
		int partyId = 1;
		int candidateId = 1;
		for(Party party : parties) {
			party.setId(partyId++);
			for(Candidate candidate : party.getCandidates()) {
				candidate.setId(candidateId++);
			}
		}
	}
}
