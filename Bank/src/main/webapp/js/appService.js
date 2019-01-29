var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope','$window',
                            	function($http, $rootScope,$window) {
	// PROMENI UMESTO LOCALHOST CURRENT HOST
	this.login = function(pan,secret){
		return $http.get("http://localhost:8082/api/bankAccount/login/"+pan+"/"+secret)
	}

	this.loginMerchant = function(merchantId,merchantPassword){
		return $http.get("http://localhost:8082/api/bankAccount/merchantlogin/"+merchantId+"/"+merchantPassword)
	}
	
	this.getBank = function(port){
		return $http.get("http://localhost:8082/api/bank/"+port)
	}


	this.registerAccount = function(accountJson,merchantJson,role) {
		if(role=="USER"){
			return $http({
				method: 'POST',
				url: '/api/bankAccount/create/',
				data: accountJson
				});	
		}else{
			return $http({
				method: 'POST',
				url: '/api/bankAccount/createMerchant/',
				data: merchantJson
				});	
		}
	};

	this.getPayment=function(id){
		return $http.get("http://localhost:8082/api/bank/getPayment/"+id)
	}

	this.pay = function(requestJSON) {
			return $http({
				method: 'POST',
				url: '/api/bank/finalizeTransfer/',
				data: requestJSON
				});	
	};

	
}
]);