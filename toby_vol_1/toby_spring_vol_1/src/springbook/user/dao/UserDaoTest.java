package springbook.user.dao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.domain.User;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		
		// INSERT 테스트
		User user = new User();
		user.setId("아이디_1");
		user.setPassword("비밀번호_1");
		user.setName("이름_1");
		
		dao.add(user);
		

		// SELECT 테스트
		User user2 = dao.get(user.getId());
		
		assertThat(user.getName(), is("김영우"));
		assertThat(user.getPassword(), is(user2.getPassword()));
	}
}
