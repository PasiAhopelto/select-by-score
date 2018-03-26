package github.pasiahopelto.scorelib.fetcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import github.pasiahopelto.scorelib.model.Score;
import github.pasiahopelto.scorelib.model.Selection;

public class SelectionScorer {

	private static final RowMapper<Score> ROW_TO_SCORE = new RowMapper<Score>() {
		@Override
		public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
			Score score = new Score();
			score.setParty(rs.getString("party"));
			score.setMatchingVotes(rs.getInt("matching_votes"));
			score.setTotalVotes(rs.getInt("all_votes"));
			return score;
		}
	};

	private static final Comparator<? super Score> SCORE_SORTER = new Comparator<Score>() {
		@Override
		public int compare(Score left, Score right) {
			return compare(2, left.getPercentage(), right.getPercentage()) + compare(1, left.getTotalVotes(), right.getTotalVotes());
		}
		
		private int compare(int multiplier, float left, float right) {
			return (left == right ? 0 : left < right ? -1 * multiplier : multiplier);
		}

		private int compare(int multiplier, int left, int right) {
			return (left == right ? 0 : left < right ? -1 * multiplier : multiplier);
		}
	};

	public List<Score> scoreAndSort(JdbcTemplate jdbcTemplate, List<Selection> selections) {
		List<Score> scores = Lists.newArrayList();
		for (Selection selection : selections) {
			List<Score> scoresForSelection = jdbcTemplate.query("select p.name as party, sum(vs.votes) as all_votes, sum(case when vs.vote = ? then vs.votes else 0 end) as matching_votes from votes vs inner join party p on p.id = vs.party_id inner join voting vn on vn.id = vs.voting_id where vn.name = ? group by p.name", new Object[] { selection.getVote(), selection.getVotingName() }, ROW_TO_SCORE);
			scores.addAll(scoresForSelection);
		}
		consolidate(scores);
		scores.sort(SCORE_SORTER);
		return scores;
	}

	private void consolidate(List<Score> scores) {
		Map<String, Score> result = Maps.newHashMap();
		for(Score score : scores) {
			String party = score.getParty();
			if(result.containsKey(party)) {
				Score matchingScore = result.get(party);
				matchingScore.setTotalVotes(matchingScore.getTotalVotes() + score.getTotalVotes());
			}
			else {
				result.put(party, score);
			}
		}
		scores.clear();
		scores.addAll(result.values());
	}
}
