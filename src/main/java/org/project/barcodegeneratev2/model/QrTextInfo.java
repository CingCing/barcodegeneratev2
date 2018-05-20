package org.project.barcodegeneratev2.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class QrTextInfo {
	
	private String username;
	private String context;

	// Upload file.
    private CommonsMultipartFile fileData;
	
	public QrTextInfo() {
		
	}
	
	// Không được thay đổi Constructor này,
	// Nó được sử dụng trong Hibernate Query.
	public QrTextInfo(String username, String context) {
	    this.username = username;
	    this.context = context;
	}
	
	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
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
