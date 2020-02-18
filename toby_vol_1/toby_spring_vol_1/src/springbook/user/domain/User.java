package springbook.user.domain;

public class User {
	private String id;
	private String name;
	private String password;
	
	
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
}
