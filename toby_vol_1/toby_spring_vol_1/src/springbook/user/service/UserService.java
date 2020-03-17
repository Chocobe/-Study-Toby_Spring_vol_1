package springbook.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import springbook.user.domain.User;

@Transactional
public interface UserService {
	abstract public void add(User user);
	
	@Transactional(readOnly=true)
	abstract public User get(String id);
	
	@Transactional(readOnly=true)
	abstract public List<User> getAll();
	
	abstract public void deleteAll();
	
	abstract public void update(User user);
	
	abstract public void upgradeLevels();
}
