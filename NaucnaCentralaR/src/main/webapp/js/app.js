var app= angular.module('app',['ui.router','app.services','app.controllers']);

app.config(function($stateProvider,$urlRouterProvider) {
	$stateProvider
	.state('index', {
		url:'/',
		templateUrl:'views/home.html',
		controller:'appController'
	}).state('kupac',{
		url:'/kupac',
		templateUrl:'views/kupac.html',
		controller:'appController'
	}).state('autor',{
		url:'/autor',
		templateUrl:'views/autor.html',
		controller:'appController'
	}).state('noviRad',{
		url:'/noviRad',
		templateUrl:'views/noviRad.html',
		controller:'appController'
	}).state('profil',{
		url:'/profil',
		templateUrl:'views/profil.html',
		controller:'appController'
	});
	$urlRouterProvider.otherwise('/#');
	
	
});