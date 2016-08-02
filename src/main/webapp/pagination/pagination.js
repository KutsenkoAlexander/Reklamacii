angular.module('reclamaciiApp.pagination', ['ngResource'])

    .controller('paginationCtrl', function ($scope, $rootScope, paramsFactory, $location, sortFactory){
        $scope.$on('filterEvent', function (event, args) {
            $scope.content = args.content;
            $scope.page = args.page;
            angular.forEach(args.links, function (value) {
                if (value.rel === 'next') {
                    $scope.nextPageLink = value.href;
                }
                if (value.rel === 'prev') {
                    $scope.prevPageLink = value.href;
                }
            });
        });
        $scope.setPageAndSize = function(page){
            paramsFactory.page = page;
            $location.search("page", page || null);
            sortFactory.query(paramsFactory).$promise.then(function (data) {
                $rootScope.$broadcast("paginationProducts", {
                    content: data.content
                });
                $scope.page = data.page;
                $scope.idCategoryProducts = paramsFactory.id;
                angular.forEach(data.links, function (value) {
                    if (value.rel === 'next') {
                        $scope.nextPageLink = value.href;
                    }
                    if (value.rel === 'prev') {
                        $scope.prevPageLink = value.href;
                    }
                });
            })
        };
        $scope.setPageAndSize(0);
    })

    .filter('pages', function () {
        return function (input, totalPages) {
            totalPages = parseInt(totalPages);
            for (var i = 0; i <= totalPages-1; i++) {
                input.push(i);
            }
            return input;
        };
    });