package springbook.user.service;

import springbook.user.dao.UserDao;
import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserLevelUpgradePolicyCommon implements UserLevelUpgradePolicy {
	private UserDao userDao;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		
		switch(currentLevel) {
			case BASIC: return (user.getLogin() >= UserService.MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommand() >= UserService.MIN_RECOMMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalStateException("Unknown Level : " + currentLevel);
		}
	}
	
	
	@Override
	public void upgradeLevel(User user) {
		user.setLevel(user.getLevel().nextLevel());
		userDao.update(user);
	}
}
