package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("Im_Spring");
		user.setName("내이름은 스프링");
		user.setPassword("스프링 비번");
		
		dao.add(user);
		
		System.out.println("스프링을 이용한 데이터 저장 완료");
		
		User user_result = dao.get(user.getId());
		System.out.println("스프링을 이용한 DB 조회");
		System.out.println("ID : " + user_result.getId());
		System.out.println("Name : " + user_result.getName());
		System.out.println("Password : " + user_result.getPassword());
	}
}
