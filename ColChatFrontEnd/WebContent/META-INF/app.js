
var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider ) {
	$routeProvider

	.when('/', {
		templateUrl : 'col_home/home.html'
		
	})
	
	
	
	.when('/ListUser', {
		templateUrl : 'col_user/users.html',
		controller : 'UserController'
	})

	.when('/UploadImage', {
		templateUrl : 'col_upload/profilepictureupload.html',
		
	})

	.when('/about', {
		templateUrl : 'col_about/about.html',
		
	})

	.when('/login', {
		templateUrl : 'col_user/login.html',
		controller : 'UserController',
		controllerAs: 'ctrl'
	})

	.when('/register', {
		templateUrl : 'col_user/register.html',
		controller : 'UserController'
	})
	
	
	.when('/myProfile', {
		templateUrl : 'col_user/my_profile.html',
		controller : 'UserController'
	})
	
	.when('/myProfilePage', {
		templateUrl : 'col_user/Profilepage.html',
		
	})
	
	
    /*Job related mappings*/

	
		
	.when('/search_job', {
		templateUrl : 'col_job/search_job.html',
		controller : 'JobController'
	})

	.when('/view_applied_jobs', {
		templateUrl : 'col_job/view_applied_jobs.html',
		controller : 'JobController'
	})
	
	.when('/view_job_details', {
		templateUrl : 'col_job/view_job_details.html',
		controller : 'JobController'
	})

	.when('/post_job', {
		templateUrl : 'col_job/post_job.html',
		controller : 'JobController'
	})
	
	
	/*Blog related mappings*/
	
	
	.when('/approveblogs', {
		templateUrl: 'col_blog/ApproveBlogs.html',
		controller: 'BlogController'
	})
	.when('/viewblogs', {
		templateUrl: 'col_blog/ViewBlogs.html',
		controller: 'BlogController'
	})
	.when('/addblog', {
		templateUrl: 'col_blog/CreateBlog.html',
		controller: 'BlogController'
	})
	.when('/myblog', {
		templateUrl: 'col_blog/MyCreatedBlogs.html',
		controller: 'BlogController'
	})


	
	/* Friend related mapping
	 */

	.when('/listallusers', {
		templateUrl: 'col_friend/All Users.html',
		controller: 'FriendController'
	})
	.when('/listpending', {
		templateUrl: 'col_friend/frmPendingRequest.html',
		controller: 'FriendController'
	})
	.when('/listmyfriends', {
		templateUrl: 'col_friend/frmViewFriends.html',
		controller: 'FriendController'
	})

	/* Chat related mapping
	 */
	
	
	.when('/chat', {
		templateUrl: 'col_chat/SendChat.html',
		controller: 'ChatController'
	})



	.otherwise({
		redirectTo : '/'
	});
	
});
	
	app.run( function ($rootScope, $location,$cookieStore, $http) {

		 $rootScope.$on('$locationChangeStart', function (event, next, current) {
			 console.log("$locationChangeStart")
			
		        // redirect to login page if not logged in and trying to access a restricted page
		        var restrictedPage = $.inArray($location.path(), ['','/','/viewblogs','/login','/register','/list_blog' , '/about' , '/view_job_details']) === -1;
			 console.log("Navigating to page :" + $location.path())
		        console.log("restrictedPage:" +restrictedPage)
		        console.log("currentUser:" +$rootScope.currentUser)
		        var loggedIn = $rootScope.currentUser.userId;
		        
		        console.log("loggedIn:" +loggedIn)
		        
		        if(!loggedIn)
		        	{
		        	
		        	 if (restrictedPage) {
			        	  console.log("Navigating to login page:")
			        	

							            $location.path('/login');
			                }
		        	}
		        
				 else //logged in
		        	{
		        	
					 var role = $rootScope.currentUser.role;
					 var userRestrictedPage = $.inArray($location.path(), ["/post_job" ]) == 0;
					
					 
					 if(userRestrictedPage && role!='Admin' )
						 {
						 
						  alert("You are unauthorized to log into this page  or do this operation as you are logged as : " + role )
						   $location.path('/');
						 
						 }
					     
		        	
		        	}
		        
		 }
		       );
		 
		 
		 // keep user logged in after page refresh
	     $rootScope.currentUser = $cookieStore.get('currentUser') || {};
	     if ($rootScope.currentUser) {
	         $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
	     }

	});






