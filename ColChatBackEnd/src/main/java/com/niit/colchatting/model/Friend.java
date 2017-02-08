package com.niit.colchatting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="COL_FRIEND")
@Component
public class Friend extends BaseDomain{

	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="FRIEND_ID")
	private String friendId;
	
	@GenericGenerator(name="friend" , strategy="increment")
	@GeneratedValue(generator="friend")
 	@Column(name="ID")
	@Id
	private int id;
	
	@Column(name="STATUS")
	private char status;
	
	@Column(name="IS_ONLINE")
	private char isOnline;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	
}
