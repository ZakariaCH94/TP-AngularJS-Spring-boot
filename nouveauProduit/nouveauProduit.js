var nouveauProduitRouteControllers = angular.module('nouveauProduitRouteControllers', []);
nouveauProduitRouteControllers.controller('nouveauProduitController',function ($scope,$http,saveProduit) {

    $scope.produit={};
    $scope.mode=1;

    $scope.saveProduit=function () {
        saveProduit($scope.produit)
            .then(function (data) {
                $scope.produit=data.data;
                console.log($scope.produit);
                $scope.mode=2;

            },function(error){
                console.log(error);
            });
    }

    $scope.changemode=function () {
        $scope.mode=1;
    }

});
 
