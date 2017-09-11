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

import github.pasiahopelto.scorelib.model.Candidate;
import github.pasiahopelto.scorelib.model.Vote;
import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.model.VotingOption;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVoteInsertMaker {

	private static final String INSERT_SQL = "insert into vote (voting_id, candidate_id, option_position) values (?, ?, ?)";
	private static final Integer VOTING_ID = 2;
	private static final Integer CANDIDATE_ID = 3;
	private static final Integer OPTION_POSITION = 4;

	@Mock
	private PreparedStatement preparedStatement;
	
	@Mock
	private Connection connection;

	@Mock
	private Candidate candidate;

	@Mock
	private VotingOption votingOption;
	
	@Mock
	private Voting voting;

	@Mock
	private Vote vote;
	
	@InjectMocks
	private VoteInsertMaker maker;
	
	@Before
	public void before() {
		doReturn(candidate).when(vote).getCandidate();
		doReturn(voting).when(vote).getVoting();
		doReturn(votingOption).when(vote).getVotingOption();
		doReturn(VOTING_ID).when(voting).getId();
		doReturn(CANDIDATE_ID).when(candidate).getId();
		doReturn(OPTION_POSITION).when(votingOption).getPosition();
	}
	
	@Test(expected=SQLException.class)
	public void doesNotHideSqlException() throws SQLException {
		specifyPrepareStatementFails();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(vote);
		statementCreator.createPreparedStatement(connection);
	}

	@Test
	public void preparesStatementAndSetsVariables() throws SQLException {
		specifyPreparesStatement();
		PreparedStatementCreator statementCreator = maker.createStatementCreator(vote);
		assertSame(preparedStatement, statementCreator.createPreparedStatement(connection));
		verifySetVariables();
	}

	private void verifySetVariables() throws SQLException {
		verify(preparedStatement).setInt(1, VOTING_ID);
		verify(preparedStatement).setInt(2, CANDIDATE_ID);
		verify(preparedStatement).setInt(3, OPTION_POSITION);
	}

	private void specifyPreparesStatement() throws SQLException {
		doReturn(preparedStatement).when(connection).prepareStatement(INSERT_SQL);
	}

	private void specifyPrepareStatementFails() throws SQLException {
		doThrow(new SQLException()).when(connection).prepareStatement(INSERT_SQL);
	}
}
