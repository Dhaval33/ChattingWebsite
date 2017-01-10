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


import com.niit.colchatting.dao.UserDAO;
import com.niit.colchatting.model.User;

@RestController
public class UserController {

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;

	//@Autowired
	//FriendDAO friendDAO;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/hello")

	public String sayHello() {
		return "Hello";
	}

	
	//http://localhost:9999/ColChatBackEnd/makeAdmin/{id}
	@RequestMapping(value = "/makeAdmin/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("id") String empID) {

		logger.debug("calling the method makeAdmin");
		user = userDAO.get(empID);

		if (user == null) {
			logger.debug("Employee does not exist with the id : " + empID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Employee does not exist");
			return new ResponseEntity<User>(user, HttpStatus.OK); // 200

		}

		if (user.getRole() != 'E') {
			logger.debug("We cannot make this user as Admin:" + empID);
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("This User does not have access rights  to become Admin");

		}
		user.setRole('A');
		userDAO.update(user);
		user.setErrorCode("200");
		user.setErrorMessage("Successfully assign Admin role to the employee :" + user.getName());
		logger.debug("Employee role updated as admin successfully " + empID);

		return new ResponseEntity<User>(user, HttpStatus.OK); // 200

	}

	//http://localhost:9999/ColChatBackEnd/Users
	@RequestMapping("/Users")
	public ResponseEntity<List<User>> getAllUserDetails() {

		logger.debug("->->->->calling method listAllUsers");

		List<User> users = userDAO.list();

		if (users.isEmpty()) {

			user.setErrorCode("404");
			user.setErrorMessage("No Users are Available");
			users.add(user);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//http://localhost:9999/ColChatBackEnd/Users/{id}
	@RequestMapping("/Users/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String userID) {

		user = userDAO.get(userID);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User not found with this ID" + userID);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	//http://localhost:9999/ColChatBackEnd/Authenticate
	@RequestMapping(value = "/Authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session) {
		logger.debug("->->->->calling method authenticate");
		user = userDAO.isValidUser(user.getId(), user.getPassword());

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Crediantials, please try again");
		}

		else {
			user.setErrorCode("200");
			user.setErrorMessage("You have successfully logged in.");
			user.setIs_online('Y');
			logger.debug("->->->->Valid Credentials");
			session.setAttribute("loggedInUser", user);
			session.setAttribute("loggedInUserID", user.getId());

			// friendDAO.setOnline(user.getId());
			userDAO.setOnline(user.getId());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//http://localhost:9999/ColChatBackEnd/Register/
	@RequestMapping(value = "/Register/", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user) {

		logger.debug("->->->->calling method Register");
		if (userDAO.get(user.getId()) == null) {
			logger.debug("->->->->User is going to create with id:" + user.getId());
			logger.debug("->->->->User is going to create with name:" + user.getName());
			user.setIs_online('N');
			user.setStatus('N');

			if (userDAO.save(user) == false) {
				user.setErrorCode("404");
				user.setErrorMessage("The Registration could not be successful, please try again");
			} else {
				user.setErrorCode("200");
				user.setErrorMessage("Registration Successfull");
			}

			return new ResponseEntity<User>(user, HttpStatus.OK);

		}
		logger.debug("->->->->User already exist with id " + user.getId());
		user.setErrorCode("404");
		user.setErrorMessage("User already exist with id : " + user.getId());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/Update/
	@RequestMapping(value = "/Update/", method = RequestMethod.PUT)
	public ResponseEntity<User> Update(@RequestBody User user) {
		if (userDAO.update(user) == false) {
			user.setErrorCode("404");
			user.setErrorMessage("the update is not successful, please try again after some time");
		} else {
			user.setErrorCode("200");
			user.setErrorMessage("Suceessfully updated the information");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	//http://localhost:9999/ColChatBackEnd/myProfile
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

	//http://localhost:9999/ColChatBackEnd/accept/{id}
	@RequestMapping(value = "/accept/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("id") String id) {
		logger.debug("Starting of the method accept");

		user = updateStatus(id, 'A', "");
		logger.debug("Ending of the method accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
    
	//http://localhost:9999/ColChatBackEnd/reject/{id}/{reason}
	@RequestMapping(value = "/reject/{id}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("id") String id, @PathVariable("reason") String reason) {
		logger.debug("Starting of the method reject");

		user = updateStatus(id, 'R', reason);
		logger.debug("Ending of the method reject");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	private User updateStatus(String id, char status, String reason) {
		logger.debug("Starting of the method updateStatus");

		logger.debug("status: " + status);
		user = userDAO.get(id);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Could not update the status to " + status);
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
	
	//http://localhost:9999/ColChatBackEnd/user/logout
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(HttpSession session) {
		logger.debug("->->->->calling method logout");
		String loggedInUserID = (String) session.getAttribute("loggedInUserID");
		// friendDAO.setOffLine(loggedInUserID);
		userDAO.setOffline(loggedInUserID);

		session.invalidate();

		user.setErrorCode("200");
		user.setErrorMessage("You have successfully loggedout");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	};

}
