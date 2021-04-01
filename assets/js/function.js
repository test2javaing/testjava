function authenticate($scope, $location, $cookies, $http, $rootScope) {
    var token = $cookies.getObject("token");
    if (token == undefined) {
        return false;
    }
    const bearerToken = "Bearer " + token.value;
    // $rootScope.display = false;

    var httpOptions = {
        headers: {
            "Authorization": bearerToken,
            "Accept": "application/json"
        }
    };

    $http.post(base_url + "user/token/check", JSON.stringify(token), httpOptions)
    .then(function success(response) {
        data = response.data.data;
        $scope.result = data;
    }).catch (function (error) {
        $rootScope.error = error.data.message;
        $location.path('/rapport');
    });

    return true;
}

function checkAdmin($scope, $location, $cookies, $http) {
    var token = $cookies.getObject("token");
    if (token == undefined) {
        return false;
    }
    const bearerToken = "Bearer " + token.value;
    // $rootScope.display = false;

    var httpOptions = {
        headers: {
            "Authorization": bearerToken,
            "Accept": "application/json"
        }
    };

    $http.post(base_url + "user/token/checkAdmin", JSON.stringify(token), httpOptions)
    .then(function success(response) {
        data = response.data.data;
        $scope.result = data;
    }).catch (function (error) {
        $scope.error = error.data.message;
        $location.path('/rapport');
    });

    return true;
}