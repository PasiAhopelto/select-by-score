package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Votes;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotesInsertMaker {

	private static final String INSERT_SQL = "insert into votes (voting_id, party_id, votes, vote) values (?, ?, ?, ?)";
	private static final Integer VOTING_ID = 2;
	private static final Integer PARTY_ID = 3;
	private static final Integer VOTES = 4;
	private static final String VOTE = "yes";

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@Mock
	private Votes votes;

	@InjectMocks
	private VotesInsertMaker maker;

	@Before
	public void before() {
		doReturn(VOTES).when(votes).getVotes();
		doReturn(VOTE).when(votes).getVote();
	}

	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(votes, VOTING_ID, PARTY_ID);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(votes, VOTING_ID, PARTY_ID);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setInt(1, VOTING_ID);
		verify(preparedStatement).setInt(2, PARTY_ID);
		verify(preparedStatement).setInt(3, VOTES);
		verify(preparedStatement).setString(4, VOTE);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
