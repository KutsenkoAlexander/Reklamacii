var reclamacii = angular.module('reclamaciiApp', [
    'ui.router',
    'ui.bootstrap',
    'ngResource',
    'ngCookies',
    'angucomplete',
    'AngularPrint',
    'angular.filter',
    'reclamaciiApp.record',
    'reclamaciiApp.home',
    'reclamaciiApp.edit',
    'reclamaciiApp.executor',
    'reclamaciiApp.consumer',
    'reclamaciiApp.pagination',
    'reclamaciiApp.print.item',
    'reclamaciiApp.add.cause.defect.form',
    'reclamaciiApp.add.solution.defect.form'
]);

reclamacii.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', '$compileProvider',
    function ($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider, $compileProvider) {
        $locationProvider.html5Mode(true);
        $httpProvider.useApplyAsync(true);
        $compileProvider.debugInfoEnabled(false);
}]);

reclamacii.run(function($rootScope){
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){
        $rootScope.pageTitle = toState.data.pageTitle;
        $rootScope.currentPath = toState.url;
        $rootScope.state = toState.name;
    });
});

reclamacii.factory('pageCacheFactory', function ($cacheFactory) {
    return $cacheFactory('pageCache', {});
});

reclamacii.factory('msWordFactory', function($resource){
    return $resource('/msword/:id', {id: '@id'},{'query': {method:'GET', isArray:false}});
});

reclamacii.controller('mainCtrl', function ($rootScope, $scope, $cookies, $location, $window, sortFactory, paramsFactory, msWordFactory, $http) {
    $scope.editable = false;

    $scope.$on("paginationProducts", function(event, args){
        $scope.items = args;
        $scope.page = args.page;
    });

    $scope.$on('filterEvent', function(event, args){
        $scope.items = args;
    });

    $scope.$on('editableBroadcast', function(event, args){
        $scope.editable = args.editable;
        $scope.titleWindow = args.new;
        if(args.update){
            $scope.items = sortFactory.query(paramsFactory);
        }
    });

    $rootScope.$broadcast('checkedBroadcast', {checked: true});
    $scope.$on('checkedBroadcast', function(event, args){
        $scope.chk = args.checked;
    });

    $scope.printItem = function(item){
        $rootScope.$broadcast('itemPrintBroadcast',{
            itemPrint: item
        });
    };

    $scope.downloadWordItem = function(id){
        $http({
            url: '/msword/'+id,
            method: "GET",
            responseType: 'arraybuffer'
        }).success(function (data, status, headers, config) {
            var file = new Blob([data], {type: 'application/msword'});
            var fileURL = URL.createObjectURL(file);
            window.open(fileURL);
        }).error(function (data, status, headers, config) {
            //upload failed
        });
    };

    $scope.logout = function(){
        location.reload();
        $location.path("/logout");
    }
});
