package github.pasiahopelto.scorelib.fetcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import github.pasiahopelto.scorelib.model.Voting;

public class VotingsLister {

	private static final RowMapper<Voting> ROW_TO_VOTING = new RowMapper<Voting>() {
		@Override
		public Voting mapRow(ResultSet rs, int rowNum) throws SQLException {
			Voting voting = new Voting();
			voting.setName(rs.getString("name"));
			voting.setDescription(rs.getString("description"));
			return voting;
		}
	};

	public List<Voting> listVotings(JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.query("select name, description from voting order by id", ROW_TO_VOTING);
	}

}
