'use strict';

var myApp = angular.module('myApp');

app.controller(	'FriendController', [	'$scope', 'FriendService','UserService', '$location','$rootScope','$cookieStore',
						'$http',
						function($scope, FriendService,UserService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("FriendController...")
							var self = this;
							self.friend = {userId : '',friendId : '', id : '',status : '',isOnline:'',
								errorCode : '',	errorMessage : ''
							};
							self.friends = [];
							self.users=[];
							self.pendingRequests=[];
							 
							 self.fetchAllUsers = function() {
									console.log("fetchAllUsers...")
									UserService
											.fetchAllUsers()
											.then(
													function(d) {
														self.users = d;
														console.log("Users Length..." +self.users.length)
													},
													function(errResponse) {
														console
																.error('Error while fetching Users');
													});
								};
								
								 self.friendsRequest=function(){
										console.log("showFriendResquest controller...")
										FriendService
											.friendsRequest()
											.then(
													function(d) {
														self.pendingRequests = d;
														console.log("showFriendResquest controller...");
							});
									};
									
							
							 self.fetchAllFriends = function() {
									console.log("fetchAllFriends...")
									FriendService
											.fetchAllFriends()
											.then(
													function(d) {
														console.log("function Friends...")
														self.friends = d;
														
													},
													function(errResponse) {
														console
																.error('Error while fetching Friends');
													});
								};
								
																
									
									self.addFriend = function(userId) {
										console.log("Friend addFriend..." +userId)
										
										FriendService
												.addFriend(userId)
												.then(
														function(d) {
															self.friend = d;
															self.fetchAllUsers
															alert(self.friend.errorMessage)
															
														},
														
														function(errResponse) {
															console
																	.error('Error while updating Friend.');
														});
									};
									
									self.acceptFriend= function(userId) {
										console.log("Friend addFriend..." +userId)
										
										FriendService
												.acceptFriend(userId)
												.then(
														function(d) {
															self.friend = d;
															self.fetchAllUsers
															alert(self.friend.errorMessage)
															
														},
														
														function(errResponse) {
															console
																	.error('Error while accepting Friend.');
														});
									};
									
									self.rejectFriend= function(userId) {
										console.log("Friend rejectFriend..." +userId)
										
										FriendService
												.rejectFriend(userId)
												.then(
														function(d) {
															self.friend = d;
															self.fetchAllUsers
															alert(self.friend.errorMessage)
															
														},
														
														function(errResponse) {
															console
																	.error('Error while rejecting Friend.');
														});
									};
									
									self.unFriend= function(userId) {
										console.log("Friend unFriend..." +userId)
										
										FriendService
												.unFriend(userId)
												.then(
														function(d) {
															self.friend = d;
															self.fetchAllUsers
															alert(self.friend.errorMessage)
															
														},
														
														function(errResponse) {
															console
																	.error('Error while unFriending Friend.');
														});
									};
									self.friendsRequest();
									
									self.fetchAllFriends();
									self.fetchAllUsers();
									
} ]);