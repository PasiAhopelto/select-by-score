package github.pasiahopelto.scorelib.reader;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Election;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityJoiner {

	private static final List<Party> PARTIES = Lists.newArrayList();
	private static final List<Vote> VOTES = Lists.newArrayList();
	private static final List<Voting> VOTINGS = Lists.newArrayList();
	private static final String VOTING_NAME = null;

	private EntityJoiner joiner = new EntityJoiner();

	@Test
	public void doesNothingIfSourcesAreEmpty() {
		joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
	}

	@Test
	public void populatesPartyWithId() {
		specifyHasParty();
		joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
		assertEquals(Integer.valueOf(1), PARTIES.get(0).getId());
	}

	@Test
	public void populatesCandidateWithId() {
		specifyHasParty();
		specifyPartyHasCandidate();
		joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
		assertEquals(Integer.valueOf(1), PARTIES.get(0).getId());
	}

	@Test
	public void populatesVotingWithId() {
		specifyHasVoting();
		joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
		assertEquals(Integer.valueOf(1), VOTINGS.get(0).getId());
	}

	@Test
	public void createsElection() {
		Election election = joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
		assertSame(PARTIES, election.getParties());
		assertSame(VOTES, election.getVotes());
		assertSame(VOTINGS, election.getVotings());
	}

	@Test
	public void setsVotingOfVote() {
		specifyHasVoting();
		specifyHasVote();
		joiner.populateWithIds(PARTIES, VOTES, VOTINGS);
		assertEquals(VOTINGS.get(0), VOTES.get(0).getVoting());
	}

	private void specifyHasVote() {
		Vote vote = new Vote();
		vote.setVotingName(VOTING_NAME);
		VOTES.add(vote);
	}

	private void specifyHasVoting() {
		Voting voting = new Voting();
		voting.setName(VOTING_NAME);
		VOTINGS.add(voting);
	}

	private void specifyPartyHasCandidate() {
		PARTIES.get(0).getCandidates().add(new Candidate());
	}

	private void specifyHasParty() {
		Party party = new Party();
		party.setCandidates(new ArrayList<Candidate>());
		PARTIES.add(party);
	}
}
