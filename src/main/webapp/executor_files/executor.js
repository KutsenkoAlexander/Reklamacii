angular.module('reclamaciiApp.executor', ['ngResource'])

    .factory('saveExecutorFactory', function($resource){
        return $resource('/executor', {},{'save': {method:'POST'}});
    })

    .controller('executorCtrl', function($rootScope, $scope, saveExecutorFactory){
        $scope.$on('searchTextBroadcast', function(event, args){
            $scope.searchText = args.searchText;
        });

        $scope.addNewExecutor = function(){
            var executor = {
                "name": $scope.searchText
            };
            saveExecutorFactory.save(executor).$promise.then(function(data){
                var executor = {
                    "idExecutor": data.idExecutor,
                    "name": data.name
                };
                $rootScope.$broadcast('executorItemBroadcast', {
                    executorInput: executor
                });
                alert("Исполнитель добавлен в базу!");
            })
        };
    });
