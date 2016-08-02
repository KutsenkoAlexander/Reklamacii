angular.module('reclamaciiApp.consumer', ['ngResource'])

    .factory('saveConsumerFactory', function($resource){
        return $resource('/consumer', {},{'save': {method:'POST'}});
    })

    .controller('consumerCtrl', function($rootScope, $scope, saveConsumerFactory){
        $scope.$on('searchTextBroadcast', function(event, args){
            $scope.searchText = args.searchText;
        });

        $scope.addNewConsumer = function(){
            var consumer = {
                "name": $scope.searchText
            };
            saveConsumerFactory.save(consumer).$promise.then(function(data){
                var consumer = {
                    "idConsumer": data.idConsumer,
                    "name": data.name
                };
                $rootScope.$broadcast('consumerItemBroadcast', {
                    consumerInput: consumer
                });
                alert("Потребитель добавлен в базу!");
            })
        };
    });
