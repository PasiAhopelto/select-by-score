package github.pasiahopelto.scorelib.writer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import github.pasiahopelto.scorelib.model.Voting;

public class VotingInserter {

	private VotingInsertMaker statementMaker;

	public Integer insert(JdbcTemplate jdbcTemplate, Voting voting) {
		PreparedStatementCreator statementCreator = statementMaker.createStatementCreator(voting.getName(), voting.getDescription());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(statementCreator, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
