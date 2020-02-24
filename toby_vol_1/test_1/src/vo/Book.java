package vo;

public class Book {
	private String title;
	private String auth;
	private int price;
	
	
	public Book() {}
	
	public Book(String title,
				String auth,
				int price) {
		this.title = title;
		this.auth = auth;
		this.price = price;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}

	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
