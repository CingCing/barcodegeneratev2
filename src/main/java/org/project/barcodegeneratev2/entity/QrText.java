package org.project.barcodegeneratev2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qrText")
public class QrText implements Serializable {

	private static final long serialVersionUID = 8261540113948536717L;

	private String username;
	private String context;
	
	@Id
	@Column(name = "username", length = 50, nullable = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "context", length = 255 , nullable = true)
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
}
