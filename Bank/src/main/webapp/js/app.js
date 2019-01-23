var app= angular.module('app',['ui.router','app.services','app.controllers']);

app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state('index', {
		url:'/',
		templateUrl:'views/home.html',
		controller:'appController'
	}).state('profile',{
		url:'/profile',
		templateUrl:'views/profile.html',
		controller:'appController'
	}).state('paymentInput',{
		url:'/paymentInput',
		templateUrl:'views/paymentInput.html',
		controller:'appController'
	});
	$urlRouterProvider.otherwise('/#');
	
	
});