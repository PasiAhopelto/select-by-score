package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Voting;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotesInsertMaker {

	private static final String INSERT_SQL = "insert into vote (voting_id, party_id, name) values (?, ?, ?)";
	private static final Integer VOTING_ID = 2;
	private static final Integer PARTY_ID = 3;
	private static final String NAME = "name";

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@Mock
	private Voting voting;

	@InjectMocks
	private VotesInsertMaker maker;
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME, VOTING_ID, PARTY_ID);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME, VOTING_ID, PARTY_ID);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setInt(1, VOTING_ID);
		verify(preparedStatement).setInt(2, PARTY_ID);
		verify(preparedStatement).setString(3, NAME);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
