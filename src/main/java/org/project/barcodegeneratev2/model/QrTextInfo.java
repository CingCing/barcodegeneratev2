package org.project.barcodegeneratev2.model;

public class QrTextInfo {
	
	private String username;
	private String context;

	public QrTextInfo() {
		
	}
	
	// Không được thay đổi Constructor này,
	// Nó được sử dụng trong Hibernate Query.
	public QrTextInfo(String username, String context) {
	    this.username = username;
	    this.context = context;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	
}
