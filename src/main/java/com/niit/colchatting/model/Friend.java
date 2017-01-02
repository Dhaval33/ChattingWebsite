package com.niit.colchatting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="FRIEND")
@Component
public class Friend extends BaseDomain{
	
	@Column(name="USER_ID")
	private String UserID;
	
	@Column(name="FRIEND_ID")
	private String FriendID;
	
	private String id;
	
	private char Status;
	
	@Column(name="IS_ONLINE")
	private char IsOnline;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getFriendID() {
		return FriendID;
	}

	public void setFriendID(String friendID) {
		FriendID = friendID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public char getIsOnline() {
		return IsOnline;
	}

	public void setIsOnline(char isOnline) {
		IsOnline = isOnline;
	}

}
