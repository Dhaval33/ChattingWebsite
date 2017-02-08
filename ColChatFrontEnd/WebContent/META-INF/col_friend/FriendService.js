'use strict';
 
var myApp = angular.module('myApp');

app.factory('FriendService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("FriendService...")
	
	var BASE_URL='/ColChatBackEnd' 
		
		
    return {
		fetchAllFriends:function(){
			console.log("calling fetchAllFriends ")
            return $http.get(BASE_URL+'/myFriends')
                    .then
                    (
                            function(response)
                            {
                            	console.log("In FriendService function ")
                                return response.data;
                            }, 
                           null
                    );
		},
		
		friendsRequest:function(){
			console.log("calling friendsRequest ")
            return $http.get(BASE_URL+'/getMyFriendRequest')
                    .then
                    (
                            function(response)
                            {
                            	console.log("In FriendRequest function ")
                                return response.data;
                            }, 
                           null
                    );
		},
		
		addFriend: function(userId) {
         	console.log("calling addFriend " + userId)
                 return $http.get(BASE_URL+'/addFriend/' + userId)
                         .then(
                                 function(response){
                                     return response.data;
                                 }, 
                                 function(errResponse){
                                     console.error('Error while accept registration');
                                    
                                 }
                         );
         },
         
         acceptFriend: function(userId) {
          	console.log("calling acceptFriend " +userId)
                  return $http.get(BASE_URL+'/acceptFriend/'+userId)
                          .then(
                                  function(response){
                                      return response.data;
                                  }, 
                                  function(errResponse){
                                      console.error('Error while accept registration');
                                     
                                  }
                          );
          },
          
          rejectFriend: function(userId) {
            	console.log("calling rejectFriend " +userId)
                    return $http.get(BASE_URL+'/rejectFriend/'+userId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while rejecting');
                                       
                                    }
                            );
            },
            
            unFriend: function(userId) {
            	console.log("calling unFriend " +userId)
                    return $http.get(BASE_URL+'/unFriend/'+userId)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while unfriending friend');
                                       
                                    }
                            );
            }
		
	
	};
	 
}]);