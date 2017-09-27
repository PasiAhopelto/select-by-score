package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.VotingOption;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotesParser {

	private static final String VOTE_COUNT = "4";
	private static final String OPTION = "yes";
	private static final String PARTY = "party";

	private final ParsedLine nonVote = new ParsedLine();
	private final ParsedLine vote = new ParsedLine();
	private final Party party = new Party();
	private final VotingOption votingOption = new VotingOption();
	{
		nonVote.setType("voting");
		vote.setType("votes");
		vote.setValues(Lists.newArrayList(PARTY, OPTION, VOTE_COUNT));
		party.setName(PARTY);
		votingOption.setName(OPTION);
	}
	
	private VotesParser parser = new VotesParser();
	
	@Test
	public void ignoresNonVoteType() throws ParseException {
		assertNull(parser.parseEntity(nonVote));
	}

	@Test(expected=ParseException.class)
	public void throwsExceptionIfNotTwoValues() throws ParseException {
		vote.getValues().add("");
		parser.parseEntity(vote);
	}
	
	@Test
	public void parsesVotes() throws ParseException {
		Votes votes = parser.parseEntity(vote);
		assertEquals(party, votes.getParty());
		assertEquals(votingOption, votes.getVotingOption());
		assertEquals(Integer.valueOf(VOTE_COUNT), votes.getVotes());
	}
}
