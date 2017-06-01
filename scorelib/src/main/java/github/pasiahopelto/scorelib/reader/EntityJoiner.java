package github.pasiahopelto.scorelib.reader;

import java.util.List;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;

public class EntityJoiner {

	public void populateWithIds(List<Party> parties, List<Vote> votes, List<Voting> votings) {
		addPartyIds(parties);
		addVotingIds(votings);
	}

	private void addVotingIds(List<Voting> votings) {
		int votingId = 1;
		for(Voting voting : votings) {
			voting.setId(votingId++);
		}
	}

	private void addPartyIds(List<Party> parties) {
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
