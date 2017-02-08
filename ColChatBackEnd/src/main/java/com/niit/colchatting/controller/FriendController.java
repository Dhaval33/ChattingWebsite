package com.niit.colchatting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colchatting.dao.FriendDAO;
import com.niit.colchatting.dao.UserDAO;
import com.niit.colchatting.model.Friend;

@RestController
public class FriendController {
	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/hellofriends")
	public String sayHello()
	{
		System.out.println("shailaja");
		return "hello";
	}
	
	@RequestMapping(value="/myFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends()
	{
		logger.debug("Starting of getMyFriends method");
		String loggedInUserID=(String) session.getAttribute("loggedInUserID");
		System.out.println(" abc ="+loggedInUserID);
		List<Friend> myFriends=new ArrayList<Friend>();
		if(loggedInUserID==null){
			friend.setErrorCode("404");
			friend.setErrorMessage("Please login to know your friends");
			myFriends.add(friend);
			return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
			}
		logger.debug("getting friends of: "+loggedInUserID);
		myFriends=friendDAO.getMyFriends(loggedInUserID);
		if(myFriends.isEmpty()){
			logger.debug("Friends does not exist for user: "+loggedInUserID);
			friend.setErrorCode("404");
			friend.setErrorMessage("No friends available for this user");
			myFriends.add(friend);
			
		}
		logger.debug("Ending of getMyFriends method");
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> addFriend(@PathVariable("friendId") String friendId){
		logger.debug("Starting of addFriend method");
		String loggedInUserID=(String) session.getAttribute("loggedInUserID");
        friend.setUserId(loggedInUserID);
        friend.setFriendId(friendId);
        friend.setStatus('N');//N-new friend request
        friend.setIsOnline('N');
        
        if(isUserExist(friendId)==false)//check whether the friend exist in user table or not
        {
        	friend.setErrorCode("404");
        	friend.setErrorMessage("No User exist with this id: "+friendId);
        }
       
        if(friendDAO.get(loggedInUserID, friendId)!=null)//If user had already send a friend request
        {
        	friend.setErrorCode("404");
        	friend.setErrorMessage("You already sent the friend request");
        }
        else
        {
        	friendDAO.save(friend);
        	friend.setErrorCode("200");
        	friend.setErrorMessage("Friend request successfully sended to: "+friendId);
        }
		logger.debug("Ending of addFriend method");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
			}
 
	
	@RequestMapping(value="/rejectFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriend(@PathVariable("friendId") String friendId)
	{
		logger.debug("Starting of rejectFriend method");
		updateRequest(friendId,'R');//R-Reject Friend request
		logger.debug("Ending of rejectFriend method");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
       		
	}
	
	@RequestMapping(value="/acceptFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriend(@PathVariable("friendId") String friendId)
	{
		logger.debug("Starting of acceptFriend method");
		updateRequest(friendId,'A');//A-Accept Friend request
		logger.debug("Ending of acceptFriend method");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
       		
	}
	
	@RequestMapping(value="/unFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") String friendId)
	{
		logger.debug("Starting of acceptFriend method");
		updateRequest(friendId,'U');//U-Unfriend Friend request
		logger.debug("Ending of acceptFriend method");
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
       		
	}
	
	public Friend updateRequest(String friendId,char status){
		logger.debug("Starting of updateRequest method");
		String loggedInUserID=(String) session.getAttribute("loggedInUserID");
		if(isFriendExist(friendId)==false){
			friend.setErrorCode("404");
			friend.setErrorMessage("The friend does not exist");
		}
		if(status=='A'||status=='R'){
			friend=friendDAO.get(friendId, loggedInUserID);
		}
		else{
			friend=friendDAO.get(loggedInUserID, friendId);
		}
		friend.setStatus(status);
		friendDAO.update(friend);
		friend.setErrorCode("200");
		friend.setErrorMessage("Request from= "+friend.getUserId()+" to "+friend.getFriendId()+" has updated to: "+status);
		logger.debug("Ending of updateRequest method");
      return friend;
	}
	
	public boolean isUserExist(String userId)
	{
		if(userDAO.get(userId)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean isFriendExist(String userId)
	{
		if(userDAO.get(userId)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	@RequestMapping(value="/getMyFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequest(){
		logger.debug("Starting of getMyFriendRequest method");
		String loggedInUserID=(String) session.getAttribute("loggedInUserID");
		List<Friend> myFriendRequests=friendDAO.getNewFriendRequests(loggedInUserID);
		logger.debug("Ending of getMyFriendRequest method");
		return new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
	}

}
