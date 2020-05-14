var appRoute=angular.module('appRoute',['ngRoute']);
appRoute.config(function config($routeProvider) {
        $routeProvider.
        when('/home', {
            templateUrl: 'sections/chercher/chercher.html',
            controller:'chercherController'
        }).
        when('/newProduit', {
            templateUrl: 'sections/nouveauProduit/nouveauProduit.html',
            controller:'nouveauProduitController'
        }).
        when('/editProduit/:id', {
            templateUrl: 'sections/editProduit/editProduit.html',
            controller:'editProduitController'
        }).
        otherwise('/home');

    }
);



