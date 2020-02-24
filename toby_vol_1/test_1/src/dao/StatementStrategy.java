package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	abstract public PreparedStatement makePrepareStatement(Connection conn) throws SQLException;
}
