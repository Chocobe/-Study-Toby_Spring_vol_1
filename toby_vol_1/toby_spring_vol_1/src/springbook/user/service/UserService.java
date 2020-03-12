package springbook.user.service;

import java.util.List;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserService {
	private UserDao userDao;
	
//	private UserLevelUpgradePolicy userLevelUpgradePolicy;
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECOMMEND_FOR_GOLD = 30;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
//	public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy) {
//		this.userLevelUpgradePolicy = userLevelUpgradePolicy;
//	}
	
	
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		
		for(User user : users) {
//			if(userLevelUpgradePolicy.canUpgradeLevel(user)) {
//				userLevelUpgradePolicy.upgradeLevel(user);
//			}
			
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
			
//			Boolean changed = null;
//			
//			if(user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
//				user.setLevel(Level.SILVER);
//				changed = true;
//				
//			} else if(user.getLevel() == Level.SILVER && user.getRecommand() >= 30) {
//				user.setLevel(Level.GOLD);
//				changed = true;
//				
//			} else if(user.getLevel() == Level.GOLD) {
//				changed = false;
//				
//			} else {
//				changed = false;
//			}
//			
//			if(changed == true) {
//				userDao.update(user);
//			}
		}
	}
	
	
	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommand() >= MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown Level : " + currentLevel);
		}
	}
	
	
	protected void upgradeLevel(User user) {
		user.upgradeLevel();		
		userDao.update(user);
	}
	
	
	public void add(User user) {
		if(user.getLevel() == null) {
			user.setLevel(Level.BASIC);
		}
		
		userDao.add(user);
	}
//	
//	
//	static class TestUserService extends UserService {
//		// 이 아이디가 작업요청되면, 예외를 발생시킬 예정
//		private String id;
//		
//		
//		private TestUserService(String id) {
//			this.id = id;
//		}
//		
//		
//		@Override
//		protected void upgradeLevel(User user) {
//			if(user.getId().equals(this.id)) throw new TestUserServiceException();
//			super.upgradeLevel(user);
//		}
//		
//		
//		static class TestUserServiceException extends RuntimeException { }
//	}
}
