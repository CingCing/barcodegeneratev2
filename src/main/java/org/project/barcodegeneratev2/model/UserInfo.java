package org.project.barcodegeneratev2.model;

public class UserInfo {
	private String userName;
	private String password;
	
	//Khong thay doi constructor nay, no duoc su dung trong hibernate query
	public UserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
