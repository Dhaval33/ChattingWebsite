package com.niit.colchatting.model;







import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import oracle.sql.CLOB;

@Entity
@Table(name="COL_JOB")
@Component
public class Job extends BaseDomain{
	
	@Id
	@Column(name="ID")
	@GenericGenerator(name="job1" , strategy ="increment")
	@GeneratedValue(generator="job1")
	private Long JobID;
	
	private String title;
	
	@Column(name="QUALIFICATION")
	private String qualification;
	
	private char status;
	
	private String description;
	
	
	
	@Column(name="DATE_TIME")
	private String DateTime;

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	

	
	public Long getJobID() {
		return JobID;
	}

	public void setJobID(Long jobID) {
		JobID = jobID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	
}
