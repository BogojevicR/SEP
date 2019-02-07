var services = angular.module('app.services',['ngResource']);

services.service('appService', ['$http', '$rootScope','$window',
                            	function($http, $rootScope,$window) {
	
	this.getToken=function(casopis){
		var username=casopis.informacijeZaPlacanje.paypalUsername;
		var password=casopis.informacijeZaPlacanje.paypalPassword;
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

	this.sendSubscribe=function(json){
		return $http({
			method: 'POST',
			headers: {
				'Authorization': localStorage.getItem('token_type') + ' '+ localStorage.getItem('token')
		         },
			url: "https://api.sandbox.paypal.com/v1/payments/billing-agreements",
			data: json
			});	
	}

	this.paypallSubscripeSuccess=function(email,casopisId){
		return $http.post("http://localhost:8084/api/nc/casopis/pretplatiSe/"+email+"/"+casopisId)
	}

	
	
	
/*	this.getRacun=function(racunId){
		return $http({
			method: 'GET',
			headers: {
				'Authorization': localStorage.getItem('token_type') + ' '+ localStorage.getItem('token')
		         },
			url: 'https://api.sandbox.paypal.com/v1/payments/payment/'+racunId,
			});	

	};
	*/
	this.bitcoinPayment=function(json){
		console.log(json);
		return $http({
			method: 'POST',
			headers: {
				'Accept': 'application/json',
                'Content-Type': 'application/json',
                'cache-control': 'no-cache',
				'Authorization': 'Bearer UgSUMdDH_9qcXH7u8XdUjrX5Ud-iFCGAnx2XNymB'
			
		         },
			url: "https://api-sandbox.coingate.com/v2/orders",
			data: json
			});			
	}
	
	this.karticaPayment=function(json){
		console.log(json)
		return $http({
			method: 'POST',
			url: "http://localhost:8083/api/bank/createPayment",
			data: json
			});			
	}

	this.getPaymentRequest=function(merchantOrderId){
		 return $http.get("http://localhost:8081/api/KP/getPaymentRequest/"+merchantOrderId)
	}

	this.getCasopis=function(casopisId){
		return $http.get("http://localhost:8084/api/nc/casopis/getOne/"+casopisId)
   }

   this.dostaviCasopis = function(email,casopisId) {

	return $http.post("http://localhost:8084/api/nc/casopis/kupiCasopis/"+email+"/"+casopisId)

};
	
}
]);