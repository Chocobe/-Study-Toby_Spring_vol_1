package springbook.user.dao;

public class DUserDao extends UserDao {
	public DUserDao(ConnectionMaker connectionMaker) {
		super(connectionMaker);
	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		String id = "DUserDao";
//		String name = "DUserDao 이름";
//		String password = "DUserDao 비번";
//		
//		User user = new User();
//		user.setId(id);
//		user.setName(name);
//		user.setPassword(password);
//		
//		DUserDao dUserDao = new DUserDao();
//		dUserDao.add(user);
//		
//		System.out.println(user.getId() + " : 등록이 완료 되었습니다");
//		
//		User user_2 = dUserDao.get(user.getId());
//		
//		System.out.println("--- DUserDao에서 조회한 데이터 ---");
//		System.out.println("아이디 : " + user_2.getId());
//		System.out.println("이름 : " + user_2.getName());
//		System.out.println("비밀번호 : " + user_2.getPassword());
//	}
}
