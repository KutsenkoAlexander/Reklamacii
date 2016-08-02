angular.module('reclamaciiApp.add.cause.defect.form', ['ngResource'])

    .factory('deleteCauseDefectFactory', function($resource){
        return $resource('/defect/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .factory('addCauseFormService', function($rootScope){
        var causeList = [];
        causeList.add = function(causeName, classDefect){
            causeList.push({
                "cause": causeName,
                "sprClassDefect": classDefect
            });
        };
        causeList.deleteCause = function(id){
            causeList.splice(id, 1);
            if(causeList.length === 0){
                $rootScope.$broadcast('causeListBroadcast',{
                    causeList: null
                });
            }
        };
        causeList.setNewCausesList = function(newCausesList){
            causeList.splice(0, causeList.length);
            if(causeList.length === 0){
                angular.forEach(newCausesList, function(value, key) {
                    causeList.push(value);
                });
            }
        };
        causeList.clear = function(){
            causeList.splice(0, causeList.length);
        };
        return causeList;
    })

    .controller('addCauseFormCtrl', function ($rootScope,
                                              $scope,
                                              allClassDefect,
                                              pageCacheFactory,
                                              addCauseFormService,
                                              $uibModalInstance) {
        $scope.dataClassDefects = pageCacheFactory.get("class_defect");
        if (!$scope.dataClassDefects) {
            $scope.dataClassDefects = allClassDefect.query();
            pageCacheFactory.put("class_defect", $scope.dataClassDefects);
        }

        $scope.addCause = function(){
            addCauseFormService.add($scope.causeName, $scope.classDefect);
            $rootScope.$broadcast('causeListBroadcast',{
                causeList: addCauseFormService
            });
            $scope.causeName = null;
            $scope.classDefect = null;
            $uibModalInstance.dismiss('cancel');
        };

        $scope.cancelAddCauseForm = function(){
            $uibModalInstance.dismiss('cancel');
        };

    });
