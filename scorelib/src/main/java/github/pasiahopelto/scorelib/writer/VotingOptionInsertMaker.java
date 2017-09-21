package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.VotingOption;

public class VotingOptionInsertMaker implements InsertStatementMaker {

	private static final String INSERT_SQL = "insert into voting_option (voting_id, name, position) values (?, ?, ?)";

	public PreparedStatementCreator createStatementCreator(final Serializable entity) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				VotingOption party = (VotingOption) entity;
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setString(2, party.getName());
				prepareStatement.setInt(3, party.getPosition());
				return prepareStatement;
			}
		};
	}
}