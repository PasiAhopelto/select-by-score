package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Vote;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestVoteParser {

	private final ParsedLine nonVote = new ParsedLine();
	private final ParsedLine vote = new ParsedLine();
	{
		nonVote.setType("party");
		vote.setType("vote");
		vote.setValues(Lists.newArrayList("voting", "candidate", "option"));
	}
	
	private VoteParser parser = new VoteParser();
	
	@Test
	public void votesIsEmptyAfterInit() {
		assertTrue(parser.getVotes().isEmpty());
	}

	@Test
	public void ignoresNonVoteType() throws ParseException {
		parser.parseEntity(nonVote);
		assertTrue(parser.getVotes().isEmpty());
	}

	@Test(expected=ParseException.class)
	public void throwsExceptionIfNotThreeValues() throws ParseException {
		vote.getValues().add("");
		parser.parseEntity(vote);
	}
	
	@Test
	public void addsVote() throws ParseException {
		parser.parseEntity(vote);
		List<Vote> votes = parser.getVotes();
		assertEquals(1, votes.size());
		assertEquals("voting", votes.get(0).getVotingName());
		assertEquals("candidate", votes.get(0).getCandidateName());
		assertEquals("option", votes.get(0).getOptionName());
	}
}
