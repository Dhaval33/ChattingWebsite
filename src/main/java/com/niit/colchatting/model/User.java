package com.niit.colchatting.model;

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
	
	private String Name;
	
	private String Email;
	
	private String Password;
	
	@Transient
	private String ConfirmPassword;
	
	private String Address;
	
	private String ContactNo;
	
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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
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
