app.
       controller(	'JobApplication', 
    		              [	'$scope', 
    		            	'JobService', 
    		            	'$location',
    		            	'$rootScope',
						           function($scope, JobService, $location, $rootScope) {
							console.log("JobApplication controller...")
							var self = this;
							self.jobapplications = {
									id : '',
									jobID : '',
									userID : '',
									DateApplied : '',
									status : '',
									Reason : ''
								    	
							};
							
							self.jobapplications=[];
							
							self.getMyAppliedJobs = function(){
								console.log("calling the method getMyAppliedJobs");
								JobService
								       .getMyAppliedJobs()
								       .then(function (d){
								    	   self.jobapplications=d;
								    	  console.log(d) 
								       }, 
								       function(errResponse){
								    	   console.error("Error while fetching the jobs");
								       });
								
							};
							
							self.getMyAppliedJobs();

							

							
    		              } ]);