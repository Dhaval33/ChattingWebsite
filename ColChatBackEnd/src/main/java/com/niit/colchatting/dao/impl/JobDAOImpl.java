package com.niit.colchatting.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colchatting.dao.JobDAO;
import com.niit.colchatting.model.Job;
import com.niit.colchatting.model.JobApplication;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	private static final Logger log = LoggerFactory.getLogger(JobDAOImpl.class);

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {

		try {
			this.sessionFactory = sessionFactory;
		} catch (Exception e) {
			log.error("Unable to connect with to db");
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Job> getAllOpenedJobs() {
		
		log.debug("Startiong of the method getAllOpenedJobs");
		String hql = "from Job where status ='" + "V'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
		
	}

	@Transactional
	public Job getJobDetails(Long id) {
		log.debug("Startiong of the method getJobDetails");
		
		Job job = (Job) sessionFactory.getCurrentSession().get(Job.class, id);

		return job;
	}

	@Transactional
	public JobApplication getJobApplication(String userID, Long jobID) {
		log.debug("Startoing of the method getJobApplication");

		String hql = "from JobApplication where UserID ='" + userID +  "'  and JobID ='" + jobID + "'";
		log.debug("hql" +hql);
		return (JobApplication) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Transactional
	public JobApplication getJobApplication(Long jobID) {
		log.debug("Starting of the method getJobApplication");
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, jobID);
	}

	@Transactional
	public boolean updateJob(Job job) {
		log.debug("Starting of the method updateJob");
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean updateJobApplication(JobApplication jobApplication) {
		log.debug("Startiong of the method updateJobApplication");
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	private long getMaxId() {
		log.debug("Starting of the method getMaxId");

		Long maxID = 100L;

		try {
			String hql = "select max(id) from Job";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxID = (Long) query.uniqueResult();
		} catch (HibernateException e) {
			log.debug("It seems this is the first record. setting initial id to 100:");
			maxID = 100L;
			e.printStackTrace();
		}
		log.debug("Max id :" + maxID);
		return maxID + 1;
	}

	@Transactional
	public boolean save(JobApplication jobApplied) {
		log.debug("Startiong of the method save JobApplication");
		try {
			jobApplied.getId();
			sessionFactory.getCurrentSession().save(jobApplied);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public boolean save(Job job) {
		log.debug("Starting of the method save Job ");
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Transactional
	public List<Job> getMyAppliedJobs(String userID) {
		log.debug("Startiong of the method getMyAppliedJobs");
		System.out.println("Dhaval");
		System.out.println(userID);
		String hql = "from JobApplication where UserID = '" + userID +  "'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
