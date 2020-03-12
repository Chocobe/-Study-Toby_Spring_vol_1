package springbook.user.service;

import springbook.user.domain.User;

public interface UserLevelUpgradePolicy {
	abstract public boolean canUpgradeLevel(User user);
	abstract public void upgradeLevel(User user);
}
