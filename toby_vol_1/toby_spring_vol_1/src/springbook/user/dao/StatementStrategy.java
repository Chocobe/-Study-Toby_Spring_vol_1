package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	abstract PreparedStatement makePreparedStatement(Connection conn) throws SQLException;
}
