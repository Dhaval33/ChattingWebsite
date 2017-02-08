package com.niit.colchatting.controller;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.colchatting.model.User;
import com.niit.colchatting.util.FileUtil;


@RestController
public class FileUploadController {
	/*@Autowired
	private FileUploadDAO fileUploadDao;*/
	
	@Autowired
	User user;
	
	@Autowired
	HttpSession session;
	
	private String path="D:\\Desgin project\\Collaboration Website\\ColChatBackEnd\\src\\main\\webapp\\Resources\\images";
	
	
	private static final Logger log=LoggerFactory.getLogger(FileUploadController.class);
		@RequestMapping(value="/doUpload")
		public void uploadFile(@RequestParam("image") MultipartFile image){
			log.debug("  upload start  ");
			String loggedInUserID=(String) session.getAttribute("loggedInUserID");
			FileUtil.upload(path,image,loggedInUserID +".jpg");
			log.debug("  upload end  ");
			
		}
	
}
