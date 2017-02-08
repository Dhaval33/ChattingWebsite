'use strict';

app.
       controller(	'JobController', 
    		              [	'$scope', 
    		            	'JobService', 
    		            	'$location',
    		            	'$rootScope',
						           function($scope, JobService, $location, $rootScope) {
							console.log("JobController...")
							var self = this;
							self.jobs = {
									id : '',
									title : '',
									description : '',
									dateTime : '',
									qualification : '',
									status : '',
									userID : '',
									
									
								    errorCode : '',	
								    errorMessage : ''
								    	
							};
							
							
							
							self.jobs = [];
							
							self.jobapplications=[];
							

							
							self.applyForJob = applyForJob
							
							function applyForJob(id){
								console.log("apply for job");
								var currentUser = $rootScope.currentUser
								console.log("currentUser.userId:" + currentUser.userId)
								// if(currentUser) -> not null, not empty and
								// undefined
								
								if(typeof currentUser.userId == 'undefined')
									{
									alert("please login to apply for the job")
									console.log("User is not logged in can not apply for the job")
									return
									}
							 console.log("->userID:" + currentUser.id
									 + "applying for the job:" +id)
									 
									 JobService
									           .applyForJob(id)
									           .then(
									        		   function(d){
									        			   self.job =d;
									        			   alert("You have successfully applied for the job")
									        			   
									        		   },
									        		   function(errResponse){
									        			   console
									        			         .error("Error while applying for Job request")
									        		   });
									 
									 
							
							}
							
							self.getMyAppliedJobs = function(){
								console.log("calling the method getMyAppliedJobs");
								JobService
								       .getMyAppliedJobs()
								       .then(function (d){
								    	   self.jobapplications =d;
								    	  console.log(d) 
								       }, 
								       function(errResponse){
								    	   console.error("Error while fetching the jobs");
								       });
								
							};
							
							self.getMyAppliedJobs();

							
							self.rejectJobApplication = function(userID){
								var jobID = $rootScope.selectedJob.id;
								    JobService.rejectJobApplication(userID, jobID)
								      .then(
								    		  function (d){
								    			  self.job =d;
								    			  alert("you have successfully Rejected the Job application")
								    		  },
								    		  function(errResponse){
								    			  console
								    			        .error("Error while rejecting the job Application")
								    		  
								    		  });
								
								
							}
							
							self.callForInterview = function(userID){
								var jobID = $rootScope.selectedJob.id;
								    JobService.callForInterview(userID, jobID)
								      .then(
								    		  function (d){
								    			  self.job =d;
								    			  alert("Appliucation status changed as call for Interview")
								    		  },
								    		  function(errResponse){
								    			  console
								    			        .error("Error while call for Interview")
								    		  
								    		  });
								
								
							}
							
							self.selectUser = function(userID){
								var jobID = $rootScope.selectedJob.id;
								    JobService.selectUser(userID, jobID)
								      .then(
								    		  function (d){
								    			  self.job =d;
								    			  alert("Application status changed to selected")
								    		  },
								    		  function(errResponse){
								    			  console
								    			        .error("Error while changing the application to selected")
								    		  
								    		  });
								
								
							};
							
							self.getAllJobs = function(){
								
								console.log("Calling the method getAllJobs");
								JobService
								   .getAllJobs()
								   .then(
										   function(d){
											   self.jobs =d;
										   } ,
										   function(errResponse){
											   console
											          .error("Error while fetching all opened Jobs")
										   
										   });
							};
							
							self.getAllJobs();
							
							self.submit = function(){
								
								{
									console.log("submit a  new Job" , self.job);
									self.postAJob(self.job);
								}
							      
							};
							
							
								
							self.postAJob = function(job){
								console.log("submit a new job" , self.job);
								JobService.postAJob(job).then( function(d){
									alert("You successfully posted the job")
								}, function (errResponse){
									
									console.error("Error while posting the job");
								});
								 self.reset();
							};
								
								self.getJobDetails = getJobDetails
								
								function getJobDetails(jobID){
									console.log("get Job details of the id" , jobID);
									JobService
									    .getJobDetails(jobID)
									    .then(
									    		function(d){
									    			self.job =d;
									    			
									    			$location
									    			       .path("/view_job_details")
									    		},
									    		function(errResponse){
									    			console
									    			       .error("Error while fetching the job Details");
									    			
									    		});
									    
								};
							
							self.reset = function(){
								console.log ("resetting the Job");
								self.jobs = {
										id : '',
										title : '',
										description : '',
										dateTime : '',
										qualification : '',
										status : '',
									    errorCode : '',	
									    errorMessage : ''
								};
								$scope.myForm.$setPristine();// Reset Form
								
								
							};
							
			} ]);