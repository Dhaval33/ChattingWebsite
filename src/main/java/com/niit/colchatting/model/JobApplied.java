package com.niit.colchatting.model;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="COL_JOB_APPLIED")
@Component
public class JobApplied extends BaseDomain{
	
	@Id
	private String id;
	
	private String UserID;
	
	private String JobId;
	
	private Date DateApplied;
	
	private char Status;
	
	private String Reason;

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

	public String getJobId() {
		return JobId;
	}

	public void setJobId(String jobId) {
		JobId = jobId;
	}

	public Date getDateApplied() {
		return DateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		DateApplied = dateApplied;
	}

	public char getStatus() {
		return Status;
	}

	public void setStatus(char status) {
		Status = status;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

}
