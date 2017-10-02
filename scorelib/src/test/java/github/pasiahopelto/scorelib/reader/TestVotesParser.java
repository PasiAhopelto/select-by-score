package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Votes;

import static org.junit.Assert.*;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class TestVotesParser {

	private static final Integer VOTE_COUNT = 4;
	private static final String OPTION = "yes";
	private static final String PARTY = "party";

	private final Votes expectedVotes = new Votes();
	private final ParsedLine nonVote = new ParsedLine();
	private final ParsedLine vote = new ParsedLine();
	{
		nonVote.setType("voting");
		nonVote.setValues(new ArrayList<String>());
		vote.setType("votes");
		vote.setValues(Lists.newArrayList(PARTY, OPTION, VOTE_COUNT.toString()));
		expectedVotes.setParty(PARTY);
		expectedVotes.setVote(OPTION);
		expectedVotes.setVotes(VOTE_COUNT);
	}

	private VotesParser parser = new VotesParser();

	@Test(expected=ParseException.class)
	public void throwsExceptionIfNotVotesLine() throws ParseException {
		parser.parseVotes(nonVote);
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionIfNotThreeValues() throws ParseException {
		vote.getValues().add("");
		parser.parseVotes(nonVote);
	}
	
	@Test
	public void extractsParty() throws ParseException {
		assertEquals(expectedVotes, parser.parseVotes(vote));
	}
}
