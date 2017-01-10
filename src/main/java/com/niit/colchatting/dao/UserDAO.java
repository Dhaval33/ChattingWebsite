package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.User;

public interface UserDAO {
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public User get(String id);
	
	public List<User> list();
	
	public User isValidUser(String id , String password);
	
	public void setOnline(String UserID);
	
	public void setOffline(String UserID);

}
