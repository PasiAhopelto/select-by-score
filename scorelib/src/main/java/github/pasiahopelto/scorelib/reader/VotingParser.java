package github.pasiahopelto.scorelib.reader;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;

public class VotingParser implements LineVisitor {

	private ArrayList<Voting> votings = Lists.newArrayList();

	public void parseEntity(ParsedLine parsedLine) throws ParseException {
		if("voting".equals(parsedLine.getType())) {
			addVoting(parsedLine);
		}
		if("option".equals(parsedLine.getType())) {
			addOptionToVoting(parsedLine);
		}
	}

	private void addVoting(ParsedLine parsedLine) throws ParseException {
		if(parsedLine.getValues().size() != 2) {
			throw new ParseException("expected name and description of voting and not " + parsedLine.getValues());
		}
		Voting voting = new Voting();
		voting.setName(parsedLine.getValues().get(0));
		voting.setDescription(parsedLine.getValues().get(1));
		votings.add(voting);
	}

	private void addOptionToVoting(ParsedLine parsedLine) throws ParseException {
		if(votings.isEmpty()) {
			throw new ParseException("option should have preceding voting at " + parsedLine.getValues());
		}
		if(parsedLine.getValues().size() != 1) {
			throw new ParseException("expedted option value, not " + parsedLine.getValues());
		}
		Voting currentVoting = votings.get(votings.size() - 1);
		if(currentVoting.getOptions() == null) {
			currentVoting.setOptions(new ArrayList<VotingOption>());
		}
		String option = parsedLine.getValues().get(0);
		currentVoting.getOptions().add(makeOption(option));
	}

	private VotingOption makeOption(String name) {
		VotingOption votingOption = new VotingOption();
		votingOption.setName(name);
		return votingOption;
	}

	public List<Voting> getVotings() {
		return votings;
	}
}
