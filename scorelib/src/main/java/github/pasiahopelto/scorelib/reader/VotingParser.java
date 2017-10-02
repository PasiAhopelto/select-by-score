package github.pasiahopelto.scorelib.reader;

import java.util.List;

import github.pasiahopelto.scorelib.model.Voting;

public class VotingParser {

	public Voting parseEntity(ParsedLine parsedLine) throws ParseException {
		Voting result = null;
		if("voting".equals(parsedLine.getType())) {
			result = parseVoting(parsedLine);
		}
		else {
			throw new ParseException("Expected voting, not " + parsedLine.getType());
		}
		return result;
	}

	private Voting parseVoting(ParsedLine parsedLine) throws ParseException {
		List<String> values = parsedLine.getValues();
		if(values.size() != 2) {
			throw new ParseException("expected name and description, not " + values);
		}
		Voting voting = new Voting();
		voting.setName(values.get(0));
		voting.setDescription(values.get(1));
		return voting;
	}

}
