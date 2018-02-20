package github.pasiahopelto.scorelib.fetcher;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import github.pasiahopelto.scorelib.DbConfig;
import github.pasiahopelto.scorelib.model.Voting;
import github.pasiahopelto.scorelib.writer.DbMaker;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingsLister {

	@Spy
	private DbMaker dbMaker;

	@InjectMocks
	private VotingsLister lister;

	private JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		context.close();
	}

	@Test
	public void listsVotings() {
		List<Voting> votings = lister.listVotings(jdbcTemplate);
		assertEquals(2, votings.size());
		assertEquals("voting name one", votings.get(0).getName());
		assertEquals("voting name two", votings.get(1).getName());
	}

}
