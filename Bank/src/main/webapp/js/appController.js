var app=angular.module('app.controllers',[]);


app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.init=function(){
		$scope.roles = ["USER", "MERCHANT"];
		if(localStorage.getItem("logged")=="true"){
			if(localStorage.getItem("pan")!="")
			$scope.login(localStorage.getItem("pan"),localStorage.getItem("secret"));
			if(localStorage.getItem("merchantId")!="")
			$scope.loginMerchant(localStorage.getItem("merchantId"),localStorage.getItem("merchantPassword"));
			$scope.getBank();
			$("#logoutBtn").show();
		}else{
			$("#logoutBtn").hide();
		}
	}

	$scope.changedRole= function (role){
		if(role=="MERCHANT"){
			$("#cardUsername").show();
		}else{
			$("#cardUsername").hide();
		}
	}
	
	$scope.login=function(pan,secret){
		appService.login(pan,secret).then(function(response){
				$scope.user=response.data;
				if(response.data!=""){
					localStorage.setItem("pan", pan);
					localStorage.setItem("secret", secret);
					localStorage.setItem("logged",true);
					$window.location.href = "/#/profile"
				}else{
					alert("Wrong Login!");
					return
				}
					
		})
	}

	$scope.loginMerchant=function(merchantId,merchantPassword){
		appService.loginMerchant(merchantId,merchantPassword).then(function(response){
				$scope.merchantUser=response.data;
				$scope.user=response.data.clientAccount;
				if(response.data!=""){
					localStorage.setItem("merchantId", merchantId);
					localStorage.setItem("merchantPassword", merchantPassword);
					localStorage.setItem("logged",true);
					$window.location.href = "/#/profile"
				}else{
					alert("Wrong Login!");
					return
				}
					
		})
	}
	
	$scope.logout=function(){
		if(localStorage.getItem("logged")=="true"){
			localStorage.setItem("pan", "");
			localStorage.setItem("secret", "");
			localStorage.setItem("merchantId", "");
			localStorage.setItem("merchantPassword", "");
			localStorage.setItem("logged",false);
			$scope.merchantUser=null;
			$window.location.href = "/#/home"
		}
		else{
			return;
		}
					
	}

	$scope.registerAccount=function(password,cardHolder,role,cardUsername){
			
		var account={
			"pan": location.port,
			"securityCode": password,
			"cardHolderName": cardHolder,
			"expirationDate": "",
			"availableFunds": 0,
			"reservedFunds": 0
		  };

		var accountJson = JSON.stringify(account);

		var merchantAcc={
			"merchantId": "secret",
			"merchantPassword": "password",
			"username": cardUsername,
			clientAccount : account
		}
		var merchantJson = JSON.stringify(merchantAcc);

		appService.registerAccount(accountJson,merchantJson,role).then(function(response){
			console.log(response);
		})


	}
	
	
	$scope.getBank=function(){
		appService.getBank(location.port).then(function(response){
				$scope.bank=response.data;	
		})
	}

	$scope.paymentInit=function(){
		$scope.paymentId = $location.absUrl().split('?')[1];
		appService.getPayment($scope.paymentId).then(function(response){
			console.log(response.data);
			$scope.payment=response.data;
		})
	}

	$scope.pay=function(cardHolderName,pan,password,expdate){
		var transferRequest={
			"buyer":{ 
				"pan": pan,
				"securityCode": password,
				"cardHolderName": cardHolderName,
				"expirationDate": expdate,
				"availableFunds": 0,
				"reservedFunds": 0
		},
			"payment":$scope.payment 
		}
		var requestJSON = JSON.stringify(transferRequest);

		appService.pay(requestJSON).then(function(response){
			$scope.transaction=response.data;
			console.log(response.data)
		})

	}
	
}]);