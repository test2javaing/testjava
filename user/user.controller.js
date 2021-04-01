app.controller('UserController', function ($scope, $location, $http, $cookies, $sce, $routeParams, $route, $rootScope) {
    $scope.login = function() {
        let user = {
            login : $scope.mail,
            mdp : $scope.mdp
        }

        $http.post(base_url + "user/login", JSON.stringify(user))
        .then(function (response) {
            $cookies.putObject('user', response.data.data);
            $rootScope.userName = response.data.data.nom;
            $location.path('/voiture');
        }).catch(function (error) {
            $scope.error = error.data.message;
            $location.path('/login');
        });
    }

    $scope.signin = function() {
        let user = {

            login : $scope.mail,
            mdp : $scope.mdp
        }

        $http.post(base_url + "user/login", JSON.stringify(user))
        .then(function (response) {
            $cookies.putObject('user', response.data.data);
            $rootScope.error = "";
            $rootScope.userName = response.data.data.nom;
            $location.path('/voiture');
        }).catch(function (error) {
            $scope.error = error.data.message;
            $location.path('/login');
        });
    }
});