package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Voting;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingParser {

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	
	private final ParsedLine nonVotingLine = new ParsedLine();
	private final ParsedLine votingLine = new ParsedLine();
	{
		nonVotingLine.setType("votes");
		votingLine.setType("voting");
		votingLine.setValues(Lists.newArrayList(NAME, DESCRIPTION));
	}
	
	private VotingParser parser = new VotingParser();
	
	@Test(expected=ParseException.class)
	public void throwsExceptionIfNonVotingLine() throws ParseException {
		assertNull(parser.parseEntity(nonVotingLine));
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionIfNotTwoValues() throws ParseException {
		votingLine.getValues().clear();
		parser.parseEntity(votingLine);
	}
	
	@Test
	public void parsesVoting() throws ParseException {
		Voting voting = parser.parseEntity(votingLine);
		assertEquals(NAME, voting.getName());
		assertEquals(DESCRIPTION, voting.getDescription());
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionWhenOptionValueIsMissing() throws ParseException {
		votingLine.getValues().remove(1);
		parser.parseEntity(votingLine);
	}

}
