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

import github.pasiahopelto.scorelib.model.VotingOption;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingOptionInsertMaker {

	private static final String INSERT_SQL = "insert into voting_option (voting_id, name, position) values (?, ?, ?)";
	private static final Integer VOTING_ID = 2;
	private static final String NAME = "name";
	private static final Integer POSITION = 3;

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@Mock
	private VotingOption votingOption;
	
	@InjectMocks
	private VotingOptionInsertMaker maker;
	
	@Before
	public void before() {
		doReturn(VOTING_ID).when(votingOption).getVotingId();
		doReturn(NAME).when(votingOption).getName();
		doReturn(POSITION).when(votingOption).getPosition();
	}
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(votingOption);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(votingOption);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setInt(1, VOTING_ID);
		verify(preparedStatement).setString(2, NAME);
		verify(preparedStatement).setInt(3, POSITION);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
