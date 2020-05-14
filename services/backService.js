var backService = angular.module('backService', []);
backService.factory('getProduits',function ($http) {

    return function (motcle,page,size) {

        return $http.get('http://localhost:8080/produits?mc='+motcle+'&page='+page+'&size='+size,
            {headers :{'Content-Type': 'application/json'}})
    }
});

backService.factory('saveProduit',function ($http) {

    return function (produit) {

        return $http.post('http://localhost:8080/saveProduit',produit)

    }
});

backService.factory('getProduit',function ($http) {

    return function (id) {

        return $http.get('http://localhost:8080/produit/'+id)
    }
});
