package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Job;

public interface JobDAO {
	
	public boolean save(Job job);
	
	public boolean update(Job job);
	
	public Job get(String id);
	
	public List<Job> list();

}
