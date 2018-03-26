package github.pasiahopelto.scorelib.fetcher;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Lists;

import github.pasiahopelto.scorelib.DbConfig;
import github.pasiahopelto.scorelib.model.Score;
import github.pasiahopelto.scorelib.model.Selection;

@RunWith(MockitoJUnitRunner.class)
public class TestSelectionScorer {

	private static final String NO_VOTING_NAME = "no voting name";
	private static final String VOTE_NO = "no";
	private static final String VOTE_YES = "yes";
	private static final String VOTING_NAME = "voting name one";
	private static final String VOTING_NAME_TWO = "voting name two";
	private static final String PARTY_ONE = "party one";
	private static final String PARTY_TWO = "party two";
	private static final String PARTY_THREE = "party three";

	@InjectMocks
	private SelectionScorer scorer;

	private JdbcTemplate jdbcTemplate;
	private ArrayList<Selection> selections = Lists.newArrayList();

	@Before
	public void before() {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(DbConfig.class);
		jdbcTemplate = context.getBean(JdbcTemplate.class);
		context.close();
	}

	@Test
	public void returnsEmptyListIfSelectionIsEmpty() {
		assertTrue(scorer.scoreAndSort(jdbcTemplate, Lists.newArrayList()).isEmpty());
	}

	@Test
	public void returnsEmptyListIfSelectedVotingDoesNotMatchAvailableVotings() {
		specifySelection(NO_VOTING_NAME, VOTE_YES);
		assertTrue(scorer.scoreAndSort(jdbcTemplate, selections).isEmpty());
	}

	@Test
	public void returnsSolePartyWithScore() {
		specifySelection(VOTING_NAME, VOTE_YES);
		List<Score> scores = scorer.scoreAndSort(jdbcTemplate, selections);
		assertEquals(1, scores.size());
		verifyVote(scores.get(0), PARTY_ONE, 0.66f);
	}

	@Test
	public void returnsPartiesWithScoresAndSortsByPercentageAndVoteCount() {
		specifySelection(VOTING_NAME_TWO, VOTE_YES);
		List<Score> scores = scorer.scoreAndSort(jdbcTemplate, selections);
		assertEquals(3, scores.size());
		verifyVote(scores.get(2), PARTY_THREE, 1);
		verifyVote(scores.get(1), PARTY_ONE, 1);
		verifyVote(scores.get(0), PARTY_TWO, 0);
	}

	@Test
	public void selectsFromMultipleVotings() {
		specifySelection(VOTING_NAME, VOTE_NO);
		specifySelection(VOTING_NAME_TWO, VOTE_YES);
		List<Score> scores = scorer.scoreAndSort(jdbcTemplate, selections);
		assertEquals(3, scores.size());
		verifyVote(scores.get(2), PARTY_THREE, 1);
		verifyVote(scores.get(1), PARTY_ONE, 0.28f);
		verifyVote(scores.get(0), PARTY_TWO, 0f);
	}

	private void verifyVote(Score score, String party, float percentage) {
		assertEquals(party, score.getParty());
		assertEquals(score.toString(), percentage, score.getPercentage(), 0.01f);
	}

	private void specifySelection(String votingName, String vote) {
		Selection selection = new Selection();
		selection.setVotingName(votingName);
		selection.setVote(vote);
		selections.add(selection);
	}
}
