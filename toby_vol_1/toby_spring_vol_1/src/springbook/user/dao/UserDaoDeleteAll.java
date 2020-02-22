package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDao {
	protected PreparedStatement makeStatement(Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users");
		return pstmt;
	}
}
