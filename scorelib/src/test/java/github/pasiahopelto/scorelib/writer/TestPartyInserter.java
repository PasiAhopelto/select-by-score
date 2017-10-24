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

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestPartyInserter {

	private static final Integer PARTY_ID = 2;
	private static final String PARTY = "party";

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private PartyInsertMaker insertMaker;

	@Mock
	private PreparedStatementCreator statementCreator;

	@InjectMocks
	private PartyInserter inserter;

	@Test
	public void insertsPartyAndReturnsId() {
		specifyMakesStatementCreator();
		specifyInsertsParty();
		Integer partyId = inserter.insert(jdbcTemplate, PARTY);
		verifyInsertedParty(partyId);
	}

	private void verifyInsertedParty(Integer partyId) {
		verify(jdbcTemplate).update(same(statementCreator), any(KeyHolder.class));
		assertEquals(PARTY_ID, partyId);
	}

	private void specifyInsertsParty() {
		doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				GeneratedKeyHolder keyHolder = invocation.getArgumentAt(1, GeneratedKeyHolder.class);
				List<Map<String, Object>> keys = keyHolder.getKeyList();
				Map<String, Object> key = Maps.newHashMap();
				key.put("id", PARTY_ID);
				keys.add(key);
				return 1;
			}
		}).when(jdbcTemplate).update(eq(statementCreator), any(KeyHolder.class));
	}

	private void specifyMakesStatementCreator() {
		doReturn(statementCreator).when(insertMaker).createStatementCreator(PARTY);
	}

}
