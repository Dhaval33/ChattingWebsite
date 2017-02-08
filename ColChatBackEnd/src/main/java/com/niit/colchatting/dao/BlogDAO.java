package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Blog;

public interface BlogDAO {

public List<Blog> getAllBlogs();
	
	public boolean saveBlog(Blog ubObj);
	
	public boolean updateApprove(int blgid, char flag);
		
	public Blog getBlogByID(int blgid);

	public boolean getUpdateLike(int blgid);
	
	public boolean getDelete(int blgid);

	
}
