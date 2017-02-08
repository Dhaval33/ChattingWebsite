package com.niit.colchatting.model;





import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;



@Entity
@Table(name="COL_BLOG")
@Component
public class Blog extends BaseDomain{
	
	@Id
	@GenericGenerator(name="Blog" , strategy ="increment")
	@GeneratedValue(generator="Blog")
	@Column(name="ID")
	private int blogid;
	
	@Column(name="TITLE")
	private String blogtitle;
	
	
	
    @Column(name="USER_ID")
	private String UserID;
	
	@Column(name="DATE_TIME")
	private String blogcreatedate;

	
	@Column(name="STATUS")
	private char approve;

	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LIKES")
	private int likes;

	

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public String getBlogtitle() {
		return blogtitle;
	}

	public void setBlogtitle(String blogtitle) {
		this.blogtitle = blogtitle;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getBlogcreatedate() {
		return blogcreatedate;
	}

	public void setBlogcreatedate(String blogcreatedate) {
		this.blogcreatedate = blogcreatedate;
	}

	

	

	public char getApprove() {
		return approve;
	}

	public void setApprove(char approve) {
		this.approve = approve;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}


	
	

}
