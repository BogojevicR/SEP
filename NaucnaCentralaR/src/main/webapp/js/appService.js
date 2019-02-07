var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope','$window',
                            	function($http, $rootScope,$window) {

	this.login = function(email,password){
		return $http.get("http://localhost:8084/api/centrala/login/"+email+"/"+password)
	}

	this.registerAccount = function(accountJson) {
			return $http({
				method: 'POST',
				url: '/api/centrala/create/',
				data: accountJson
				});	
	};

	this.getAllCasopisi = function(){
		return $http.get("http://localhost:8084/api/nc/casopis/getAll")

	}

	this.dodajRadUCasopis = function(radJSON){
		return $http({
			method: 'POST',
			url: '/api/nc/casopis/dodajRad',
			data: radJSON
			});	
	}

}
]);