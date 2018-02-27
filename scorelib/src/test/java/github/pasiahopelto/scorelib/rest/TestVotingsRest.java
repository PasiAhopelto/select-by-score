package github.pasiahopelto.scorelib.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.fetcher.VotingsLister;
import github.pasiahopelto.scorelib.model.Voting;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestVotingsRest {

	private static final List<Voting> VOTINGS = Lists.newArrayList(mock(Voting.class));

	@Mock
	private JdbcTemplate jdbcTemplate;

	@Mock
	private VotingsLister votingsLister;

	@InjectMocks
	private VotingsRest service;

	@Before
	public void before() {
		doReturn(VOTINGS).when(votingsLister).listVotings(jdbcTemplate);
	}

	@Test
	public void listsVotings() {
		assertEquals(VOTINGS, service.votings());
	}

}
