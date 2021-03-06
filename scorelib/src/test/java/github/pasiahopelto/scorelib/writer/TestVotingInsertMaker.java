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

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingInsertMaker {

	private static final String INSERT_SQL = "insert into voting (name, description) values (?, ?)";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@InjectMocks
	private VotingInsertMaker maker;
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME, DESCRIPTION);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME, DESCRIPTION);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setString(1, NAME);
		verify(preparedStatement).setString(2, DESCRIPTION);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
