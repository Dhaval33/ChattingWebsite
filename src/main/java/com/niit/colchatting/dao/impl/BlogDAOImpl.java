package com.niit.colchatting.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.colchatting.dao.BlogDAO;
import com.niit.colchatting.model.Blog;


@Repository("blogDAO")
public class BlogDAOImpl  implements BlogDAO{
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory ){
		
		this.sessionFactory = sessionFactory;
		
	}

	@Transactional
	public boolean save(Blog blog) {
		try{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		
		return false;
		}
	}
		

	@Transactional
	public boolean update(Blog blog) {
		try{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
		
		return false;
		}
	}

	@Transactional
	public Blog get(String id) {
		Blog blog =(Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
		
		return blog;
	}

	@Transactional
	public List<Blog> list() {
		String hql = "from Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
