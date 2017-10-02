package github.pasiahopelto.scorelib.reader;

import github.pasiahopelto.scorelib.model.Votes;

public class VotesParser {

	public Votes parseVotes(ParsedLine votesLine) throws ParseException {
		assertHasThreeValues(votesLine);
		Votes votes = new Votes();
		votes.setParty(votesLine.getValues().get(0));
		votes.setVote(votesLine.getValues().get(1));
		votes.setVotes(Integer.valueOf(votesLine.getValues().get(2)));
		return votes;
	}

	private void assertHasThreeValues(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 3) {
			throw new ParseException("expected three values (party, vote and count) for a votes, not " + parsedLine);
		}
	}
}
