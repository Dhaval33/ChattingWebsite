'use strict';

var myApp = angular.module('myApp');

app.controller(	'UserController', [	'$scope', 'UserService', '$location','$rootScope','$cookieStore',
						'$http',
						function($scope, UserService, $location, $rootScope,
								$cookieStore,$http) {
							console.log("UserController...")
							var self = this;
							self.user = {userId : '',name : '', password : '',	mobile : '',
								address : '', mail : '',Is_online : '',	role : '',
								errorCode : '',	errorMessage : ''
							};
							self.users = [];
							
							

							self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};
							
							//self.fatchAllUsers();
							
							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout();
								$location.path('/');

							};

							self.Register = function(user) {
								console.log("createUser...")
								UserService
										.Register(user)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while creating User.');
												});
							};
							
							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(
												function(d) {
													self.user = d;
													$location.path("/myProfile")
												},
												function(errResponse) {
													console
															.error('Error while fetch profile.');
												});
							};
							
							self.accept = function(userId) {
								console.log("accept...")
								UserService
										.accept(userId)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/")
													alert(self.user.errorMessage)
													
												},
												
												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};
							
							self.reject = function( userId) {
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								UserService
										.reject(userId,reason)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/")
													alert(self.user.errorMessage)
													
												},
												null );
							};

							/*self.update = function(user) {
								console.log("updateUser...")
								UserService
										.updateUser(user)
										.then(
												self.fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while Updating User.');
												});
							};
							*/
							self.update = function(user) {
								{
									console.log('Checking Update', self.user);
									UserService.updateUser(self.user);
									alert("Update successfully")
								}
								self.reset();
							};


							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService
										.authenticate(user)
										.then(

												function(d) {

													self.user = d;
													console
															.log("user.errorCode: "
																	+ self.user.errorCode)
													if (self.user.errorCode == "404")

													{
														alert(self.user.errorMessage)

														self.user.userId = "";
														self.user.password = "";

													} else { //valid credentials
														console
																.log("Valid credentials. Navigating to home page")
														$rootScope.currentUser = self.user
														/*$http.defaults.headers.common['Authorization'] = 'Basic '
																+ $rootScope.currentUser;*/
														$cookieStore
																.put(
																		'currentUser',
																		$rootScope.currentUser);
														$location.path('/');

													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							

						

							self.fetchAllUsers();

							self.login = function() {
								{
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									UserService.Register(self.user);
									alert("You successfully Registered, and fowarded to Admin approval")
								}
								self.reset();
							};

							self.reset = function() {
								self.user = {
									userId : '',
									Name : '',
									Password : '',
									Contact : '',
									Address : '',
									Mail : '',
									Is_online : '',
									errorCode : '',
									errorMessage : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};

						} ]);