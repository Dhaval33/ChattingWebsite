package com.niit.colchatting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {
	
	private static final Logger logger =  LoggerFactory.getLogger(IndexController.class);
	
	
	@RequestMapping("/")
	public String getIndexPage(){
		
		logger.debug("Starting of the method Index Page");
		
		
		return "index";
	}

}
