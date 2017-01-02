package com.niit.colchatting.model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.CLOB;

@Entity
@Table(name="COL_FORUM_COMMENT")
@Component
public class ForumComment extends BaseDomain{
	
	@Id
	private String id;
	
	@Column(name="FORUM_ID")
	private String ForumId;
	
	@Column(name="USER_ID")
	private String UserID;
	
	@Column(name="FORUM_COMMENT")
	private CLOB ForumCom;
	
	@Column(name="COMMENTED_DATE")
	private Date CommentedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForumId() {
		return ForumId;
	}

	public void setForumId(String forumId) {
		ForumId = forumId;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public CLOB getForumCom() {
		return ForumCom;
	}

	public void setForumCom(CLOB forumCom) {
		ForumCom = forumCom;
	}

	public Date getCommentedDate() {
		return CommentedDate;
	}

	public void setCommentedDate(Date commentedDate) {
		CommentedDate = commentedDate;
	}

}
