package springbook.user.service;

import springbook.user.domain.User;

public interface UserService {
	abstract public void upgradeLevels();
	abstract public void add(User user);
}
