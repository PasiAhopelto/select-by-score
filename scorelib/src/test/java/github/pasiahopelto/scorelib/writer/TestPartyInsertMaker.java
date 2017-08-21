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

import github.pasiahopelto.scorelib.model.Party;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestPartyInsertMaker {

	private static final String INSERT_SQL = "insert into party (id, name) values (?, ?)";
	private static final Integer ID = 2;
	private static final String NAME = "name";

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@Mock
	private Party party;
	
	@InjectMocks
	private PartyInsertMaker maker;
	
	@Before
	public void before() {
		doReturn(ID).when(party).getId();
		doReturn(NAME).when(party).getName();
	}
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(party);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(party);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setInt(1, ID);
		verify(preparedStatement).setString(2, NAME);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
