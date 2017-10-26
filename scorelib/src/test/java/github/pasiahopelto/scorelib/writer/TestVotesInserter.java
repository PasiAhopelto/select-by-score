package github.pasiahopelto.scorelib.writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Votes;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotesInserter {

	private static final Integer VOTING_ID = 3;
	private static final Integer PARTY_ID = 4;
	private static final Integer VOTES = 5;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private VotesInsertMaker insertMaker;

	@Mock
	private PreparedStatementCreator statementCreator;

	@Mock
	private Votes votes;

	@InjectMocks
	private VotesInserter inserter;
	
	@Test
	public void insertsVotingAndReturnsId() {
		specifyMakesStatementCreator();
		specifyVoting();
		inserter.insert(jdbcTemplate, votes, VOTING_ID, PARTY_ID);
		verifyInsertedVoting();
	}

	private void specifyVoting() {
		doReturn(VOTES).when(votes).getVotes();
	}

	private void verifyInsertedVoting() {
		verify(jdbcTemplate).update(statementCreator);
	}

	private void specifyMakesStatementCreator() {
		doReturn(statementCreator).when(insertMaker).createStatementCreator(votes, VOTING_ID, PARTY_ID);
	}

}
