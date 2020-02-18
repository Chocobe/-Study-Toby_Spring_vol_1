package springbook.user.dao;

public class NUserDao extends UserDao {
	public NUserDao(ConnectionMaker connectionMaker) {
		super(connectionMaker);
	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		String id = "NUserDao";
//		String name = "NUserDao 이름";
//		String password = "NUserDao 비번";
//		
//		User user = new User();
//		user.setId(id);
//		user.setName(name);
//		user.setPassword(password);
//		
//		NUserDao nUserDao = new NUserDao();
//		
//		nUserDao.add(user);
//		System.out.println(user.getId() + " - 등록 성공!");
//		
//		User user_2 = nUserDao.get(user.getId());
//		System.out.println(user_2.getId() + " - 조회 성공!");
//		System.out.println(user_2.getName());
//		System.out.println(user_2.getPassword());
//	}
}
