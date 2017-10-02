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
public class TestPartyInsertMaker {

	private static final String INSERT_SQL = "insert into party (name) values (?)";
	private static final String NAME = "name";

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@InjectMocks
	private PartyInsertMaker maker;
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariable() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(NAME);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariable();
	}

	private void verifySetVariable() throws SQLException {
		verify(preparedStatement).setString(1, NAME);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
