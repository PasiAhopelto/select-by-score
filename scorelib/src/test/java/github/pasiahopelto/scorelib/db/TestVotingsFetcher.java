package github.pasiahopelto.scorelib.db;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import github.pasiahopelto.scorelib.DbConfig;
import github.pasiahopelto.scorelib.model.Voting;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingsFetcher {

	private VotingsFetcher fetcher = new VotingsFetcher();
	private JdbcTemplate jdbcTemplate;

	@Before
	public void before() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		context.close();
	}

	@Test
	public void isSerialiable() {
		assertTrue(fetcher instanceof Serializable);
	}

	@Test
	public void fetchesVotings() {
		List<Voting> votings = fetcher.fetchVotings(jdbcTemplate);
		assertEquals(2, votings.size());
		verifyVoting(votings.get(0), "voting name one", "description one");
		verifyVoting(votings.get(1), "voting name two", "description two");
	}

	private void verifyVoting(Voting voting, String name, String description) {
		assertEquals(name, voting.getName());
		assertEquals(description, voting.getDescription());
	}
}
