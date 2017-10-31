package github.pasiahopelto.scorelib.reader;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.Voting;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestInputFileReader {

	@Spy
	private LineSplitter lineSplitter = new LineSplitter(); 

	@InjectMocks
	private InputFileReader reader;

	@Test
	public void insertsInputFileToDatabase() throws IOException, ParseException {
		List<Voting> votings = reader.read("input.txt");
		verifyVotings(votings);
		verifyVotes(votings);
	}

	private void verifyVotes(List<Voting> votings) {
		verifyVotes(votings.get(0), new String[] { "party one", "party one" }, new String[] { "yes", "no" }, new Integer[] { 4, 2 });
		verifyVotes(votings.get(1), new String[] { "party one", "party two", "party three" }, new String[] { "yes", "no", "yes" }, new Integer[] { 1, 1, 5 });
	}

	private void verifyVotes(Voting voting, String[] partyNames, String[] votingOptions, Integer[] voteCounts) {
		int index = 0;
		for(Votes votes : voting.getVotes()) {
			assertEquals(partyNames[index], votes.getParty());
			assertEquals(votingOptions[index], votes.getVote());
			assertEquals(votes.getParty() + " " + votes.getVote(), voteCounts[index], votes.getVotes());
			++index;
		}
	}

	private void verifyVotings(List<Voting> votings) {
		assertEquals(2, votings.size());
		assertVoting(votings.get(0), "voting name one", "description one");
		assertVoting(votings.get(1), "voting name two", "description two");
	}

	private void assertVoting(Voting voting, String name, String description) {
		assertEquals(name, voting.getName());
		assertEquals(description, voting.getDescription());
	}

}
