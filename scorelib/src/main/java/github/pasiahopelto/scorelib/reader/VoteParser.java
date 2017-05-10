package github.pasiahopelto.scorelib.reader;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Vote;

public class VoteParser implements LineVisitor {

	private static final String TYPE = "vote";
	private ArrayList<Vote> votes = Lists.newArrayList();

	public void parseEntity(ParsedLine parsedLine) throws ParseException {
		if(TYPE.equals(parsedLine.getType())) {
			assertHasThreeValues(parsedLine);
			votes.add(makeVote(parsedLine));
		}
	}

	private Vote makeVote(ParsedLine parsedLine) {
		Vote vote = new Vote();
		vote.setVotingName(parsedLine.getValues().get(0));
		vote.setCandidateName(parsedLine.getValues().get(1));
		vote.setOptionName(parsedLine.getValues().get(2));
		return vote;
	}

	private void assertHasThreeValues(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 3) {
			throw new ParseException("expected three values (voting, candidate and option) for a vote");
		}
	}

	public List<Vote> getVotes() {
		return votes;
	}
}
