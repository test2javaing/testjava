app.controller('LogoutController', function ($location, $cookies, $rootScope) {
    $cookies.remove("user");
    $rootScope.userName = "User";
    $location.path(send);
});