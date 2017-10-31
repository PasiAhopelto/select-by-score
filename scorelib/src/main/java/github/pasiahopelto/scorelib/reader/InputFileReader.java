package github.pasiahopelto.scorelib.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.Voting;

public class InputFileReader {

	private LineSplitter lineSplitter;

	public List<Voting> read(String filename) throws IOException, ParseException {
		List<Voting> result = Lists.newArrayList();
		try (BufferedReader reader = new BufferedReader(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename))))) {
			String line;
			while((line = reader.readLine()) != null) {
				parseLine(result, line);
			}
		}
		return result;
	}

	private void parseLine(List<Voting> result, String line) throws ParseException {
		ParsedLine parsedLine = lineSplitter.splitLine(line);
		List<String> values = parsedLine.getValues();
		if("voting".equals(parsedLine.getType())) {
			addVotingToResult(result, values);
		}
		else if("votes".equals(parsedLine.getType())) {
			addVotesToVoting(result, values);
		}
	}

	private void addVotingToResult(List<Voting> result, List<String> values) {
		Voting voting = new Voting();
		voting.setName(values.get(0));
		voting.setDescription(values.get(1));
		voting.setVotes(Lists.newArrayList());
		result.add(voting);
	}

	private void addVotesToVoting(List<Voting> result, List<String> values) {
		Votes votes = new Votes();
		votes.setParty(values.get(0));
		votes.setVote(values.get(1));
		votes.setVotes(Integer.parseInt(values.get(2)));
		result.get(result.size()-1).getVotes().add(votes);
	}

}
