'user strict'

app.factory('BlogService',['$http','$q','$rootScope',function($http,$q,$rootScope)
{
	var baseurl='/ColChatBackEnd'
		

		return {
			fetchAllBlogs: function()
			{
				return $http.get(baseurl + "/alluserblog").then(function (response) 
				{	
					return response.data;
				},
				function(errResponse)
				{
					console.error('Error while fetching Blogs' + errResponse);
					deferred.reject(errResponse);
				}
			);
	 },
	 
	 approvedblog: function(blogid)
	 {
		 return $http.post(baseurl+'/getapproveblog/' + blogid)
	            .then(
	            	function (response) {
	                return response.data;
	            },
	            function(errResponse){
	                console.error('Error while fetching Blogs');
	                return $q.reject(errResponse);
	            }
	        );
	    },

		deleteblog: function(blogid)
		{
			 return $http.post(baseurl+'/getdeleteblog/' + blogid)
		            .then(
		            	function (response) {
		                return response.data;
		            },
		            function(errResponse){
		                console.error('Error while fetching Blogs');
		                return $q.reject(errResponse);
		            }
		        );
		    },

	  likeupdate: function(blogid)
	  {
	       return $http.post(baseurl+'/getupdatelike/' + blogid).then(
	    		function (response) {
		             return response.data;
		        },
		        function(errResponse){
		                console.error('Error while fetching Blogs');
		                return $q.reject(errResponse);
		            }
		        );
		},

	 createNewBlog : function (blog)
  	 {
  		  return $http.post(baseurl + '/adduserblog/' , blog).then(function (response) 
  		  {
  			  return response.data;
  		  },
  	      function(errResponse)
  	      {
  	           console.error('Error while creating blog');
  	           return $q.reject(errResponse);
  	      });
  	  }  	  
	}
  }
]);