package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Votes;

public class VotesInsertMaker {

	private static final String INSERT_SQL = "insert into votes (voting_id, party_id, votes, vote) values (?, ?, ?, ?)";

	public PreparedStatementCreator createStatementCreator(final Votes votes, final Integer votingId, final Integer partyId) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setInt(1, votingId);
				prepareStatement.setInt(2, partyId);
				prepareStatement.setInt(3, votes.getVotes());
				prepareStatement.setString(4, votes.getVote());
				return prepareStatement;
			}
		};
	}
}
