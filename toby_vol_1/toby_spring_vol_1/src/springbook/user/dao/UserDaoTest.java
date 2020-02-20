package springbook.user.dao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import springbook.user.domain.User;

public class UserDaoTest {
	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// deleteAll() 테스트
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		
		// add() 테스트
		User user_1 = new User("Lucid", "루시드_이름", "루시드_비번");
		User user_2 = new User("Moon", "문_이름", "문_비번");
		
		dao.add(user_1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user_2);
		assertThat(dao.getCount(), is(2));
		
		
		// get() 테스트
		User userget_1 = dao.get(user_1.getId());
		assertThat(userget_1.getId(), is(user_1.getId()));
		assertThat(userget_1.getPassword(), is(user_1.getPassword()));
		
		User userget_2 = dao.get(user_2.getId());
		assertThat(userget_2.getId(), is(user_2.getId()));
		assertThat(userget_2.getPassword(), is(user_2.getPassword()));
	}
	
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user_1 = new User("lucid", "루시드", "루시드_비번");
		User user_2 = new User("moon", "문", "문_비번");
		User user_3 = new User("kyw", "김", "김_비번");
		
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user_1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user_2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user_3);
		assertThat(dao.getCount(), is(3));
	}
	
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
}
