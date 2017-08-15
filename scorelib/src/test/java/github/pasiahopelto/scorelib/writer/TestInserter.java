package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestInserter {

	private static final String ENTITY = "entity";
	
	@Mock
	private PreparedStatementCreator preparedStatementCreator;
	
	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private Map<Class<? extends Serializable>, ? extends InsertStatementMaker> statementMakers;

	@Mock
	private InsertStatementMaker statementMaker;

	@InjectMocks
	private Inserter inserter;

	@Before
	public void before() {
		doReturn(statementMaker).when(statementMakers).get(String.class);
		doReturn(preparedStatementCreator).when(statementMaker).createStatementCreator(ENTITY);
	}

	@Test(expected=UnknownEntityException.class)
	public void throwsExceptionIfGivenUnknownClass() throws UnknownEntityException {
		inserter.insert(Boolean.FALSE, jdbcTemplate);
	}

	@Test
	public void insertsEntity() throws UnknownEntityException {
		inserter.insert(ENTITY, jdbcTemplate);
		verify(jdbcTemplate).update(preparedStatementCreator);
	}
}
