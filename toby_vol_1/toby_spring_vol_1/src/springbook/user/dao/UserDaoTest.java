package springbook.user.dao;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDaoTest {
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	
	private User user1;
	private User user2;
	private User user3;
	
	
	@Before
	public void setUp() {
		this.dao = context.getBean("userDao", UserDao.class);
		
		user1 = new User("Lucid", "루시드", "루시드_비번");
		user2 = new User("Moon", "문", "문_비번");
		user3 = new User("kyw", "김", "김_비번");
	}
	
	
	@Test
	public void addAndGet() throws SQLException {
		// deleteAll() 테스트
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		
		// add() 테스트
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		
		// get() 테스트
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getId(), is(user1.getId()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getId(), is(user2.getId()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	
	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unknown_id");
	}
}
