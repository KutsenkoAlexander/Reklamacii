angular.module('reclamaciiApp.record', ['ui.router'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('record', {
                url: '/record',
                views: {
                    'content': {
                        templateUrl: 'record_operations/record.html',
                        controller: 'recordCtrl'
                    }
                },
                data: {
                    pageTitle: 'Работа с записями'
                }
            });

    })

    .controller('recordCtrl', function ($rootScope, $scope, $location, pageCacheFactory, $http) {
        $scope.dataPage = pageCacheFactory.get("record");
        if(!$scope.dataPage){
            pageCacheFactory.put("record", $scope.dataPage);
        }

        $scope.$on('listRecordIdBroadcast', function(event, args){
            $scope.listRecordId = args.listRecordIdBroadcast;
        });

        $scope.deleteSelected = function(listRecordId){
            var result = confirm('Удалить отмеченные рекламации?');
            if(result){
                $http.post("/record/delete", listRecordId).then(
                    function (response) {
                        // success callback
                        $scope.listRecordId.splice(0, listRecordId.length);
                        $rootScope.$broadcast("editableBroadcast", {
                            editable: false,
                            update: true
                        });
                    },
                    function (response) {
                        // failure callback
                        alert(response.statusText);
                    });
            }
        };

        $scope.msWord = function(listRecordId){
            $http({
                url: '/msword',
                method: "POST",
                responseType: 'arraybuffer',
                data: listRecordId
            }).success(function (data, status, headers, config) {
                var file = new Blob([data], {type: 'application/msword'});
                var fileURL = URL.createObjectURL(file);
                window.open(fileURL);
            }).error(function (data, status, headers, config) {
                //upload failed
            });
        }
    });
