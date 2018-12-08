var app=angular.module('app.controllers',[]);

app.controller('appController',['$http','$window','$location','$rootScope','$scope',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.izaberi=function(opcija){
		$scope.opcija=opcija;
		alert($scope.opcija);
	}
	
	
}]);