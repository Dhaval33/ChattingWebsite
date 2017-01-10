package com.niit.colchatting.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colchatting.dao.JobDAO;
import com.niit.colchatting.model.Job;

@RestController
public class JobController {
	
	@Autowired
	Job job;
		
	@Autowired
	JobDAO jobDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
	//http://localhost:9999/ColChatBackEnd/Jobs
		@RequestMapping("/Jobs")
		public ResponseEntity<List<Job>> getAllJobDetails() {

			logger.debug("->->->->calling method listAllJobs");

			List<Job> jobs = jobDAO.list();

			if (jobs.isEmpty()) {

				job.setErrorCode("404");
				job.setErrorMessage("No Jobs are Available");
				jobs.add(job);
			}

			return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
		}

		//http://localhost:9999/ColChatBackEnd/Jobs/{id}
		@RequestMapping("/Jobs/{id}")
		public ResponseEntity<Job> getJobByID(@PathVariable("id") String jobID) {

			job = jobDAO.get(jobID);

			if (job == null) {
				job = new Job();
				job.setErrorCode("404");
				job.setErrorMessage("Job not found with this ID" + jobID);
			}

			return new ResponseEntity<Job>(job, HttpStatus.OK);

		}
		
		//http://localhost:9999/ColChatBackEnd/PostJob/
		@RequestMapping(value = "/PostJob/", method = RequestMethod.POST)
		public ResponseEntity<Job> register(@RequestBody Job job) {

			logger.debug("->->->->calling method Register");
			if (jobDAO.get(job.getId()) == null) {
				logger.debug("->->->->Job is going to create with id:" + job.getId());
				
				
				if (jobDAO.save(job) == false) {
					job.setErrorCode("404");
					job.setErrorMessage("The Job could not be created, please try again");
				} else {
					job.setErrorCode("200");
					job.setErrorMessage("The Job has been Successfully created");
				}

				return new ResponseEntity<Job>(job, HttpStatus.OK);

			}
			logger.debug("->->->->Job already exist with id " + job.getId());
			job.setErrorCode("404");
			job.setErrorMessage("Job already exist with id : " + job.getId());
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		
		//http://localhost:9999/ColChatBackEnd/UpdateJob/
		@RequestMapping(value = "/UpdateJob/", method = RequestMethod.PUT)
		public ResponseEntity<Job> Update(@RequestBody Job job) {
			if (jobDAO.update(job) == false) {
				job.setErrorCode("404");
				job.setErrorMessage("the update is not successful, please try again after some time");
			} else {
				job.setErrorCode("200");
				job.setErrorMessage("Suceessfully updated the information");
			}

			return new ResponseEntity<Job>(job, HttpStatus.OK);

		}
		
		 


}
