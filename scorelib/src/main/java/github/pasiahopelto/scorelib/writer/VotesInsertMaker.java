package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class VotesInsertMaker {

	private static final String INSERT_SQL = "insert into vote (voting_id, party_id, name) values (?, ?, ?)";

	public PreparedStatementCreator createStatementCreator(final String voteName, final Integer votingId, final Integer partyId) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setInt(1, votingId);
				prepareStatement.setInt(2, partyId);
				prepareStatement.setString(3, voteName);
				return prepareStatement;
			}
		};
	}
}
