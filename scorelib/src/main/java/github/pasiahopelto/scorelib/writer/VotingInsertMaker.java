package github.pasiahopelto.scorelib.writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class VotingInsertMaker {

	private static final String INSERT_SQL = "insert into voting (name, description) values (?, ?)";

	public PreparedStatementCreator createStatementCreator(final String name, final String description) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT_SQL);
				prepareStatement.setString(1, name);
				prepareStatement.setString(2, description);
				return prepareStatement;
			}
		};
	}
}
