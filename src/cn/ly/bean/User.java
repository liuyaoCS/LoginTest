package cn.ly.bean;

public class User {
	private String username;
	private String password;
	
	public User(String username2, String password2) {
		// TODO Auto-generated constructor stub
		username=username2;
		password=password2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
	
