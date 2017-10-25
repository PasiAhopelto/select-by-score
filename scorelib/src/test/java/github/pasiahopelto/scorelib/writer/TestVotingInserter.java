package github.pasiahopelto.scorelib.writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.google.common.collect.Maps;

import github.pasiahopelto.scorelib.model.Voting;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingInserter {

	private static final Integer VOTING_ID = 2;
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private VotingInsertMaker insertMaker;

	@Mock
	private PreparedStatementCreator statementCreator;

	@Mock
	private Voting voting;

	@InjectMocks
	private VotingInserter inserter;
	
	@Test
	public void insertsVotingAndReturnsId() {
		specifyMakesStatementCreator();
		specifyVoting();
		specifyInsertsVoting();
		Integer votingId = inserter.insert(jdbcTemplate, voting);
		verifyInsertedVoting(votingId);
	}

	private void specifyVoting() {
		doReturn(NAME).when(voting).getName();
		doReturn(DESCRIPTION).when(voting).getDescription();
	}

	private void verifyInsertedVoting(Integer votingId) {
		verify(jdbcTemplate).update(same(statementCreator), any(KeyHolder.class));
		assertEquals(VOTING_ID, votingId);
	}

	private void specifyInsertsVoting() {
		doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				GeneratedKeyHolder keyHolder = invocation.getArgumentAt(1, GeneratedKeyHolder.class);
				List<Map<String, Object>> keys = keyHolder.getKeyList();
				Map<String, Object> key = Maps.newHashMap();
				key.put("id", VOTING_ID);
				keys.add(key);
				return 1;
			}
		}).when(jdbcTemplate).update(eq(statementCreator), any(KeyHolder.class));
	}

	private void specifyMakesStatementCreator() {
		doReturn(statementCreator).when(insertMaker).createStatementCreator(NAME, DESCRIPTION);
	}

}
