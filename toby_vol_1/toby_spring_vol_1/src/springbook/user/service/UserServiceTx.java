package springbook.user.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import springbook.user.domain.User;

public class UserServiceTx implements UserService {
	private PlatformTransactionManager transactionManager;
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
	@Override
	public void add(User user) {
		userService.add(user);
	}
	
	
	@Override
	public void upgradeLevels() {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			userService.upgradeLevels();
			transactionManager.commit(status);
			
		} catch(RuntimeException e) {
			transactionManager.rollback(status);
			throw e;
		}
	}
}