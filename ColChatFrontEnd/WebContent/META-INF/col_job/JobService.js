'use strict';
 
app.factory('JobService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("JobService...")
	
	var BASE_URL='/ColChatBackEnd'
		
    return {
         
            applyForJob: function(id) {
            	console.log("applyForJob.... ")
                    return $http.post(BASE_URL+'/applyForJob/' +id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   function(errResponse){
                                    	console.error('Error while applying for the job');
                                    	return $q.reject(errResponse);
                                    }
                            );
            },
            
            getJobDetails: function(jobID) {
            	console.log("calling job details of " +jobID)
                    return $http.get(BASE_URL+'/getJobDetails/' +jobID)
                            .then(
                                    function(response){
                                    	$rootScope.selectedJob = response.data
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while applying for the job');
                                    	return $q.reject(errResponse);
                                    });
            },
           
            getMyAppliedJobs: function() {
            	console.log("calling getMyAppliedJobs ")
                    return $http.get(BASE_URL+'/getMyAppliedJobs/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while applying for the job');
                                    	return $q.reject(errResponse);
                                    });
            },
            
            postAJob: function(job) {
            	console.log("calling postAjob ")
                    return $http.post(BASE_URL+'/postAJob/' , job)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while applying for the job');
                                    	return $q.reject(errResponse);
                                    });
            },
            
            rejectJobApplication: function(userID , jobID) {
            	console.log("calling rejectJobApplication ")
                    return $http.put(BASE_URL+'/rejectJobApplication/' +userID+ "/" +jobID+ "/" +reason)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while rejecting the job');
                                    	return $q.reject(errResponse);
                                    });
            },
            
            callForInterview: function(userID , jobID) {
            	console.log("calling callForInterview ")
                    return $http.put(BASE_URL+'/rejectJobApplication/' +userID+ "/" +jobID+"/" +reason)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while callForInterview');
                                    	return $q.reject(errResponse);
                                    });
            },
            
            selectUser: function(id) {
            	console.log("selectUser ")
                    return $http.post(BASE_URL+'/selectUser/'+userID ,jobID)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while selecting User for the job');
                                    	return $q.reject(errResponse);
                                    });
            },
            
            getAllJobs: function() {
            	console.log("getAllJobs ")
                    return $http.get(BASE_URL+'/getAllJobs/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                    	console.error('Error while getting all jobs');
                                    	return $q.reject(errResponse);
                                    });
            }
	};
	
	
}]);