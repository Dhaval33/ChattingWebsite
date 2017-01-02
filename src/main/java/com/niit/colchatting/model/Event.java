package com.niit.colchatting.model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import oracle.sql.CLOB;

@Entity
@Table(name="COL_EVENT")
@Component
public class Event extends BaseDomain {
	
	@Id
	private String id;
	
	private String name;
	
	@Column(name="DATE_TIME")
	private Date DateTime;
	
	private String Venue;
	
	private CLOB Description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}

	public String getVenue() {
		return Venue;
	}

	public void setVenue(String venue) {
		Venue = venue;
	}

	public CLOB getDescription() {
		return Description;
	}

	public void setDescription(CLOB description) {
		Description = description;
	}

}
