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

import com.niit.colchatting.dao.UserDAO;
import com.niit.colchatting.model.User;


@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	  SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		
		 try {
			this.sessionFactory=sessionFactory;
		} catch (Exception e) {
			log.error(" Unable to connect to db");
			e.printStackTrace();
		}
	}
	
	@Transactional
	public boolean save(User user) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		}
		catch(HibernateException e){
			e.printStackTrace();
		return false;
		}
	}
    
	@Transactional
	public boolean update(User user) {
		try{
				
	   		sessionFactory.getCurrentSession().update(user);
	   			   	
	   				return true;
	   	}
	   	catch(HibernateException e){
	   		e.printStackTrace();
	   	return false;
	   	}
	}
    
	@Transactional
	public boolean delete(String userId) {
		User DeleteUser=new User();
		   DeleteUser.setUserId(userId);;
		try{
			sessionFactory.getCurrentSession().delete(DeleteUser);
			return true;
			}
		catch(Exception e)
		{
			e.printStackTrace();
		return false;
			}
	}
    
	@Transactional
	public User get(String userId) {
		String H_Query="from User where userId="+"'"+userId+"'"; 
		return getUserName(H_Query);
	}
    
	@Transactional
	public List<User> list() {
		String hql="from User";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return  query.list();
	}
    
	
	private User getUserName(String H_Query) {
		 User user=null;
			Query query=sessionFactory.getCurrentSession().createQuery(H_Query);
			@SuppressWarnings("unchecked")
			List<User> list=(List<User>)query.list();
			if(list != null && !list.isEmpty())
			{
				user=(User)list.get(0);
			}
			return user;
	}
    
	@Transactional
	public User get(String id, String password) {
		log.debug("->->Starting of the method get");
		log.debug("->->id : " + id);
		log.debug("->->password : " + password);
		String hql = "from User where id=" + "'"+ id+"'  and password = "
				+"'" + password + "'";
	   return getUserName(hql);
	}
     
	@Transactional
	public User authenticate(String userId, String password) {
		log.debug("->->Starting of the method isValidUserDetails");
		String hql = "from User where userId= '" + userId + "' and  password ='" + password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		
		return null;
	}
     
	@Transactional
	public void setOnline(String userId) {
		log.debug("Starting of the metnod setOnline");
		String hql =" UPDATE User SET is_Online = 'Y' where userId='" +  userId + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOnline");
		
	}
     
	@Transactional
	public void setOffLine(String userId) {
		log.debug("Starting of the metnod setOffLine");
		String hql =" UPDATE User	SET is_Online = 'N' where userId='" +  userId + "'" ;
		  log.debug("hql: " + hql);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		log.debug("Ending of the metnod setOffLine");
			}
	
	


}
