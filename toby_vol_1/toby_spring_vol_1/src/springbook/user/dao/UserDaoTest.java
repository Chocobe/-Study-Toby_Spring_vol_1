package springbook.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		testEqualsByDaoFactory();
		
		testEqualsByApplicationContext();
	}
	
	
// 1. 스프링 컨테이너(애플리케이션 컨텍스트) 테스트
	public static void testIoC() throws ClassNotFoundException, SQLException {
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
	
	
// 2. getBean()이 반환하는 오브젝트의 동일성 테스트
	public static void testEqualsByDaoFactory() {
		DaoFactory factory = new DaoFactory();
		
		UserDao dao_1 = factory.userDao();
		UserDao dao_2 = factory.userDao();
		
		System.out.println("factory.userDao() 첫번쨰 : " + dao_1);
		System.out.println("factory.userDao() 두번째 : " + dao_2);
	}
	
	public static void testEqualsByApplicationContext() {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		UserDao dao_1 = context.getBean("userDao", UserDao.class);
		UserDao dao_2 = context.getBean("userDao", UserDao.class);
		
		System.out.println("ApplicationContext.getBean() 첫번째 : " + dao_1);
		System.out.println("ApplicationContext.getBean() 두번째 : " + dao_2);
	}
}
