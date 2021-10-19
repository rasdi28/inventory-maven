package com.sdd.inventory.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Muser {
	
	private Integer muserpk;
	private String name;
	private String email;
	private String username;
	private String password;
	private Integer role;
	
	
	@Id
	public Integer getMuserpk() {
		return muserpk;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Integer getRole() {
		return role;
	}
	public void setMuserpk(Integer muserpk) {
		this.muserpk = muserpk;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	
	
}
