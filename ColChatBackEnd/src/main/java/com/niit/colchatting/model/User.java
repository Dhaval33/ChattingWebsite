package com.niit.colchatting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.stereotype.Component;

@Entity
@Table(name = "USER_DETAILS")
@Component
public class User extends BaseDomain{
	
	@Id
	@Column(name="USERID")
	private String  userId;
	
	

	@Column(name="NAME")
	private String Name;
	
	@Column(name="MAIL")
	private String Mail;
	
	@Column(name="PASSWORD")
	private String Password;
	
	@Column(name="ADDRESS")
	private String Address;
	
	@Column(name="CONTACT")
	private String mobile;
	
	@Column(name="ROLE")
	private String Role;
	
	@Column(name="IS_ONLINE")
	private char Is_online;
	
	@Column(name="REASON")
	private String Reason;
	
	@Column(name="STATUS")
	private char Status;
	
	
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	

	
	

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public char getIs_online() {
		return Is_online;
	}

	public void setIs_online(char is_online) {
		Is_online = is_online;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	
	
	
	
	

}
