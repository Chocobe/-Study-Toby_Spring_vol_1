package springbook.user.dao;

import java.sql.SQLException;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("------ DConnectionMaker 테스트 ------");
		
		ConnectionMaker connectionMaker = new DConnectionMaker();
		UserDao dao = new UserDao(connectionMaker);
		
		User user = new User();
		user.setId("D 아이디~");
		user.setName("D 이름~");
		user.setPassword("D 비번~");
		
		dao.add(user);
		
		System.out.println("DConnectionMaker를 이용한 User 저장 완료");
		
		User user_2 = dao.get(user.getId());
		System.out.println("DConnectionMaker를 이용한 User 조회");
		System.out.println("ID : " + user_2.getId());
		System.out.println("Name : " + user_2.getName());
		System.out.println("Password : " + user_2.getPassword());
		
		
		System.out.println("------ NConnectionMaker 테스트 ------");
		
		ConnectionMaker connectionMaker_2 = new NConnectionMaker();
		UserDao dao_2 = new UserDao(connectionMaker_2);
		
		User user_3 = new User();
		user_3.setId("~N 아이디");
		user_3.setName("~N 이름");
		user_3.setPassword("~N 비번");
		
		dao_2.add(user_3);
		
		System.out.println("NConnectionMaker로 유저 저장 완료!");
		
		User user_4 = dao.get(user_3.getId());
		System.out.println("NConnectionMaker를 이용한 User 조회");
		System.out.println("ID : " + user_4.getId());
		System.out.println("Name : " + user_4.getName());
		System.out.println("Password : " + user_4.getPassword());
	}
}
