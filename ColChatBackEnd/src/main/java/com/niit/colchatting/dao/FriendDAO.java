package com.niit.colchatting.dao;

import java.util.List;

import com.niit.colchatting.model.Friend;

public interface FriendDAO {
          public List<Friend> getMyFriends(String userId);
          public Friend get(String userId, String friendId);
          //if u want to get all details of you friend
         //You can use get(userId) of UserDao interface
          public boolean save(Friend friend);
          public boolean update(Friend friend);
          public void delete(String userId, String friendId);
          public List<Friend> getNewFriendRequests(String friendId);
          public List<Friend> getNewFriendRequestsSendByMe(String userId);
          public void setOnline(String friendId);
          public void setOffline(String friendId);

}
