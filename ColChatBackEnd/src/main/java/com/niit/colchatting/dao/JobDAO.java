package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Job;
import com.niit.colchatting.model.JobApplication;

public interface JobDAO {
	
	
	
	public Job getJobDetails(Long jobID);
	
	public boolean save( Job job);
	
	public boolean updateJob(Job job);
	
	public List<Job> getAllOpenedJobs();
	
	public boolean save(JobApplication jobApplied);
	
	public boolean updateJobApplication(JobApplication jobApplication);
	
	public JobApplication getJobApplication(String userID, Long jobID);
	
	public List<Job> getMyAppliedJobs(String userID);
	
	
	
	
	
	//public JobApplication getJobApplication(Long jobID);

}
