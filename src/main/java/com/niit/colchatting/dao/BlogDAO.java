package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Blog;

public interface BlogDAO {

	
	public boolean save(Blog blog);
	
	public boolean update(Blog blog);
	
	public Blog get(String id);
	
	public List<Blog> list();
}
