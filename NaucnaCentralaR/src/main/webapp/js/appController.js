var app=angular.module('app.controllers',[]);


app.controller('appController',['$http','$window','$location','$rootScope','$scope','appService',
    function($http,$window,$location,$scope,$rootScope, appService) {  
	
	$scope.init=function(){
		$scope.roles = ["KUPAC", "AUTOR"];
		$scope.oblasti = ["Fizika", "Hemija","Matematika","Biologija","Medicina"];
		if(localStorage.getItem("logged")=="true"){
			if(localStorage.getItem("emaillogin")!="")
			$scope.login(localStorage.getItem("emaillogin"),localStorage.getItem("emailpassword"));
			$("#logoutBtn").show();
		}else{
			$("#logoutBtn").hide();
		}
		$scope.getAllCasopisi();
	}
	
	$scope.login=function(emaillogin,emailpassword){
		appService.login(emaillogin,emailpassword).then(function(response){
				$scope.user=response.data;
				if(response.data!=""){
					localStorage.setItem("emaillogin", emaillogin);
					localStorage.setItem("emailpassword", emailpassword);
					localStorage.setItem("logged",true);
					if($scope.user.uloga=="KUPAC"){
						$window.location.href = "/#/kupac"
						
					}else{
						$window.location.href = "/#/autor"
					}
					
				}else{
					alert("Wrong Login!");
					return
				}
					
		})
	}
	
	$scope.logout=function(){
		if(localStorage.getItem("logged")=="true"){
			localStorage.setItem("emaillogin", "");
			localStorage.setItem("emailpassword", "");
			localStorage.setItem("logged",false);
			$scope.user=null;
			$window.location.href = "/#/home"
		}
		else{
			return;
		}
					
	}

	$scope.registerAccount=function(email,ime,prezime,grad,drzava,password,role){
		var account={
			"email": email,
			"ime": ime,
			"prezime": prezime,
			"grad": grad,
			"drzava": drzava,
			"lozinka": password,
			"uloga": role,
			"profulKupca":null
		  };
		var accountJson = JSON.stringify(account);

		appService.registerAccount(accountJson).then(function(response){
		})


	}
	
	$scope.getAllCasopisi=function(){
		appService.getAllCasopisi().then(function(response){
			$scope.casopisi=response.data;
		})
	}

	$scope.dodajRad=function(id){
		$location.path("noviRad" );
		$scope.casopisId=id;
	}

	$scope.dodajRadUCasopis=function(naslov,kljucniPojmovi,apstrakt,naucnaOblast,pdf){
		var rad={
			"id":$scope.casopisId,
			"naslov": naslov,
			"kljucniPojmovi": kljucniPojmovi,
			"apstrakt": apstrakt,
			"naucnaOblast": naucnaOblast,
			"pdf": pdf
		  };
		  var radJSON = JSON.stringify(rad);

		appService.dodajRadUCasopis(radJSON).then(function(response){
			$location.path("autor");
		})
	}

	$scope.kupiCasopis=function(id){
		$window.location.href ="http://localhost:8081/#/?"+id+"&"+$scope.user.email;
	}

	$scope.pretplatiSe=function(id){
		$window.location.href ="http://localhost:8081/#/paypalSubscription?"+id+"&"+$scope.user.email;
	}
	
	
}]);