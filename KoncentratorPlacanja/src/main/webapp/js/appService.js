var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope','$window',
                            	function($http, $rootScope,$window) {
	
	this.getToken=function(){
		var username='ARNtEjszQ7JEgVLsfLteGrtPMd6nzcVhCqnouVDeke3uLX44jmzqCNpBwLMm52-XvTuvnF9ROJESzcoF';
		var password='EEXFSULzD9EzIeAhukYACZDWL20YWm9FcDNwSCFypPhjuNlCU9mHwcF1IzQeAqKl9HlRV5tMRBSAR332';
		var basicAuthString = btoa(username+':'+password);
		return $http({
			method: "POST",
            url: 'https://api.sandbox.paypal.com/v1/oauth2/token',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic ' + basicAuthString,
            },
            data: 'grant_type=client_credentials'
        }).then(function(response){
        	$window.localStorage.setItem('token',response.data.access_token)
        	$window.localStorage.setItem('token_type',response.data.token_type)
        	$rootScope.token = response.data.access_token;
			$rootScope.token_type = response.data.token_type;
        	return response;
        });
	};
	
	
	this.kreiraj = function(racun) {
		return $http({
			method: 'POST',
			headers: {
				'Authorization': localStorage.getItem('token_type') + ' '+ localStorage.getItem('token')
		         },
			url: 'https://api.sandbox.paypal.com/v1/payments/payment',
			data: racun
			});	
	};
	
	
	this.plati = function(payment,payer) {
		return $http({
			method: 'POST',
			headers: {
				'Authorization': localStorage.getItem('token_type') + ' '+ localStorage.getItem('token')
		         },
			url: "https://api.sandbox.paypal.com/v1/payments/payment/"+payment+"/execute",
			data: payer
			});	
	};
	
	
	this.getRacun=function(racunId){
		return $http({
			method: 'GET',
			headers: {
				'Authorization': localStorage.getItem('token_type') + ' '+ localStorage.getItem('token')
		         },
			url: 'https://api.sandbox.paypal.com/v1/payments/payment/'+racunId,
			});	

	};
	
}
]);