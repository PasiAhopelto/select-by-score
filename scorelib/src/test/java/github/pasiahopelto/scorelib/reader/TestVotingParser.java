package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingParser {

	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String OPTION = "option";
	
	private final ParsedLine nonVotingLine = new ParsedLine();
	private final ParsedLine votingLine = new ParsedLine();
	private final ParsedLine optionLine = new ParsedLine();
	{
		nonVotingLine.setType("party");
		votingLine.setType("voting");
		votingLine.setValues(Lists.newArrayList(NAME, DESCRIPTION));
		optionLine.setType("option");
		optionLine.setValues(Lists.newArrayList(OPTION));
	}
	
	private VotingParser parser = new VotingParser();
	
	@Test
	public void isLineVisitor() {
		assertTrue(parser instanceof LineVisitor);
	}
	
	@Test
	public void ignoresNonVotingLine() throws ParseException {
		parser.parseEntity(nonVotingLine);
		assertTrue(parser.getVotings().isEmpty());
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionWhenNameAndDescriptionAreMissing() throws ParseException {
		votingLine.getValues().clear();
		parser.parseEntity(votingLine);
		assertTrue(parser.getVotings().isEmpty());
	}
	
	@Test
	public void parsesVoting() throws ParseException {
		parser.parseEntity(votingLine);
		assertEquals(1, parser.getVotings().size());
		Voting voting = parser.getVotings().get(0);
		assertEquals(NAME, voting.getName());
		assertEquals(DESCRIPTION, voting.getDescription());
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionIfOptionWithoutVoting() throws ParseException {
		parser.parseEntity(optionLine);
	}

	@Test(expected=ParseException.class)
	public void throwsExceptionWhenOptionValueIsMissing() throws ParseException {
		optionLine.getValues().clear();
		parser.parseEntity(votingLine);
		parser.parseEntity(optionLine);
	}

	@Test
	public void addsOptionToVoting() throws ParseException {
		parser.parseEntity(votingLine);
		parser.parseEntity(optionLine);
		List<VotingOption> options = parser.getVotings().get(0).getOptions();
		assertEquals(1, options.size());
		assertEquals(OPTION, options.get(0).getName());
	}
}
