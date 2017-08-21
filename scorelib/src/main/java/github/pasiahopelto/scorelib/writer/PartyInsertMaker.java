package github.pasiahopelto.scorelib.writer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import github.pasiahopelto.scorelib.model.Party;

public class PartyInsertMaker implements InsertStatementMaker {

	private static final String INSERT_SQL = "insert into party (id, name) values (?, ?)";

	public PreparedStatementCreator createStatementCreator(final Serializable entity) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Party party = (Party) entity;
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setInt(1, party.getId());
				prepareStatement.setString(2, party.getName());
				return prepareStatement;
			}
		};
	}
}
