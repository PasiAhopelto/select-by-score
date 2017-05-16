package github.pasiahopelto.scorelib.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Party;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestPartyParser {

	private static final String CANDIDATE_NAME = "candidate name";
	private static final String CANDIDATE = "candidate";
	private static final String PARTY = "party";
	private static final String VOTE = "vote";
	private static final String PARTY_NAME = "party name";
	
	private final ParsedLine noMatch = new ParsedLine();
	private final ParsedLine partyLine = new ParsedLine();
	private final ParsedLine candidateLine = new ParsedLine();
	{
		noMatch.setType(VOTE);
		partyLine.setType(PARTY);
		partyLine.setValues(Lists.newArrayList(PARTY_NAME));
		candidateLine.setType(CANDIDATE);
		candidateLine.setValues(Lists.newArrayList(CANDIDATE_NAME));
	}

	private PartyParser parser = new PartyParser();

	@Test
	public void partyIsEmptyAfterInit() {
		assertTrue(parser.getParties().isEmpty());
	}
	
	@Test
	public void isLineVisitor() {
		assertTrue(parser instanceof LineVisitor);
	}
	
	@Test
	public void ignoresNonPartyAndNonCandidate() throws ParseException {
		parser.parseEntity(noMatch);
		assertTrue(parser.getParties().isEmpty());
	}
	
	@Test(expected=ParseException.class)
	public void throwsExceptionIfPartyWithoutName() throws ParseException {
		specifyPartyNameIsMissing();
		parser.parseEntity(partyLine);
	}

	@Test(expected=ParseException.class)
	public void throwsExceptionIfCandidateWithoutName() throws ParseException {
		specifyCandidateNameIsMissing();
		parsePartyWithCandidate();
	}

	@Test
	public void parsesParty() throws ParseException {
		parser.parseEntity(partyLine);
		List<Party> parties = parser.getParties();
		verifyFoundParty(parties);
	}

	@Test(expected=ParseException.class)
	public void throwsExceptionIfCandidateWithoutParty() throws ParseException {
		parser.parseEntity(candidateLine);
	}

	@Test
	public void addsCandidateToParty() throws ParseException {
		parsePartyWithCandidate();
		verifyPartyHasCandidate();
	}

	private void verifyPartyHasCandidate() {
		Party party = parser.getParties().get(0);
		List<Candidate> candidates = party.getCandidates();
		assertEquals(1, candidates.size());
		assertEquals(CANDIDATE_NAME, candidates.get(0).getName());
	}

	private void verifyFoundParty(List<Party> parties) {
		assertEquals(1, parties.size());
		assertEquals(PARTY_NAME, parties.get(0).getName());
	}

	private void specifyPartyNameIsMissing() {
		partyLine.getValues().clear();
	}
	
	private void specifyCandidateNameIsMissing() {
		candidateLine.getValues().clear();
	}

	private void parsePartyWithCandidate() throws ParseException {
		parser.parseEntity(partyLine);
		parser.parseEntity(candidateLine);
	}
}
