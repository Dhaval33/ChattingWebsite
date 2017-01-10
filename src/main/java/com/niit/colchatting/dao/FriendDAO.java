package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Friend;

public interface FriendDAO {
	
	
public boolean save(Friend friend);
	
	public boolean update(Friend friend);
	
	public Friend get(String id);
	
	public List<Friend> list();
	
	public Friend isValidFriend(String id , String password);

}



