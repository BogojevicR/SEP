var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope','$window',
                            	function($http, $rootScope,$window) {
	// PROMENI UMESTO LOCALHOST CURRENT HOST
	this.login = function(pan,secret){
		return $http.get("http://localhost:8082/api/bankAccount/login/"+pan+"/"+secret)
	}
	
	this.getBank = function(port){
		return $http.get("http://localhost:8082/api/bankAccount/bank/"+port)
	}
	
}
]);