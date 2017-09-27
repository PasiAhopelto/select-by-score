package github.pasiahopelto.scorelib.reader;

import github.pasiahopelto.scorelib.model.Party;
import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.VotingOption;

public class VotesParser {

	public Votes parseEntity(ParsedLine parsedLine) throws ParseException {
		Votes result = null;
		if("votes".equals(parsedLine.getType())) {
			assertHasThreeValues(parsedLine);
			result = parseVote(parsedLine);
		}
		return result;
	}

	private Votes parseVote(ParsedLine parsedLine) {
		Votes vote = new Votes();
		vote.setParty(makeParty(parsedLine.getValues().get(0)));
		vote.setVotingOption(makeOption(parsedLine.getValues().get(1)));
		vote.setVotes(makeVotesCount(parsedLine.getValues().get(2)));
		return vote;
	}

	private Integer makeVotesCount(String votes) {
		return Integer.parseInt(votes);
	}

	private VotingOption makeOption(String optionName) {
		VotingOption votingOption = new VotingOption();
		votingOption.setName(optionName);
		return votingOption;
	}

	private Party makeParty(String partyName) {
		Party party = new Party();
		party.setName(partyName);
		return party;
	}

	private void assertHasThreeValues(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 3) {
			throw new ParseException("expected three values (party, option and count) for a votes, not " + parsedLine);
		}
	}
}
