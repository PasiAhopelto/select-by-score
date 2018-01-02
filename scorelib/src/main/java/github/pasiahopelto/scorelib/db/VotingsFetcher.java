package github.pasiahopelto.scorelib.db;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import github.pasiahopelto.scorelib.model.Voting;

public class VotingsFetcher implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final RowMapper<Voting> ROW_TO_VOTING = new RowMapper<Voting>() {
		@Override
		public Voting mapRow(ResultSet rs, int rowNum) throws SQLException {
			Voting voting = new Voting();
			voting.setName(rs.getString("name"));
			voting.setDescription(rs.getString("description"));
			return voting;
		}
	};

	public List<Voting> fetchVotings(JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.query("select * from voting order by id", ROW_TO_VOTING);
	}
}
