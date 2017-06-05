package github.pasiahopelto.scorelib.reader;

import java.util.List;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Election;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;

public class EntityJoiner {

	public Election populateWithIds(List<Party> parties, List<Vote> votes, List<Voting> votings) {
		generatePartyIds(parties);
		generateVotingIds(votings);
		return makeElection(parties, votes, votings);
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
