package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class PartyInsertMaker {

	private static final String INSERT_SQL = "insert into party (name) values (?)";

	public PreparedStatementCreator createStatementCreator(final String partyName) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
				preparedStatement.setString(1, partyName);
				return preparedStatement;
			}
		};
	}
}
