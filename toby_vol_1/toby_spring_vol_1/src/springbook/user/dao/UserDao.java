package springbook.user.dao;

import java.util.List;

import springbook.user.domain.User;

public interface UserDao {
	abstract public void add(User user);
	abstract public User get(String id);
	abstract public List<User> getAll();
	abstract public void deleteAll();
	abstract public int getCount();
	abstract public void update(User user);
}
