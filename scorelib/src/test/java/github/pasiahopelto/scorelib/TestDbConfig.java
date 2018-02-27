package github.pasiahopelto.scorelib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import github.pasiahopelto.scorelib.fetcher.VotingsLister;
import github.pasiahopelto.scorelib.rest.VotingsRest;

@RunWith(MockitoJUnitRunner.class)
public class TestDbConfig {

	private JdbcTemplate jdbcTemplate;
	private VotingsLister votingsLister;
	private VotingsRest votingsRest;

	@Before
	public void before() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		votingsLister = context.getBean(VotingsLister.class);
		votingsRest = context.getBean(VotingsRest.class);
		context.close();
	}

	@Test
	public void createdJdbcTemplateBean() {
		assertNotNull(jdbcTemplate);
		assertNotNull(jdbcTemplate.queryForObject("select count(*) from party", Integer.class));
	}

	@Test
	public void populatedDatabase() {
		assertEquals(Integer.valueOf(3), jdbcTemplate.queryForObject("select count(*) from party", Integer.class));
		assertEquals(Integer.valueOf(2), jdbcTemplate.queryForObject("select count(*) from voting", Integer.class));
	}

	@Test
	public void definesVotingsListerBean() {
		assertEquals(2, votingsLister.listVotings(jdbcTemplate).size());
	}

	@Test
	public void definesVotingsRest() {
		assertEquals(2, votingsRest.votings().size());
	}
}
