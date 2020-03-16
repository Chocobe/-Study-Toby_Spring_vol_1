package springbook.user.service;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private MailSender mailSender;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
	@Override
	public User get(String id) {
		return userDao.get(id);
	}
	
	
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	
	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}
	
	
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	
	
//	public void upgradeLevels() throws Exception {
//		TransactionSynchronizationManager.initSynchronization();
//		Connection conn = DataSourceUtils.getConnection(dataSource);
//		conn.setAutoCommit(false);
//		
//		try {
//			List<User> users = userDao.getAll();
//			
//			for(User user : users) {
//				if(canUpgradeLevel(user)) {
//					upgradeLevel(user);
//				}
//			}
//			
//			conn.commit();
//			
//		} catch(Exception e) {
//			conn.rollback();
//			throw e;
//			
//		} finally {
//			DataSourceUtils.releaseConnection(conn, dataSource);
//			TransactionSynchronizationManager.unbindResource(this.dataSource);
//			TransactionSynchronizationManager.clearSynchronization();
//		}
//	}
	@Override
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		
		for(User user : users) {
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}
	
	
	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level : " + currentLevel);
		}
	}
	
	
	protected void upgradeLevel(User user) {
		user.upgradeLevel();		
		userDao.update(user);
		sendUpgradeEmail(user);
	}
	
	
	@Override
	public void add(User user) {
		if(user.getLevel() == null) {
			user.setLevel(Level.BASIC);
		}
		
		userDao.add(user);
	}
	
	
	private void sendUpgradeEmail(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("useradmin@ksug.org");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevel().name() + " (으)로 업그래이드 되었습니다");
		
		mailSender.send(mailMessage);
		
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "mail.ksug.org");
//		Session s = Session.getInstance(props, null);
//		
//		MimeMessage message = new MimeMessage(s);
//		
//		try {
//			message.setFrom(new InternetAddress("useradmin@ksug.org"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//			message.setSubject("Upgrade 안내");
//			message.setText("사용자님의 등급이 " + user.getLevel().name() + "(으)로 업그레이드 되었습니다");
//			
//			Transport.send(message);
//			
//		} catch(AddressException e) {
//			throw new RuntimeException(e);
//			
//		} catch(MessagingException e) {
//			throw new RuntimeException(e);
//			
//		}
//		catch(UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
	}
}
