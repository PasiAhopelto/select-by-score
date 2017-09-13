package github.pasiahopelto.scorelib.writer;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class TestDbMaker {

	@InjectMocks
	private DbMaker dbMaker;

	@Test
	public void returnsDataSourceThatHasConnection() throws SQLException {
		JdbcTemplate jdbcTemplate = dbMaker.createDatabase();
		DataSource dataSource = jdbcTemplate.getDataSource();
		Connection connection = dataSource.getConnection();
		connection.close();
	}

	@Test
	public void createsPartyTable() throws SQLException {
		verifyTableExists("party");
	}

	@Test
	public void createsVotingTable() throws SQLException {
		verifyTableExists("voting");
	}

	@Test
	public void createsVoteTable() throws SQLException {
		verifyTableExists("vote");
	}

	@Test
	public void createsVotingOptionTable() throws SQLException {
		verifyTableExists("voting_option");
	}

	private void verifyTableExists(String table) {
		JdbcTemplate jdbcTemplate = dbMaker.createDatabase();
		SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select count(*) from " + table);
		assertTrue(sqlRowSet.first());
		assertEquals(0, sqlRowSet.getInt(1));
	}
}
