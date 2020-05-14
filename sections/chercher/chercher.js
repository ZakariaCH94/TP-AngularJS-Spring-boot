var chercherRouteControllers = angular.module('chercherRouteControllers', []);
chercherRouteControllers.controller('chercherController',function ($scope,$http,$location,getProduits) {

    $scope.pages=[];
    $scope.pageProduits=null;
    $scope.motcle='';
    $scope.size=5;
    $scope.currentpage=0;

    $scope.chercherProduits=function() {
        getProduits($scope.motcle,$scope.currentpage,$scope.size)
            .then(function (data) {
                $scope.pageProduits=data;
                $scope.pages=new Array(data.data.totalPages);
                console.log(data.data);
            },function(error){
                console.log(error);
            });

    }

    $scope.goToPage=function (i) {
        $scope.currentpage=i;
        $scope.chercherProduits();

    }

    $scope.goTochercherProduits=function () {
        $scope.currentpage=0;
        $scope.chercherProduits();

    }


    $scope.goToEdit=function (id) {
      $location.path('/editProduit/'+id);

    }

});
