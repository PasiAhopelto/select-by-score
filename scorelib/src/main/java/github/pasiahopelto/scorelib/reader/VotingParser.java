package github.pasiahopelto.scorelib.reader;

import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;

public class VotingParser implements LineVisitor {

	public Voting parseEntity(ParsedLine parsedLine) throws ParseException {
		Voting result = null;
		if("voting".equals(parsedLine.getType())) {
			result = parseVoting(parsedLine);
		}
		return result;
	}

	private Voting parseVoting(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 3) {
			throw new ParseException("expected name, description and options of voting and not " + parsedLine.getValues());
		}
		Voting voting = new Voting();
		voting.setName(parsedLine.getValues().get(0));
		voting.setDescription(parsedLine.getValues().get(1));
		voting.setOptions(parseOptions(parsedLine.getValues().get(2)));
		return voting;
	}

	private List<VotingOption> parseOptions(String optionsString) {
		List<VotingOption> options = Lists.newArrayList();
		int position = 1;
		for(String option : optionsString.split(",")) {
			VotingOption votingOption = new VotingOption();
			votingOption.setName(option);
			votingOption.setPosition(position++);
			options.add(votingOption);
		}
		return options;
	}
}
