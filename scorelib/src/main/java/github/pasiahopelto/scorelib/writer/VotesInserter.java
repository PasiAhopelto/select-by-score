package github.pasiahopelto.scorelib.writer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Votes;

public class VotesInserter {

	private VotesInsertMaker statementMaker;

	public void insert(JdbcTemplate jdbcTemplate, Votes votes, Integer votingId, Integer partyId) {
		PreparedStatementCreator statementCreator = statementMaker.createStatementCreator(votes, votingId, partyId);
		jdbcTemplate.update(statementCreator);
	}
}
