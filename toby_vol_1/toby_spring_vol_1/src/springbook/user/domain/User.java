package springbook.user.domain;

public class User {
	private String id;
	private String name;
	private String password;
	
	private Level level;
	private int login;
	private int recommand;
	
	
// 생성자
	public User() { }
	
	public User(String id,
				String name,
				String password,
				Level level,
				int login,
				int recommand) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.recommand = recommand;
	}
	
	
// id
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	
// name
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	
// password
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	
// level
	public void setLevel(Level level) {
		this.level = level;
	}
	public Level getLevel() {
		return level;
	}
	
	
// login
	public void setLogin(int login) {
		this.login = login;
	}
	public int getLogin() {
		return login;
	}
	
	
// recommand
	public void setRecommand(int recommand) {
		this.recommand = recommand;
	}
	public int getRecommand() {
		return recommand;
	}
}
