package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDao {
// add
	public void add(User user) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/tobySpring";
		String userName = "root";
		String password = "1111";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(url, userName, password);
		
		PreparedStatement ps = c.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
// get
	public User get(String id) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/tobySpring";
		String userName = "root";
		String password = "1111";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection(url, userName, password);
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE id=?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	
// Console TEST
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("chocobe");
		user.setName("김영우");
		user.setPassword("내 비번");
		
		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공!");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "조회 성공!!");
	}
}
