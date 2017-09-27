package github.pasiahopelto.scorelib.reader;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Votes;

public class VotesParser {

	private static final String TYPE = "vote";
	private ArrayList<Votes> votes = Lists.newArrayList();

	public void parseEntity(ParsedLine parsedLine) throws ParseException {
		if(TYPE.equals(parsedLine.getType())) {
			assertHasThreeValues(parsedLine);
			votes.add(makeVote(parsedLine));
		}
	}

	private Votes makeVote(ParsedLine parsedLine) {
		Votes vote = new Votes();
		return vote;
	}

	private void assertHasThreeValues(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 2) {
			throw new ParseException("expected two values (voting and option) for a vote");
		}
	}

	public List<Votes> getVotes() {
		return votes;
	}
}
