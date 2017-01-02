package com.niit.colchatting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

@Entity
@Table(name="COL_FORUM")
@Component
public class Forum extends BaseDomain{
	
	@Id
	private String id;
	
	@Column(name="USER_ID")
	private String UserID;
	
	private CLOB message;
	
	@Column(name="CREATED_DATE")
	private  TIMESTAMP CreatedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public CLOB getMessage() {
		return message;
	}

	public void setMessage(CLOB message) {
		this.message = message;
	}

	public TIMESTAMP getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(TIMESTAMP createdDate) {
		CreatedDate = createdDate;
	}

}
