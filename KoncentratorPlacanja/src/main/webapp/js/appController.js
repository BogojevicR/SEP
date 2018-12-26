var app=angular.module('app.controllers',[]);

app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.izaberi=function(opcija){
		$scope.opcija=opcija;
		$window.location.href = "/#/"+opcija;
		$window.location.reload();
		
	}
	
	$scope.paypalInit=function(){
		appService.getToken().then(function(response){
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
				    /*    "details": {
				          "subtotal": "5.00",
				          "tax": "0.00",
				          "shipping": "0.00",
				          "handling_fee": "0.00",
				          "shipping_discount": "0.00",
				          "insurance": "0.00"
				        } */
				      },
				      "description": "The payment transaction description.",
				      "custom": "EBAY_EMS_90048630024435",
				      // TODO 1 INVOCE NUMBER TREBA DA JE RAZLICIT STALNO!!! IMA DA SAM GENERISE PGOLEDAJ TO U SACUVANIM
				      
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
				      /*  "shipping_address": {
				          "recipient_name": "Brian Robinson",
				          "line1": "4th Floor",
				          "line2": "Unit #34",
				          "city": "San Jose",
				          "country_code": "US",
				          "postal_code": "95131",
				          "phone": "011862212345678",
				          "state": "CA"
				        } */
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
	
	
}]);