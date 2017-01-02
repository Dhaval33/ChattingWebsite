package com.niit.colchatting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.CLOB;

@Entity
@Table(name="COL_JOB")
@Component
public class Job extends BaseDomain{
	
	@Id
	private String id;
	
	private String title;
	
	private String Qualification;
	
	private char status;
	
	private CLOB description;
	
	@Column(name="DATE_TIME")
	private Date DateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public CLOB getDescription() {
		return description;
	}

	public void setDescription(CLOB description) {
		this.description = description;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

}
