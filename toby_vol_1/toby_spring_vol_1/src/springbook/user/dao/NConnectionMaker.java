package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/tobySpring";
		String userName = "root";
		String password = "1111";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(url, userName, password);
	}
}
