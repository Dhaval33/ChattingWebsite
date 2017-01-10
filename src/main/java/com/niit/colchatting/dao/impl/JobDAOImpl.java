package com.niit.colchatting.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colchatting.controller.JobController;
import com.niit.colchatting.dao.JobDAO;
import com.niit.colchatting.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {
	
	private static final Logger log = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory){
		
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean save(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			return false;
		}
		
	}

	@Transactional
	public boolean update(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			return false;
		}
		
	}

	@Transactional
	public Job get(String id) {
		
		Job job = (Job)sessionFactory.getCurrentSession().get(Job.class, id);
		
		return job;
	}

	@Transactional
	public List<Job> list() {
		
        String hql= "from Job";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}
	
	
	
	

}
