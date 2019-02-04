var app=angular.module('app.controllers',[]);

/*app.factory('responseInterceptor', [function(){
	var responseInterceptor={
			request: function(config){
				console.log(config);
				config.headers["Access-Control-Allow-Origin"]="http://localhost:8081";
				config.headers["Access-Control-Allow-Methods"]="GET, PUT, POST, DELETE, HEAD, OPTIONS";
				config.headers["Access-Control-Allow-Credentials"]="true";
				config.headers["Access-Control-Allow-Headers"]="Content-Type, Accept, X-Requested-With, Authorization-Token, Response-Type";

				return config;
			},
			response: function(response){
				console.log(response.config);
				response.config.headers["Access-Control-Allow-Origin"]="http://localhost:8081";
				response.config.headers["Access-Control-Allow-Credentials"]="true";
				response.config.headers["Access-Control-Allow-Methods"]="POST, GET, PUT, OPTIONS, DELETE";
				response.config.headers["Access-Control-Max-Age"]="3600";
				response.config.headers["Access-Control-Allow-Headers"]="Content-Type, Accept, X-Requested-With, Authorization-Token, Response-Type";
				return response;
			}		
	};
	return responseInterceptor;
}]);

app.config(['$httpProvider', function($httpProvider){
	$httpProvider.interceptors.push('responseInterceptor');
}]);
*/

app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',/*'responseInterceptor' , */
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.izaberi=function(opcija){
		$scope.opcija=opcija;
		$window.location.href = "/#/"+opcija;
		$window.location.reload();
		
	}
	// PAYPAL
	$scope.paypalInit=function(){
		appService.getToken().then(function(response){
			
		//	console.log(response)
			$scope.kreiraj();
		});
		
	}
	
	$scope.kreiraj=function(){
		
		var racun={
				  "intent": "sale",
				  "payer": {
				    "payment_method": "paypal"
				  },
				  "transactions": [
				    {
				      "amount": {
				        "total": "5.00",
				        "currency": "USD",
				      },
				      "description": "The payment transaction description.",
				      "custom": "EBAY_EMS_90048630024435",
				      "payment_options": {
				        "allowed_payment_method": "INSTANT_FUNDING_SOURCE"
				      },
				      "soft_descriptor": "ECHI5786786",
				      "item_list": {
				        "items": [
				          {
				            "name": "Casopis 1",
				            "description": "Naucni casopis.",
				            "quantity": "1",
				            "price": "5.00",
				            "tax": "0.00",
				            "sku": "1",
				            "currency": "USD"
				          }
				        ],
				      }
				    }
				  ],
				  "note_to_payer": "Contact us for any questions on your order.",
				  "redirect_urls": {
				    "return_url": "http://localhost:8081/#/sucess",
				    "cancel_url": "http://localhost:8081"
				  }
				};
		
		
		var bill = JSON.stringify(racun);
		
		appService.kreiraj(bill).then(function(response){
			$window.location.href = response.data.links[1].href;
		});		 
	}
	
	$scope.sucessInit=function(){
		$rootScope.url = $location.absUrl().split('?')[1];
		$rootScope.params=$rootScope.url.split('&');
		
		$rootScope.id=$rootScope.params[2].split('=')[1];
		$rootScope.payer=JSON.stringify($rootScope.id);
		
		$rootScope.id=$rootScope.params[0].split('=')[1];
		$rootScope.payer=$rootScope.payer.slice(1, -1);
		var json= {"payer_id" : $rootScope.payer}
		$rootScope.payment=$rootScope.id;
		
		appService.plati($rootScope.payment,json).then(function(response){
			$rootScope.par=response.data;
		});	
	}
	
	$scope.getRacun=function(){
		
		appService.getRacun($rootScope.data).then(function(response){
			$rootScope.par=response.data;

		});	
		
	}
	
	//BITCOIN
	
	$scope.bitcoinInit=function(){
		var request={
				  "order_id": '1195865',
				  "price_amount": "0.2",
				  "price_currency": "USD",
				  "receive_currency": "BTC",
				  "receive_amount": "",
				  "title": "Casopis",
				  "description": "Placanje naucnog casopisa",
				  "callback_url": "http://localhost:8081",
				  "cancel_url": "https://coingate.com/invoice/6003de09-ee9a-4584-be0e-5c0c71c5e497",
				  "success_url": "http://localhost:8081",
				  "token": "MVsgsjGXv-pRWMnZzsuD4B5xcdnj-w"
				};
		
		var json = JSON.stringify(request);
		
		appService.bitcoinPayment(json).then(function(response){
			console.log(response);
		});	
	}
		
		//KARTICA
		
		$scope.karticaInit=function(){
			
			var request={
					  "merchantId": "3d6b1825-8dce-486a-83fa-2d6e1108c16f",
					  "merchantPassword": "1980",
						"merchantTimestamp": "",
					  "amount": "20",
					  "sucessUrl": "http://localhost:8082",
					  "failedUrl": "http://localhost:8082",
					  "errorUrl": "http://localhost:8082"
					};
			
			var json = JSON.stringify(request);
			
			appService.karticaPayment(json).then(function(response){
				window.location = response.data.paymentUrl+"?"+response.data.paymentId;
			});	
		
	}


	$scope.bankFinalizeInit=function(){
		$scope.merchantOrderId = $location.absUrl().split('?')[1];
		appService.getPaymentRequest($scope.merchantOrderId).then(function(response){
			$scope.paymentRequest=response.data;
		});
	}
	
}]);