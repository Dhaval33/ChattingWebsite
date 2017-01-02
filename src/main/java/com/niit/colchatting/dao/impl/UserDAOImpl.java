package com.niit.colchatting.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colchatting.dao.UserDAO;
import com.niit.colchatting.model.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	public User get(String id) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);

		return user;
	}

	@Transactional
	public List<User> list() {

		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}

	@Transactional
	public User isValidUser(String id, String password) {
		String hql = "from User where id=" + "'" + id + "'" + "and password=" + "'" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		User user = (User) query.uniqueResult();

		return user;
	}

	public boolean save(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			e.getMessage();
			return false;
		}

	}

	public boolean update(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			e.getMessage();
			return false;
		}

	}

}
