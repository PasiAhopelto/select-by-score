package github.pasiahopelto.scorelib.writer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class PartyInserter {

	private PartyInsertMaker statementMaker;

	public PartyInserter(PartyInsertMaker partyInsertMaker) {
		statementMaker = partyInsertMaker;
	}

	public Integer insert(JdbcTemplate jdbcTemplate, String partyName) {
		PreparedStatementCreator statementCreator = statementMaker.createStatementCreator(partyName);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(statementCreator, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
