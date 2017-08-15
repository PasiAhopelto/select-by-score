package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class Inserter {

	private Map<Class<? extends Serializable>, InsertStatementMaker> statementMakers;

	public void insert(Serializable entity, JdbcTemplate jdbcTemplate) throws UnknownEntityException {
		InsertStatementMaker statementMaker = statementMakers.get(entity.getClass());
		if(statementMaker == null) {
			throw new UnknownEntityException(entity.getClass());
		}
		PreparedStatementCreator statementCreator = statementMaker.createStatementCreator(entity);
		jdbcTemplate.update(statementCreator);
	}
}
