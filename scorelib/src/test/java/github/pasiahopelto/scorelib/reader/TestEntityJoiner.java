package github.pasiahopelto.scorelib.reader;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Election;
import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;
import github.pasiahopelto.scorelib.reader.EntityJoiner.IntegrityException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityJoiner {

	private static final String OPTION_NAME = "option name";
	private static final String VOTING_NAME = "voting name";
	private static final String UNKNOWN_NAME = "unknown";

	private final List<Party> parties = Lists.newArrayList();
	private final List<Vote> votes = Lists.newArrayList();
	private final List<Voting> votings = Lists.newArrayList();
	private final List<VotingOption> votingOptions = Lists.newArrayList();

	private EntityJoiner joiner = new EntityJoiner();

	@Test
	public void doesNothingIfSourcesAreEmpty() throws IntegrityException {
		joiner.populateWithIds(parties, votes, votings);
	}

	@Test
	public void populatesPartyWithId() throws IntegrityException {
		specifyHasParty();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(Integer.valueOf(1), parties.get(0).getId());
	}

	@Test
	public void populatesCandidateWithId() throws IntegrityException {
		specifyHasParty();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(Integer.valueOf(1), parties.get(0).getId());
	}

	@Test
	public void populatesVotingWithId() throws IntegrityException {
		specifyHasValidEntries();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(Integer.valueOf(1), votings.get(0).getId());
	}

	@Test
	public void populatesVotingOptionWithVotingId() throws IntegrityException {
		specifyHasValidEntries();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(votings.get(0).getId(), votings.get(0).getOptions().get(0).getVotingId());
	}

	@Test
	public void populatesVotingOptionWithPositionStartingFromOne() throws IntegrityException {
		specifyHasValidEntries();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(Integer.valueOf(1), votings.get(0).getOptions().get(0).getPosition());
	}

	@Test
	public void createsElection() throws IntegrityException {
		Election election = joiner.populateWithIds(parties, votes, votings);
		assertSame(parties, election.getParties());
		assertSame(votes, election.getVotes());
		assertSame(votings, election.getVotings());
	}

	@Test
	public void setsVotingOfVote() throws IntegrityException {
		specifyHasValidEntries();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(votings.get(0), votes.get(0).getVoting());
	}

	@Test
	public void setsVotingOptionOfVote() throws IntegrityException {
		specifyHasValidEntries();
		joiner.populateWithIds(parties, votes, votings);
		assertEquals(votingOptions.get(0), votes.get(0).getVotingOption());
	}
	
	@Test(expected=IntegrityException.class)
	public void throwsExceptionIfVotingNameIsRepeated() throws IntegrityException {
		specifyHasValidEntries();
		specifyHasVoting();
		joiner.populateWithIds(parties, votes, votings);
	}
	
	@Test(expected=IntegrityException.class)
	public void throwsExceptionIfVotingNameIsUnknown() throws IntegrityException {
		specifyHasVoteWithUnknownVotingName();
		joiner.populateWithIds(parties, votes, votings);
	}
	
	private void specifyHasValidEntries() {
		specifyHasVote();
		specifyHasVoting();
	}

	private void specifyHasVoteWithUnknownVotingName() {
		specifyHasVote();
		votes.get(0).setVotingName(UNKNOWN_NAME);
	}

	private void specifyHasVote() {
		votes.add(new Vote());
	}

	private void specifyHasVoting() {
		votes.get(0).setVotingName(VOTING_NAME);
		Voting voting = new Voting();
		voting.setName(VOTING_NAME);
		votes.get(0).setOptionName(OPTION_NAME);
		VotingOption votingOption = new VotingOption();
		votingOption.setName(OPTION_NAME);
		votingOptions.add(votingOption);
		voting.setOptions(Lists.newArrayList(votingOption));
		votings.add(voting);
	}

	private void specifyHasParty() {
		Party party = new Party();
		parties.add(party);
	}
}
