package cn.ly.bean;

public class User {
	private int id;
	private double money;
	private String name;
	private String password;
	public User(){
		
	}
	public User(String username2, String password2) {
		// TODO Auto-generated constructor stub
		name=username2;
		password=password2;
	}
	public User(int id2,double money2,String username2, String password2){
		id=id2;
		money=money2;
		name=username2;
		password=password2;	
	}
	public String getUsername() {
		return name;
	}
	public void setUsername(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}
	
