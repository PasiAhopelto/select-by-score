package github.pasiahopelto.scorelib.writer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.model.Votes;
import github.pasiahopelto.scorelib.model.Voting;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestDataWriter {

	private static final String VOTING_ONE_NAME = "voting one name";
	private static final String VOTING_ONE_DESCRIPTION = "voting one description";
	private static final String VOTING_TWO_NAME = "voting two name";
	private static final String VOTING_TWO_DESCRIPTION = "voting two description";
	private static final String PARTY_ONE = "party one";
	private static final String PARTY_TWO = "party two";
	private static final String YES = "yes";
	private static final String NO = "no";
	private static final Integer VOTES_ONE = 1;
	private static final Integer VOTES_TWO = 2;

	@Spy
	private PartyInsertMaker partyInsertMaker = new PartyInsertMaker();

	@Spy
	private PartyInserter partyInserter = new PartyInserter(partyInsertMaker);

	@Spy
	private VotesInsertMaker votesInsertMaker = new VotesInsertMaker();

	@Spy
	private VotesInserter votesInserter = new VotesInserter(votesInsertMaker);

	@Spy
	private VotingInsertMaker votingInsertMaker = new VotingInsertMaker();

	@Spy
	private DbMaker dbMaker = new DbMaker();

	@Spy
	private VotingInserter votingInserter = new VotingInserter(votingInsertMaker);

	@InjectMocks
	private DataWriter dataWriter;

	private List<Voting> votings = Lists.newArrayList();
	private JdbcTemplate jdbcTemplate;
	private int votingOneRowCount;
	private int votingTwoRowCount;

	@Before
	public void before() {
		votings.add(newVoting(VOTING_ONE_NAME, VOTING_ONE_DESCRIPTION));
		votings.add(newVoting(VOTING_TWO_NAME, VOTING_TWO_DESCRIPTION));
		doAnswer(new Answer<JdbcTemplate>() {
			@Override
			public JdbcTemplate answer(InvocationOnMock invocation) throws Throwable {
				jdbcTemplate = (JdbcTemplate) invocation.callRealMethod();
				return jdbcTemplate;
			}
		}).when(dbMaker).createDatabase();
	}

	@Test
	public void writesVotingsToDatabase() {
		dataWriter.write(votings);
		verifyVotings();
	}

	/**
	 * VOTING ONE
	 * 	 PARTY_ONE YES 1
	 * 	 PARTY_TWO NO 2
	 * VOTING TWO
	 * 	 PARTY_ONE YES 1
	 * 	 PARTY_TWO NO 2
	 */
	private void verifyVotings() {
		jdbcTemplate.query("select v.name as voting_name, v.description as voting_description, p.name as party_name, vs.vote, vs.votes from voting v inner join votes vs on vs.voting_id = v.id inner join party p on p.id = vs.party_id", new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String votingName = rs.getString("voting_name");
				String votingDescription = rs.getString("voting_description");
				String partyName = rs.getString("party_name");
				String vote = rs.getString("vote");
				Integer votes = rs.getInt("votes");
				if(PARTY_ONE.equals(partyName)) {
					 assertEquals(VOTES_ONE, votes);
					 assertEquals(YES, vote);
				}
				else {
					 assertEquals(VOTES_TWO, votes);
					 assertEquals(NO, vote);
				}
				if(VOTING_ONE_NAME.equals(votingName)) {
					assertEquals(VOTING_ONE_DESCRIPTION, votingDescription);
					++votingOneRowCount;
				}
				else {
					assertEquals(VOTING_TWO_DESCRIPTION, votingDescription);
					++votingTwoRowCount;
				}
			}
		});
		assertEquals(2, votingOneRowCount);
		assertEquals(2, votingTwoRowCount);
	}

	private Voting newVoting(String votingName, String votingDescription) {
		Voting voting = new Voting();
		voting.setName(votingName);
		voting.setDescription(votingDescription);
		voting.setVotes(Lists.newArrayList());
		voting.getVotes().add(newVotes(PARTY_ONE, VOTES_ONE, YES));
		voting.getVotes().add(newVotes(PARTY_TWO, VOTES_TWO, NO));
		return voting;
	}

	private Votes newVotes(String party, Integer voteCount, String vote) {
		Votes votes = new Votes();
		votes.setParty(party);
		votes.setVotes(voteCount);
		votes.setVote(vote);
		return votes;
	}
}
