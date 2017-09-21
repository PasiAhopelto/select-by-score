package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Votes;

public class VoteInsertMaker implements InsertStatementMaker {

	private static final String INSERT_SQL = "insert into vote (voting_id, option_position) values (?, ?)";

	public PreparedStatementCreator createStatementCreator(final Serializable entity) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Votes party = (Votes) entity;
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setInt(2, party.getVotingOption().getPosition());
				return prepareStatement;
			}
		};
	}
}
