const base_url = "http://127.0.0.1:8080/";
const send = "/login";

var app = angular.module("test", ['ngRoute','ngCookies']);

app.config(function($routeProvider) {
	$routeProvider
		.when('/',{
			redirectTo: '/voiture'
        })
        .when('/voiture', {
			templateUrl: 'voiture/voiture.html',
			controller: 'VoitureController'
        })
		.when('/voiture/ajouter', {
			templateUrl: 'voiture/ajouter.html',
			controller: 'VoitureController'
        })
		.when('/:id/comment', {
			templateUrl: 'comment/comment.html',
			controller: 'VoitureController'
		})
        .when('/signin', {
			templateUrl: 'user/signin.html',
			controller: 'UserController'
		})
		.when('/login', {
			templateUrl: 'user/login.html',
			controller: 'UserController'
		})
		.when('/logout', {
			templateUrl: 'user/login.html',
			controller: 'LogoutController'
		})
});

app.filter('replace_zero', function() {
	return function (text) {
		text = text.toString();
		if (text.length > 1) {
			return text;
		}
		
		return text.replace("0", "-");
	}
})

app.controller('IndexController', function ($scope, $cookies) {
    var user = $cookies.getObject("user");
    if (user != undefined) {
        $scope.userName = user.nom;
    } else {
		$scope.userName = 'User';
	}
});