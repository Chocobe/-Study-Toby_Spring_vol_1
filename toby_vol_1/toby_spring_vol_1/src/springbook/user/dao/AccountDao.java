package springbook.user.dao;

public class AccountDao {
	private ConnectionMaker connectionMaker;
	
	public AccountDao(ConnectionMaker connectionDao) {
		this.connectionMaker = connectionDao;
	}
}
