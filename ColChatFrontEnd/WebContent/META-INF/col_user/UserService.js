'use strict';

var myApp = angular.module('myApp');
 
app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("UserService...")
	
	var BASE_URL='/ColChatBackEnd'
		
    return {
         
            fetchAllUsers: function() {
            	console.log("calling fetchAllUsers ")
                    return $http.get(BASE_URL+'/users')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   null
                            );
            },
            
            myProfile: function() {
            	console.log("calling myprofile ")
                    return $http.get(BASE_URL+'/myProfile')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                   null
                            );
            },
            
            accept: function(userId) {
            	console.log("calling approve ")
                    return $http.get(BASE_URL+'/accept/'+userId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while accept registration');
                                       
                                    }
                            );
            },
            
            reject: function(userId, reason) {
            	console.log("calling reject ")
                    return $http.get(BASE_URL+'/reject/'+userId+'/'+reason)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while reject registration');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            Register: function(user){
            	console.log("calling create user")
                    return $http.post(BASE_URL+'/Register/', user) //1
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateUser: function(user){
            	console.log("calling Update ")
                    return $http.put(BASE_URL+'/Update/' , user )  //2
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
              
            logout: function(){
            	console.log('logout....')
                return $http.get(BASE_URL+'/user/logout')
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                              null
                        );
        },
        
        
        showDetails: function(userId) {
        	console.log("calling showDetails " +userId)
                return $http.get(BASE_URL+'/userDetails/'+userId)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while showing friends details');
                                   
                                }
                        );
        },
        
            
            authenticate: function(user){
            	   console.log("Calling the method authenticate with the user :"+user)
          		 
                return $http.post(BASE_URL+'/Authenticate/',user)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                               null
                        );
        }
         
    };
 
}]);