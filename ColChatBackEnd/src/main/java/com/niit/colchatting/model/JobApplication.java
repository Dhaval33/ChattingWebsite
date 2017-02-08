package com.niit.colchatting.model;





import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="COL_JOB_APPLIED")
@Component
public class JobApplication extends BaseDomain{
	
	
	
	
	@Id
	@GenericGenerator(name="job" , strategy ="increment")
	@GeneratedValue(generator="job")
	private Long id;
	
	
	@Column(name="JOB_ID")
	private Long JobID;
	
	
	@Column(name="USER_ID")
	private String UserID;
	
	
	
	@Column(name="DATE_APPLIED")
	private Date DateApplied;
	
	@Column(name="STATUS")
	private char Status;
	
	@Column(name="REASON")
	private String Reason;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	

	

	public Long getJobID() {
		return JobID;
	}

	public void setJobID(Long jobID) {
		JobID = jobID;
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

