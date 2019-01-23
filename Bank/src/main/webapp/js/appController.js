var app=angular.module('app.controllers',[]);


app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.init=function(){
		if(localStorage.getItem("logged")=="true"){
			$scope.login(localStorage.getItem("pan"),localStorage.getItem("secret"));
			$scope.getBank();
			$("#logoutBtn").show();
		}else{
			$("#logoutBtn").hide();
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
	
	$scope.logout=function(){
		if(localStorage.getItem("logged")=="true"){
			localStorage.setItem("pan", "");
			localStorage.setItem("secret", "");
			localStorage.setItem("logged",false);
			$window.location.href = "/#/home"
		}
		else{
			return;
		}
					
	}
	
	
	$scope.getBank=function(){
		appService.getBank("8082").then(function(response){
				$scope.bank=response.data;	
		})
	}
	
}]);