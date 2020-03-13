package springbook.user.domain;

public class User {
	private String id;
	private String name;
	private String password;
	
	private String email;
	
	private Level level;
	private int login;
	private int recommend;
	
	
// 생성자
	public User() { }
	
	public User(String id,
				String name,
				String password,
				String email,
				Level level,
				int login,
				int recommend) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
	}
	
	
// upgradeLevel
	public void upgradeLevel() {
		Level nextLevel = this.level.nextLevel();
		
		if(nextLevel == null) {
			throw new IllegalStateException(this.level + "은 업그레이드가 불가능 합니다");
			
		} else {
			this.level = nextLevel;
		}
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
	
	
// email
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
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
	
	
// recommend
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public int getRecommend() {
		return recommend;
	}
}
