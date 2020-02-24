package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	abstract public PreparedStatement makePreparedStatement(Connection conn) throws SQLException;
}
