package com.niit.colchatting.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.niit.colchatting.dao.BlogDAO;
import com.niit.colchatting.model.Blog;
import com.niit.colchatting.model.User;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO service;
	
	@Autowired
	User user;
	
	private static final Logger log = LoggerFactory.getLogger(BlogController.class);
	

	//http://localhost:9999/ColChatBackEnd/adduserblog/{userID}

	@RequestMapping(value = "/adduserblog/", method = RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog ublog, HttpSession session)
	{
		log.debug("calling => createBlog() method");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		ublog.setBlogcreatedate(dateFormat.format(date));
		ublog.setLikes(0);
		ublog.setApprove('N');
		ublog.setUserID((String)session.getAttribute("loggedInUserID"));
		
		
		boolean flag = service.saveBlog(ublog);
		
		if(!flag){
			log.debug("error in calling => createUserType() method");
			return new ResponseEntity<Blog>(ublog, HttpStatus.CONFLICT);
		}
		else
		{
			log.debug("Update user blog");
			return new ResponseEntity<Blog>(ublog, HttpStatus.OK);
		}
	}
	
	
	//http://localhost:9999/ColChatBackEnd/alluserblog
	@RequestMapping(value = "/alluserblog", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllBlog()	{

		log.debug("calling => listAllUserType() method");
		List<Blog> lsts = service.getAllBlogs();
		if(lsts.isEmpty()){
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(lsts, HttpStatus.OK);
	}
	
	//http://localhost:9999/ColChatBackEnd/getblogbyid/{blogid}
	@RequestMapping(value = "/getblogbyid/{blogid}", method = RequestMethod.GET)
	public ResponseEntity<Blog> getblogbyid(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getblogbyid() method");
		Blog userblog = service.getBlogByID(blogid);
		
		if(userblog==null){
			return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}

	//http://localhost:9999/ColChatBackEnd/getapproveblog/{blogid}
	@RequestMapping(value = "/getapproveblog/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getapproveblog(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapproveblog() method");
		boolean flag = service.updateApprove(blogid, 'Y');
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = service.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	

	//http://localhost:9999/ColChatBackEnd/getdeleteblog/{blogid}
	@RequestMapping(value = "/getdeleteblog/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getdeleteblog(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapprovegetdeleteblogblog() method");
		boolean flag = service.getDelete(blogid);
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = service.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	

	//http://localhost:9999/ColChatBackEnd/getupdatelike/{blogid}
	@RequestMapping(value = "/getupdatelike/{blogid}", method = RequestMethod.POST)
	public ResponseEntity<Blog> getupdatelike(@PathVariable("blogid") int blogid)	{

		log.debug("calling => getapproveblog() method");
		boolean flag = service.getUpdateLike(blogid);
		if(!flag){
			return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
		}
		Blog userblog = service.getBlogByID(blogid);
		return new ResponseEntity<Blog>(userblog, HttpStatus.OK);
	}	
}


