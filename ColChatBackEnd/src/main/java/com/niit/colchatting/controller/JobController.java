package com.niit.colchatting.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.niit.colchatting.model.JobApplication;

@RestController
public class JobController {

	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	Job job;

	@Autowired
	JobDAO jobDAO;

	@Autowired
	JobApplication jobApplication;

	@Autowired
	HttpSession httpSession;
	
	
	//http://localhost:9999/ColChatBackEnd/getAllJobs
	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET) // $http.get(base_url+
																		// "/getAllJobs/)
	public ResponseEntity<List<Job>> getAllOpenedJobs() {
		logger.debug("--Starting o the method getAllOpenedJobs--");
		List<Job> jobs = jobDAO.getAllOpenedJobs();
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/getMyAppliedJobs
	@RequestMapping(value = "/getMyAppliedJobs", method = RequestMethod.GET)
	public ResponseEntity<List<Job>> getMyAppliedJobs(HttpSession httpSession) {
		logger.debug("---Startng of the method getmyAppliedJobs---");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");
		List<Job> jobs = new ArrayList<Job>();
		if (loggedInUserID == null || loggedInUserID.isEmpty()) {
			job.setErrorCode("404");
			job.setErrorMessage("You have to login to see the applied jobs");
			jobs.add(job);
		} else {
			job.setErrorCode("200");
			job.setErrorMessage("These are your applied Jobs");
			jobs = jobDAO.getMyAppliedJobs(loggedInUserID);
		}

		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/getJobDetails/{jobID}
	@RequestMapping(value = "/getJobDetails/{jobID}", method = RequestMethod.GET)
	public ResponseEntity<Job> getJobDetails(@PathVariable("jobID") Long jobID) {
		logger.debug("---Starting of the method getJobDetails---");
		Job job = jobDAO.getJobDetails(jobID);

		if (job == null) {
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Job not available for this is" + jobID);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/postAJob
	@RequestMapping(value = "/postAJob", method = RequestMethod.POST)
	public ResponseEntity<Job> postAJob(@RequestBody Job job) {
		logger.debug("---Starting of the method postAJob--");
		job.setStatus('V');// V-vacant , F-Filled , P- pending
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		job.setDateTime(dateFormat.format(date));
		
		if (jobDAO.save(job) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Not able to post A job");
			logger.debug("Not able to post a job");
		} 
		
		else
		
		{
			job.setErrorCode("200");
			job.setErrorMessage("Successfully posted the job");
			logger.debug("Posted the job");
		}

		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/applyForJob/{jobID}
	@RequestMapping(value = "/applyForJob/{jobID}", method = RequestMethod.POST)
	public ResponseEntity<JobApplication> applyForJob(@PathVariable("jobID") Long jobID) {
		logger.debug("Starting of the method applyForJob");
		String loggedInUserID = (String) httpSession.getAttribute("loggedInUserID");

		if (loggedInUserID == null || loggedInUserID.isEmpty()) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You have to login to see the applied jobs");
		} else {

			 if(isUserAppliedForTheJob(loggedInUserID , jobID)== false){
				jobApplication.setJobID(jobID);
				jobApplication.setUserID(loggedInUserID);
				jobApplication.setStatus('N');// N-newly applied; , C-> Call for
												// Interview, S- Selected
				
				
				
				jobApplication.setDateApplied(new Date(System.currentTimeMillis()));

				logger.debug("Applied Date : " + jobApplication.getDateApplied());

				if (jobDAO.save(jobApplication)) {
					jobApplication.setErrorCode("200");
					jobApplication.setErrorMessage("You have successfully applied fo the Job :" + jobID);
					logger.debug("Successfully applied for the job");
				}
			}  else {
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage("You already applied for the job" + jobID);
				logger.debug("Not able to apply for the job");
			}

		}
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);

	}

	//http://localhost:9999/ColChatBackEnd/selectUser/{userID}/{jobID}/{reason}
	@RequestMapping(value = "/selectUser/{userID}/{jobID}/{reason}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> selectUser(@PathVariable("userID") String userID,
			@PathVariable("jobID") Long jobID, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method selectUser");
		jobApplication = updateJobApplicationStatus(userID, jobID, 'S', reason);

		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/callForInterview/{userID}/{jobID}/{reason}
	@RequestMapping(value = "/callForInterview/{userID}/{jobID}/{reason}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> callForInterview(@PathVariable("userID") String userID,
			@PathVariable("jobID") Long jobID, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method callForInterview");
		jobApplication = updateJobApplicationStatus(userID, jobID, 'C', reason);

		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/rejectJobApplication/{userID}/{jobID}/{reason}
	@RequestMapping(value = "/rejectJobApplication/{userID}/{jobID}/{reason}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("userID") String userID,
			@PathVariable("jobID") Long jobID, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method rejectJobApplication");
		jobApplication = updateJobApplicationStatus(userID, jobID, 'R', reason);

		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	private JobApplication updateJobApplicationStatus(String userID, Long jobID, char status, String reason) {
		String loggedInUserRole = (String) httpSession.getAttribute("loggedInUserRole");
		logger.debug("Starting of the method updateJobApplicationStatus");

		if (isUserAppliedForTheJob(userID, jobID) == false) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage(userID + " not applied for the job" + jobID);
			return jobApplication;
		}

		
		logger.debug("loggedInUserRole:" + loggedInUserRole);
		if (loggedInUserRole == null || loggedInUserRole.isEmpty()) {
			job.setErrorCode("404");
			job.setErrorMessage("You are not logged in");
			return jobApplication;
		}

		if (!loggedInUserRole.equalsIgnoreCase("Admin")) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You are not Admin, you cannot do this operation");
			return jobApplication;
		}
		jobApplication = jobDAO.getJobApplication(userID, jobID);

		jobApplication.setStatus(status);
		jobApplication.setReason(reason);

		if (jobDAO.updateJobApplication(jobApplication)) {
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("Successfully updated the status as " + status);
			logger.debug("Successfully  updated the status as" + status);
		} else {
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("Not able to update the status " + status);
		}
		return jobApplication;
	}

	private boolean isUserAppliedForTheJob(String userID, Long jobID) {
		if (jobDAO.getJobApplication(userID, jobID) == null) {
			return false;
		}

		return true;
	}

}
