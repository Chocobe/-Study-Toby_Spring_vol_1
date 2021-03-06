package springbook.user.tester;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserServiceTest {
	@Autowired private UserService userService;
	@Autowired private UserService testUserService;
	@Autowired private UserDao userDao;
	@Autowired private PlatformTransactionManager transactionManager;
	
	private List<User> users;
	
	
	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", "email_1@test.com", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER - 1, 0),
				new User("joytouch", "강명성", "p2", "email_2@test.com", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", "email_3@test.com", Level.SILVER, 60, UserServiceImpl.MIN_RECOMMEND_FOR_GOLD - 1),
				new User("madnite1", "이상호", "p4", "email_4@test.com", Level.SILVER, 60, UserServiceImpl.MIN_RECOMMEND_FOR_GOLD),
				new User("green", "오민규", "p5", "email_5@test.com", Level.GOLD, 100, Integer.MAX_VALUE)
		);
	}
	
	
	@Test
	public void bean() {
		assertThat(userService, is(notNullValue()));
		assertThat(testUserService, is(notNullValue()));
		assertThat(userDao, is(notNullValue()));
		assertThat(transactionManager, is(notNullValue()));
	}
	
	
	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		MockUserDao mockUserDao = new MockUserDao(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MockMailSender mockMailSender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		List<User> updated = mockUserDao.getUpdated();
		assertThat(updated.size(), is(2));
		checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER);
		checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD);
		
		List<String> request = mockMailSender.getRequests();
		assertThat(request.size(), is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));
	}
	
	
	private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) {
		assertThat(updated.getId(), is(expectedId));
		assertThat(updated.getLevel(), is(expectedLevel));
	}
	
	
	@Test
	public void mockUpgradeLevels() throws Exception {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		
		UserDao mockUserDao = mock(UserDao.class);
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);
		
		MailSender mockMailSender = mock(MailSender.class);
		userServiceImpl.setMailSender(mockMailSender);
		
		userServiceImpl.upgradeLevels();
		
		// Level update 테스트
		verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao, times(2)).update(any(User.class));
		
		verify(mockUserDao).update(users.get(1));
		assertThat(users.get(1).getLevel(), is(Level.SILVER));
		
		verify(mockUserDao).update(users.get(3));
		assertThat(users.get(3).getLevel(), is(Level.GOLD));
		
		// Mail 테스트
		ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertThat(mailMessages.get(0).getTo()[0], is(users.get(1).getEmail()));
		assertThat(mailMessages.get(1).getTo()[0], is(users.get(3).getEmail()));
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
	public void upgradeAllOrNothing() throws Exception {
//		TestUserServiceImpl testUserServiceImpl = new TestUserServiceImpl(users.get(3).getId());
//		testUserService.setUserDao(this.userDao);
//		testUserService.setMailSender(this.mailSender);
//		
//		ProxyFactoryBean txProxyFactoryBean = context.getBean("&userService", ProxyFactoryBean.class);
//		txProxyFactoryBean.setTarget(testUserService);
//		
//		UserService txUserService = (UserService)txProxyFactoryBean.getObject();
		
		userDao.deleteAll();
		
		for(User user : users) {
			userDao.add(user);
		}
		
		try {
			this.testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
			
		} catch(TestUserServiceException e) { }
		
		checkLevelUpgraded(users.get(1), false);
	}
	
	
	@Test(expected=TransientDataAccessResourceException.class)
	public void readOnlyTransactionAttribute() {
		testUserService.getAll();
	}
	
	
	static class TestUserServiceImpl extends UserServiceImpl {
		private String id = "madnite1";
		
		
		@Override
		protected void upgradeLevel(User user) {
			
			if(user.getId().equals(this.id)) {
				throw new TestUserServiceException();
			}
			super.upgradeLevel(user);
		}
		
		
		@Override
		public List<User> getAll() {
			for(User user : super.getAll()) {
				super.update(user);
			}
			
			return null;
		}
	}
	
	
	static class TestUserServiceException extends RuntimeException {
		private static final long serialVersionUID = -1157034507180993791L;
	}
	
	
	static class MockMailSender implements MailSender {
		private List<String> requests = new ArrayList<String>();
		
		
		public List<String> getRequests() {
			return requests;
		}
		
		
		@Override
		public void send(SimpleMailMessage mailMessage) throws MailException {
			requests.add(mailMessage.getTo()[0]);
		}
		
		
		@Override
		public void send(SimpleMailMessage[] mailMessage) throws MailException {
			
		}
	}
	
	
	static class MockUserDao implements UserDao {
		private List<User> users;
		private List<User> updated = new ArrayList<User>();
		
		
		private MockUserDao(List<User> users) {
			this.users = users;
		}
		
		
		// DB에서 갱신된 데이터를 가져오는 역할
		public List<User> getUpdated() {
			return updated;
		}
		

		// 마치 DB에서 데이터를 가져오는 역할
		@Override 
		public List<User> getAll() { 
			return users;
		}
		
		
		// DB의 데이터를 갱신하는 역할
		@Override
		public void update(User user) {
			updated.add(user);
		}
		

		@Override public void add(User user) { throw new UnsupportedOperationException(); }
		@Override public User get(String id) { throw new UnsupportedOperationException(); }
		@Override public void deleteAll() { throw new UnsupportedOperationException(); } 
		@Override public int getCount() { throw new UnsupportedOperationException(); }
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void transactionSync() {
		userDao.deleteAll();
		
		userService.add(users.get(0));
		userService.add(users.get(1));
		assertThat(userDao.getCount(), is(2));
	}
}
