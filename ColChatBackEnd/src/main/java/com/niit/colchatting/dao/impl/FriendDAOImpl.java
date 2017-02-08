package com.niit.colchatting.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colchatting.dao.FriendDAO;
import com.niit.colchatting.model.Friend;

@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	private static final Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory)
	{
		try{
			this.sessionFactory=sessionFactory;
		}
		catch(Exception e){
			log.error("Unable to connect to database");
			e.printStackTrace();
		}
	}
	
	@Transactional
	public List<Friend> getMyFriends(String userId) {
		/*String hql="select friendId from Friend where userId='"+userId+"' and status='A'";
		String hql2="select userId from Friend where friendId='"+userId+"' and status='A'";*/
		String hql="from Friend where userId='"+userId+"' and status='A'";
		String hql2="from Friend where friendId='"+userId+"' and status='A'";
		log.debug("getMyFriends hql= "+hql);
		log.debug("getMyFriends hql2= "+hql2);
        Query query=sessionFactory.openSession().createQuery(hql);
        Query query2=sessionFactory.openSession().createQuery(hql2);
        List<Friend> list=(List<Friend>)query.list();
        List<Friend> list2=(List<Friend>)query2.list();
        list.addAll(list2);
		return list;
	}
    
	@Transactional
	public Friend get(String userId, String friendId) {
		String hql="from Friend where userId='"+userId+"' and friendId='"+friendId+"'";
		log.debug("hql=" +hql);
		Query query=sessionFactory.openSession().createQuery(hql);
		return (Friend) query.uniqueResult();
	}
  
	@Transactional
	public boolean save(Friend friend) {
		try {
			log.debug("friend saving method starting");
			sessionFactory.getCurrentSession().save(friend);
            return true;
 		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
    
	@Transactional
	public boolean update(Friend friend) {
		try {
			log.debug("friend update method starting");
			sessionFactory.getCurrentSession().update(friend);
            return true;
 		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
	@Transactional
	public void delete(String userId, String friendId) {
		Friend friend = new Friend();
        friend.setFriendId(friendId);
        friend.setUserId(userId);
	    sessionFactory.openSession().delete(friend);
	}

	/*@Transactional
	public List<Friend> getNewFriendRequests(String userId) {
				String hql ="select friendId from Friend where userId='"+userId+"' and status='N'";
		log.debug("getNewFriendRequests hql : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>)query.list();
       return list;
	}*/
	
	@Transactional
	public List<Friend> getNewFriendRequestsSendByMe(String userId) {
				String hql ="select friendId from Friend where userId='"+userId+"' and status='N'";
		log.debug("getNewFriendRequestsSendByMe hql : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>)query.list();
       return list;
	}
    
	@Transactional
	public List<Friend> getNewFriendRequests(String friendId) {
				String hql ="from Friend where friendId='"+friendId+"' and status='N'";
		log.debug("getNewFriendRequests hql : " + hql);
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>)query.list();
       return list;
	}
    
	@Transactional
	public void setOnline(String friendId) {
		log.debug("Starting of the method setOnline");
		String hql="UPDATE Friend SET isOnline='Y' where friendId='" +friendId+"'";
		log.debug("hql: " +hql);
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
   
	@Transactional
	public void setOffline(String friendId) {
		log.debug("Starting of the method setOnline");
		String hql="UPDATE Friend SET isOnline='N' where friendId='" +friendId+"'";
		log.debug("hql: " +hql);
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

}
