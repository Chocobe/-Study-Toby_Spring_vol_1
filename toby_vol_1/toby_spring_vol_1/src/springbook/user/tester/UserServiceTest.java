package springbook.user.tester;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.service.UserService;
import springbook.user.tester.UserServiceTest.TestUserService.TestUserServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	private List<User> users;
	
	
	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER - 1, 0),
				new User("joytouch", "강명성", "p2", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", Level.SILVER, 60, UserService.MIN_RECOMMEND_FOR_GOLD - 1),
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, UserService.MIN_RECOMMEND_FOR_GOLD),
				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
		);
	}
	
	
	@Test
	public void bean() {
		assertThat(userService, is(notNullValue()));
	}
	
	
	@Test
	public void upgradeLevels() {
		userDao.deleteAll();
		
		for(User user : users) {
			userDao.add(user);
		}
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
//		checkLevels(users.get(0), Level.BASIC);
//		checkLevels(users.get(1), Level.SILVER);
//		checkLevels(users.get(2), Level.SILVER);
//		checkLevels(users.get(3), Level.GOLD);
//		checkLevels(users.get(4), Level.GOLD);
	}
	
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(Level.GOLD));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}
	
	
	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		
		if(upgraded) {
			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
			
		} else {
			assertThat(userUpdate.getLevel(), is(user.getLevel()));
		}
	}
	
	
	@Test
	public void upgradeAllOrNothing() {
		UserService testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(this.userDao);
		
		userDao.deleteAll();
		
		for(User user : users) {
			userDao.add(user);
		}
		
		try {
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
			
		} catch(TestUserServiceException e) { }
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	
	static class TestUserService extends UserService {
		private String id;
		
		
		public TestUserService(String id) {
			this.id = id;
		}
		
		
		@Override
		protected void upgradeLevel(User user) {
			if(user.getId().equals(this.id)) throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
		
		
		static class TestUserServiceException extends RuntimeException {
			private static final long serialVersionUID = -1157034507180993791L;
		}
	}
}
