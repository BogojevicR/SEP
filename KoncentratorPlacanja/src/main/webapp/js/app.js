var app= angular.module('app',['ui.router','app.services','app.controllers']);

app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state('index', {
		url:'/',
		templateUrl:'views/nacinPlacanja.html',
		controller:'appController'
	})
	.state('paypal',{
		url:'/paypal',
		templateUrl:'views/paypal.html',
		controller:'appController'
	}).state('sucess',{
		url:'/sucess',
		templateUrl:'views/sucess.html',
		controller:'appController'
	}).state('racun',{
		url:'/racun',
		templateUrl:'views/racun.html',
		controller:'appController'
	});
	$urlRouterProvider.otherwise('/#');
	
	
});