app.controller("ChatController", function($scope,$rootScope, ChatService) {
	
	console.log('in chat');
	
	$scope.messages = [];
	$scope.message = "";
	$scope.max = 140;

	$scope.addMessage = function() {
		
		ChatService.send($scope.message);
	 	$scope.message = "";
	 	console.log("addMessage")
	};

	ChatService.receive().then(null, null, function(message) {
		console.log("received " + message);

		$scope.messages.push(message);
	});
});