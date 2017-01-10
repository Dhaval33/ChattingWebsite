package com.niit.colchatting.controller;

import java.util.List;

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

@RestController
public class BlogController {
	
	@Autowired
	Blog blog;
		
	@Autowired
	BlogDAO blogDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	//http://localhost:9999/ColChatBackEnd/Blogs
		@RequestMapping("/Blogs")
		public ResponseEntity<List<Blog>> getAllBlogDetails() {

			logger.debug("->->->->calling method listAllBlogs");

			List<Blog> blogs = blogDAO.list();

			if (blogs.isEmpty()) {

				blog.setErrorCode("404");
				blog.setErrorMessage("No Blogs are Available");
				blogs.add(blog);
			}

			return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
		}

		//http://localhost:9999/ColChatBackEnd/Blogs/{id}
		@RequestMapping("/Blogs/{id}")
		public ResponseEntity<Blog> getBlogByID(@PathVariable("id") String blogID) {

			blog = blogDAO.get(blogID);

			if (blog == null) {
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Blog not found with this ID" + blogID);
			}

			return new ResponseEntity<Blog>(blog, HttpStatus.OK);

		}
		
		//http://localhost:9999/ColChatBackEnd/CreateBlog/
		@RequestMapping(value = "/CreateBlog/", method = RequestMethod.POST)
		public ResponseEntity<Blog> register(@RequestBody Blog blog) {

			logger.debug("->->->->calling method Register");
			if (blogDAO.get(blog.getId()) == null) {
				logger.debug("->->->->Blog is going to create with id:" + blog.getId());
				
				
				if (blogDAO.save(blog) == false) {
					blog.setErrorCode("404");
					blog.setErrorMessage("The Blog could not be created, please try again");
				} else {
					blog.setErrorCode("200");
					blog.setErrorMessage("The Blog has been Successfully created");
				}

				return new ResponseEntity<Blog>(blog, HttpStatus.OK);

			}
			logger.debug("->->->->Blog already exist with id " + blog.getId());
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog already exist with id : " + blog.getId());
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		
		//http://localhost:9999/ColChatBackEnd/UpdateBlog/
		@RequestMapping(value = "/UpdateBlog/", method = RequestMethod.PUT)
		public ResponseEntity<Blog> Update(@RequestBody Blog blog) {
			if (blogDAO.update(blog) == false) {
				blog.setErrorCode("404");
				blog.setErrorMessage("the update is not successful, please try again after some time");
			} else {
				blog.setErrorCode("200");
				blog.setErrorMessage("Suceessfully updated the information");
			}

			return new ResponseEntity<Blog>(blog, HttpStatus.OK);

		}
		
		//http://localhost:9999/ColChatBackEnd/acceptblog/{id}
		@RequestMapping(value = "/acceptblog/{id}", method = RequestMethod.GET)
		public ResponseEntity<Blog> accept(@PathVariable("id") String id) {
			logger.debug("Starting of the method accept");

			blog = updateStatus(id, 'A', "");
			logger.debug("Ending of the method accept");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);

		}
	    
		//http://localhost:9999/ColChatBackEnd/rejectblog/{id}/{reason}
		@RequestMapping(value = "/rejectblog/{id}/{reason}", method = RequestMethod.GET)
		public ResponseEntity<Blog> reject(@PathVariable("id") String id, @PathVariable("reason") String reason) {
			logger.debug("Starting of the method reject");

			blog = updateStatus(id, 'R', reason);
			logger.debug("Ending of the method reject");
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);

		}

		private Blog updateStatus(String id, char status, String reason) {
			logger.debug("Starting of the method updateStatus");

			logger.debug("status: " + status);
			blog = blogDAO.get(id);

			if (blog == null) {
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Could not update the status to " + status);
			} else {

				blog.setStatus(status);
				blog.setReason(reason);

				blogDAO.update(blog);

				blog.setErrorCode("200");
				blog.setErrorMessage("Updated the status successfully");
			}
			logger.debug("Ending of the method updateStatus");
			return blog;

		}


}
