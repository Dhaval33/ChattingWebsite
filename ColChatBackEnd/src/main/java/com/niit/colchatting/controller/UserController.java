package com.niit.colchatting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.colchatting.dao.FriendDAO;
import com.niit.colchatting.dao.UserDAO;
import com.niit.colchatting.model.User;

@RestController
public class UserController {
  
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDAO userDAO;

	@Autowired
	User user;

	@Autowired
	FriendDAO friendDAO;

	//Admin should able to make one of the employee as admin
	
	@RequestMapping("/hello")
	public String sayHello()
	{
		
		return "hello";
	}
	
	@RequestMapping(value = "/makeAdmin/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("userId") String userId) {

		logger.debug("calling the method makeAdmin");
		user = userDAO.get(userId);

		if (user == null) {
			logger.debug("Employee does not exist with the id : " + userId);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK); // 200

		}
		
		if(user.getRole()!="Employee")
		{
			logger.debug("We cannot make thhis user as admin: " + userId);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("We cannot make thhis user as admin: " + userId);
			return new ResponseEntity<User>(user, HttpStatus.OK); // 200
			
		}		
		user.setRole("Admin");
		userDAO.update(user);
		user.setErrorCode("200");
		user.setErrorMessage("Successfully assign Admin role to the employy :" +user.getName());
		logger.debug("Employee role updated as admin successfully " + userId);

		return new ResponseEntity<User>(user, HttpStatus.OK); // 200

	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		logger.debug("->->->->calling method listAllUsers");
		List<User> users = userDAO.list();

		// errorCode :200 :404
		// errorMessage :Success :Not found

		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No users are available");
			users.add(user);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/Register/", method = RequestMethod.POST)
	public ResponseEntity<User> Register(@RequestBody User user) {
		logger.debug("->->->->calling method createUser");
		if (userDAO.get(user.getUserId()) == null) {
			logger.debug("->->->->User is going to create with id:" + user.getUserId());
			user.setIs_online('N');
			user.setStatus('N');
			  if (userDAO.save(user) ==true)
			  {
				  user.setErrorCode("200");
					user.setErrorMessage("Thank you  for registration. You have successfully registered as " + user.getRole());
			  }
			  else
			  {
				  user.setErrorCode("404");
					user.setErrorMessage("Could not complete the operatin please contact Admin");
				  
			  }
			 		return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		logger.debug("->->->->User already exist with id " + user.getUserId());
		user.setErrorCode("404");
		user.setErrorMessage("User already exist with id : " + user.getUserId());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/Update/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		logger.debug("->->->->calling method updateUser");
		if (userDAO.get(user.getUserId()) == null) {
			logger.debug("->->->->User does not exist with id " + user.getUserId());
			user = new User(); //To avoid NLP - NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist with id " + user.getUserId());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else{
			/*user.setIsOnline('Y');
			user.setStatus('A');*/
		userDAO.update(user);
		logger.debug("->->->->User updated successfully");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


	// http://localhost:9999/ColChatBackEnd/user/abbas
	@RequestMapping(value = "/userDetails/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") String userId) {
		logger.debug("->->calling method getUser");
		logger.debug("->->userId->->" + userId);
		User user = userDAO.get(userId);
		if (user == null) {
			logger.debug("->->->-> User does not exist wiht userId" + userId);
			user = new User(); //To avoid NLP - NullPointerException
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		logger.debug("->->->-> User exist wiht userId" + userId);
		logger.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/accept/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("userId") String userId) {
		logger.debug("Starting of the method accept" +userId);

		user = updateStatus(userId, 'A', "");
		logger.debug("Ending of the method accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/reject/{userId}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("userId") String userId, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method reject");

		user = updateStatus(userId, 'R', reason);
		logger.debug("Ending of the method reject");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	private User updateStatus(String userId, char status, String reason) {
		logger.debug("Starting of the method updateStatus");

		logger.debug("status: " + status);
		user = userDAO.get(userId);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("UserID does not exist.Could not update the status to " + status);
		} else {

			user.setStatus(status);
			user.setReason(reason);
			
			userDAO.update(user);
			
			user.setErrorCode("200");
			user.setErrorMessage("Updated the status successfully");
		}
		logger.debug("Ending of the method updateStatus");
		return user;

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session) {
		logger.debug("->->calling method myProfile");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		User user = userDAO.get(loggedInUserID);
		if (user == null) {
			logger.debug("->->->-> User does not exist wiht id" + loggedInUserID);
			user = new User(); // It does not mean that we are inserting new row
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		logger.debug("->->->-> User exist with id" + loggedInUserID);
		logger.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// http://localhost:9999/ColChatBackEnd/user/authenticate/
	/*
	 * { "id": "alm1", "password": "alm1" }
	 */
	@RequestMapping(value = "/Authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user, HttpSession session) {
		logger.debug("->->->->calling method authenticate");
		user = userDAO.authenticate(user.getUserId(), user.getPassword());
			
		if (user == null) {
			user = new User(); // Do wee need to create new user?
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Credentials.  Please enter valid credentials");
			logger.debug("->->->->In Valid Credentials");

		} else

		{
			if(user.getStatus()=='N'){
				user.setErrorCode("404");
				user.setErrorMessage("Your Registration  is still pending because you registration status is still showing as " +user.getStatus());				
			}
			else
			{
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");
			user.setIs_online('Y');
			logger.debug("->->->->Valid Credentials");
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserID", user.getUserId());
			session.setAttribute("loggedInUserRole", user.getRole());
			
			friendDAO.setOnline(user.getUserId());
			userDAO.setOnline(user.getUserId());
			}
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		logger.debug("->->->->calling method logout");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		friendDAO.setOffline(loggedInUserID);
		userDAO.setOffLine(loggedInUserID);

		session.invalidate();

		user.setErrorCode("200");
		user.setErrorMessage("You have successfully logged");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	};


}
