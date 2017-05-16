package github.pasiahopelto.scorelib.reader;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Party;

public class PartyParser implements LineVisitor {

	private static final String PARTY = "party";
	private static final String CANDIDATE = "candidate";

	private ArrayList<Party> parties = Lists.newArrayList();

	public List<Party> getParties() {
		return parties;
	}

	public void parseEntity(ParsedLine parsedLine) throws ParseException {
		if(PARTY.equals(parsedLine.getType())) {
			assertHasOneValue(parsedLine);
			addParty(parsedLine);
		}
		else if(CANDIDATE.equals(parsedLine.getType())) {
			assertHasOneValue(parsedLine);
			addCandidateToParty(parsedLine);
		}
	}

	private void addCandidateToParty(ParsedLine parsedLine) throws ParseException {
		if(parties.isEmpty()) {
			throw new ParseException("stray candidate without party " + parsedLine.getValues());
		}
		Party currentParty = parties.get(parties.size() - 1);
		if(currentParty.getCandidates() == null) {
			currentParty.setCandidates(new ArrayList<Candidate>());
		}
		Candidate candidate = new Candidate();
		candidate.setName(getName(parsedLine));
		currentParty.getCandidates().add(candidate);
	}

	private void addParty(ParsedLine parsedLine) {
		Party party = new Party();
		party.setName(getName(parsedLine));
		parties.add(party);
	}

	private String getName(ParsedLine parsedLine) {
		return parsedLine.getValues().get(0);
	}

	private void assertHasOneValue(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 1) {
			throw new ParseException("expected one name in a line " + parsedLine.getType() + ": " + parsedLine.getValues());
		}
	}
}
