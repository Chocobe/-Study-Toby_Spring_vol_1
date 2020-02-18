package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import springbook.user.domain.User;

public class DUserDao extends UserDao {
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost/tobySpring";
		String userName = "root";
		String password = "1111";
		
		Class.forName("com.mysql.jdbc.Driver");
		
		return DriverManager.getConnection(url, userName, password);
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String id = "spring";
		String name = "스프링~~~";
		String password = "스프링 비번";
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(password);
		
		DUserDao dUserDao = new DUserDao();
		dUserDao.add(user);
		
		System.out.println(user.getId() + " : 등록이 완료 되었습니다");
		
		User user_2 = dUserDao.get(user.getId());
		
		System.out.println("--- DUserDao에서 조회한 데이터 ---");
		System.out.println("아이디 : " + user_2.getId());
		System.out.println("이름 : " + user_2.getName());
		System.out.println("비밀번호 : " + user_2.getPassword());
	}
}
