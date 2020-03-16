package springbook.user.service;

import java.util.List;

import springbook.user.domain.User;

public interface UserService {
	abstract public void add(User user);
	abstract public User get(String id);
	abstract public List<User> getAll();
	abstract public void deleteAll();
	abstract public void update(User user);
	
	abstract public void upgradeLevels();
}
