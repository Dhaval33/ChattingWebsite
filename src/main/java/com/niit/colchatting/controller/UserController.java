package com.niit.colchatting.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping("/Users")
	public ResponseEntity<List<User>> getAllUserDetails() {
		List<User> users = userDAO.list();

		if (users.isEmpty()) {

			user.setErrorCode("404");
			user.setErrorMessage("No Users are Available");
			users.add(user);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/Users/", method = RequestMethod.POST)
	public ResponseEntity<User> getUserByID(@RequestBody User user) {

		user = userDAO.get(user.getId());

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User not found with this ID" + user.getId());
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	/*@RequestMapping( "/Users/")
	public ResponseEntity<User> getUserByID(@PathVariable("id") String userID {

		user = userDAO.get(userID);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User not found with this ID" + userID));
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}*/
	
	@RequestMapping(value = "/Authenticate/", method = RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User user, HttpSession session) {
		user = userDAO.isValidUser(user.getId(), user.getPassword());

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid Crediantials, please try again");
		}

		else {
			session.setAttribute("loggedInUserID", user.getId());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/Register/", method = RequestMethod.POST)
	public ResponseEntity<User> register(@RequestBody User user) {

		if (userDAO.save(user) == false) {
			user.setErrorCode("404");
			user.setErrorMessage("The Registration could not be successful, please try again");
		} else {
			user.setErrorCode("200");
			user.setErrorMessage("Registration Successfull");
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/Update/", method = RequestMethod.POST)
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

}
