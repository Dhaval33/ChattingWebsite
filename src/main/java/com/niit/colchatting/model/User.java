package com.niit.colchatting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "USER_DETAILS")
@Component
public class User extends BaseDomain{
	
	@Id
	private String id;
	
	@Column(name="NAME")
	private String Name;
	
	
	private String Mail;
	
	private String Password;
	
	@Transient
	private String ConfirmPassword;
	
	private String Address;
	
	private String Contact;
	
	private char Role;
	
	private char Is_online;
	
	private String Reason;
	
	private char Status;
	
	
	

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

	

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public char getRole() {
		return Role;
	}

	public void setRole(char role) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	

}
