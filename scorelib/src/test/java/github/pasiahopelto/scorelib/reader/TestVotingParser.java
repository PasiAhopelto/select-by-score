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

	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String OPTIONS = "yes,no";
	private static final List<VotingOption> VOTING_OPTIONS = Lists.newArrayList( makeOption("yes", 1), makeOption("no", 2) );
	
	private final ParsedLine nonVotingLine = new ParsedLine();
	private final ParsedLine votingLine = new ParsedLine();
	{
		nonVotingLine.setType("votes");
		votingLine.setType("voting");
		votingLine.setValues(Lists.newArrayList(NAME, DESCRIPTION, OPTIONS));
	}
	
	private VotingParser parser = new VotingParser();
	
	@Test
	public void skipsNonVotingLine() throws ParseException {
		assertNull(parser.parseEntity(nonVotingLine));
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionWhenDescriptionAndOptionsAreMissing() throws ParseException {
		votingLine.getValues().clear();
		parser.parseEntity(votingLine);
	}
	
	@Test
	public void parsesVoting() throws ParseException {
		Voting voting = parser.parseEntity(votingLine);
		assertEquals(NAME, voting.getName());
		assertEquals(DESCRIPTION, voting.getDescription());
		assertEquals(VOTING_OPTIONS, voting.getOptions());
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionWhenOptionValueIsMissing() throws ParseException {
		votingLine.getValues().remove(2);
		parser.parseEntity(votingLine);
	}

	private static VotingOption makeOption(String name, Integer position) {
		VotingOption option = new VotingOption();
		option.setName(name);
		option.setPosition(position);
		return option;
	}
}
