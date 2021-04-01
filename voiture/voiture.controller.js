app.controller('VoitureController', function ($scope, $location, $http, $cookies, $sce, $routeParams, $route, $rootScope) {
    let c_id = $routeParams.id;
    var user = $cookies.getObject("user");
    var liste_vtr = [];
    var liste_coms = [];
    if (c_id == undefined) {
        $http({
            method: "GET",
            url: base_url + "voiture"
        }).then(function (response) {
            $scope.voitures = response.data.data;
            liste_vtr = liste_vtr.concat(response.data.data);
        }).catch(function (error) {
            console.log(error);
        }); 

    } else { 
        if (user != undefined) {
            $http({
                method: "GET",
                url: base_url + "comment"
            }).then(function (response) {
                $scope.coms = response.data.data;
                liste_coms = liste_coms.concat(response.data.data);
            }).catch(function (error) {
                console.log(error.data.data);
            });

            $scope.comment = function (){
                let comment = {
                    id_usr: user.id,
                    voiture: c_id,
                    value: $scope.newCom
                }
                
                $http.post(base_url + "comment/add", JSON.stringify(comment))
                .then(function (response) {
                    liste_coms.push(response.data.data);
                    $scope.coms = liste_coms;
                    $route.reload();
                }).catch(function (error) {
                    console.log(error.data.data);
                    $scope.error = error.data.message;
                });
            }
        } else {
            $rootScope.error = "Vous devez vous connecter avant de pouvoir commenter";
            $location.path(send);
        }
    }


    $scope.getVoitureById = function () {
        for (i = 0; i < liste_vtr.length; i++) {
            if (liste_vtr[i].id.localeCompare(c_id) == 0) {
                $scope.vtr = liste_vtr[i];
                return liste_vtr[i];
            }
        }
    };
});