var app=angular.module('app.controllers',[]);



app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.init=function(){
		$scope.token = $location.absUrl().split('?')[1];
		$scope.casopisId = $scope.token.split('&')[0];
		$scope.userEmail=$scope.token.split('&')[1];
		localStorage.setItem("casopisId", 	$scope.casopisId);
		localStorage.setItem("userEmail", $scope.userEmail);
		if($scope.casopisId==null){
			$window.location.href ="http://localhost:8084/#/";
		}else{
			appService.getCasopis($scope.casopisId).then(function(response){
				$scope.casopis=response.data;
			})
		}
	}

	$scope.izaberi=function(opcija){
		$scope.opcija=opcija;
		$location.path(opcija);
		
		
	}
	// PAYPAL
	$scope.paypalInit=function(){
		appService.getToken($scope.casopis).then(function(response){

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
				        "total": $scope.casopis.cena,
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
				            "name": $scope.casopis.naziv,
				            "description": "Casopis id:"+$scope.casopis.id,
				            "quantity": "1",
				            "price": $scope.casopis.cena,
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
				    "cancel_url": "http://localhost:8081/#/"
				  }
				};
		
		
		var bill = JSON.stringify(racun);
		
		appService.kreiraj(bill).then(function(response){
			$window.location.href = response.data.links[1].href;
		});		 
	}
	
	$scope.sucessInit=function(){
		$scope.url = $location.absUrl().split('?')[1];
		$scope.params=$scope.url.split('&');
		
		$scope.id=$scope.params[2].split('=')[1];
		$scope.payer=JSON.stringify($scope.id);
		
		$scope.id=$scope.params[0].split('=')[1];
		$scope.payer=$scope.payer.slice(1, -1);
		var json= {"payer_id" : $scope.payer}
		$scope.payment=$scope.id;
		
		appService.plati($scope.payment,json).then(function(response){
			$scope.dostaviCasopis(localStorage.getItem("userEmail"),localStorage.getItem("casopisId"));
		});	
	}
	
	$scope.dostaviCasopis=function(email,id){
		appService.dostaviCasopis(email,id).then(function(response){
			alert("Casopis je kupljen!")
			$window.location.href ="http://localhost:8084/#/profile";

		});	

	}

	$scope.paypalSubscriptionInit=function(){
		$scope.token = $location.absUrl().split('?')[1];
		$scope.casopisId = $scope.token.split('&')[0];
		$scope.userEmail=$scope.token.split('&')[1];
		localStorage.setItem("casopisId", 	$scope.casopisId);
		localStorage.setItem("userEmail", $scope.userEmail);

		appService.getCasopis($scope.casopisId).then(function(response){
			$scope.casopis=response.data;
			$scope.paypalSubscription();
		})
	}

	$scope.paypalSubscription=function(){
		appService.getToken($scope.casopis).then(function(response){
			$scope.sendSubscribe();
		});
	}
	$scope.sendSubscribe=function(){
		var zahtev={
			"name": "Pretplata na casopis",
			"description": "Mesecna i nedeljna pretplata na casopis.",
			"start_date": "2019-02-22T09:13:49Z",
			"plan":
			{
				"id": "P-8ES03530EH897271HYXZDNLA"
			},
			"payer":
			{
				"payment_method": "paypal"
			}
		}
		var zahtevJson = JSON.stringify(zahtev);
		appService.sendSubscribe(zahtevJson).then(function(response){
			$window.location.href = response.data.links[0].href;
		})

	}

	$scope.paypallSubscripeSuccess=function(){
		appService.paypallSubscripeSuccess(localStorage.getItem("userEmail"),localStorage.getItem("casopisId")).then(function(response){
			alert("Pretplata je uspela!")
			$window.location.href ="http://localhost:8084/#/profile";
		})
	}


/*	$scope.getRacun=function(){
		
		appService.getRacun($scope.data).then(function(response){
			$scope.par=response.data;

		});	
		
	}
	*/


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
					  "merchantId": $scope.casopis.informacijeZaPlacanje.merchantId,
					  "merchantPassword": $scope.casopis.informacijeZaPlacanje.merchantPassword,
						"merchantTimestamp": "",
					  "amount": $scope.casopis.cena,
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
			$scope.dostaviCasopis(localStorage.getItem("userEmail"),localStorage.getItem("casopisId"));
		});
	}
	
}]);