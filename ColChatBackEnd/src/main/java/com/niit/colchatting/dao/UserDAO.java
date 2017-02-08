package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.User;

public interface UserDAO {
	     public boolean save(User user);
		 public boolean update(User user);
		 public boolean delete(String userId);
		 public User get(String userId);
		 public List<User> list();
		 public User get(String userId,String password);
		 public User authenticate(String userId, String password);
	     public void setOnline(String userId);
	     public void setOffLine(String userId);

}