package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Voting;

public class VotingInsertMaker implements InsertStatementMaker {

	private static final String INSERT_SQL = "insert into voting (id, name, description) values (?, ?, ?)";

	public PreparedStatementCreator createStatementCreator(final Serializable entity) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Voting voting = (Voting) entity;
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setString(2, voting.getName());
				prepareStatement.setString(3, voting.getDescription());
				return prepareStatement;
			}
		};
	}
}
