package org.project.barcodegeneratev2.model;

public class UserInfo {
	private String username;
	private String password;
	
	 public UserInfo()  {
	        
	 }
	 
	//Khong thay doi constructor nay, no duoc su dung trong hibernate query
	public UserInfo(String username, String password) {
		this.username = username;
		this.password = password;
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
