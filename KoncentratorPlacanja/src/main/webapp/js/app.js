var app= angular.module('app',['ui.router','app.services','app.controllers']);

app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state('index', {
		url:'',
		templateUrl:'views/nacinPlacanja.html',
		controller:'appController'
	})
	.state('nacinPlacanja',{
		url:'/nacinPlacanja',
		templateUrl:'views/nacinPlacanja.html',
		controller:'appController'
	});
	$urlRouterProvider.otherwise('/');
	
	
});