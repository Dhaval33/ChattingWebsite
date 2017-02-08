'use strict';

app.controller('BlogController', ['$scope', 'BlogService', '$location', '$rootScope',
                                function($scope, BlogService, $location, $rootScope) {
	
	console.log("user blog controller");
	
	var self = this;
    self.blogs={
    		blogid:'', approve:'', description:'', 
    		blogcreatedate:'', blogtitle:'', likes:'', userID: ''};

    self.blogs=[];
    
    self.fetchAllBlogs = function()
    {
        BlogService.fetchAllBlogs().then(function(d)
    	{
        	self.blogs = d;
            console.log("Fetch all user blogs")
        },
        function(errResponse)
        {
        	console.error('Error while fetching User blog'+ errResponse);
        }
      );
   };
    
   self.fetchAllBlogs();
   
   self.createNewBlog = function(blog){
   	BlogService.createNewBlog(blog)
   		.then(
   				self.fetchAllBlogs, self.reset,
   				function(errResponse)
   				{
   					console.error('Error while creating record');
   				}
   		);
   };

   self.approvedblog = function(blogid)
   {
	   BlogService.approvedblog(blogid)
       	.then(
       			self.fetchAllBlogs,    			
       			function(errResponse)
       			{
       				console.error('Error while approving blog');
       			}
       		);
   };

   self.deleteblog = function(blogid)
   {
	   BlogService.deleteblog(blogid)
       	.then(
       			self.fetchAllBlogs,    			
       			function(errResponse)
       			{
       				console.error('Error while deleting blog');
       			}
       		);
   };

   self.likeupdate = function(blogid)
   {
	   BlogService.likeupdate(blogid)
       	.then(
       			self.fetchAllBlogs,    			
       			function(errResponse)
       			{
       				console.error('Error while approving blog');
       			}
       		);
   };

   self.approveblog = function(blogid){
   	if(confirm('Are you sure you want to approve this Blog?')) {
   		self.approvedblog(blogid);
   	}
   };

   self.delblog = function(blogid){
	   	if(confirm('Are you sure you want to disable selected Blog?')) {
	   		self.deleteblog(blogid);
	   	}
   };

   self.likeclick = function(blogid)
   {
	   self.likeupdate(blogid);
   };

   self.submit = function()
   {
   		self.createNewBlog(self.blog);
   		console.log("Saving new blog", self.blog);
   		alert("the blog has been successfully created")
   };
   

   self.reset = function()
   {
	    self.blog={
	    		blogid:'',
	    		approve:'',
	    		description:'',
	    		blogcreatedate:'',
	    		blogtitle:'',
	    		likes:'',
	    		userID: ''};
	    $scope.myForm.$setPristine();
   };
}]);
