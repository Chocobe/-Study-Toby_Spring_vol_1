package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import springbook.user.domain.User;

public class NUserDao extends UserDao {
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/tobySpring";
		String userName = "root";
		String password = "1111";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(url, userName, password);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String id = "chocobe_4";
		String name = "김영우";
		String password = "내 비번_4";
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		
		NUserDao nUserDao = new NUserDao();
		
		nUserDao.add(user);
		System.out.println(user.getId() + " - 등록 성공!");
		
		User user_2 = nUserDao.get(user.getId());
		System.out.println(user_2.getId() + " - 조회 성공!");
		System.out.println(user_2.getName());
		System.out.println(user_2.getPassword());
	}
}
