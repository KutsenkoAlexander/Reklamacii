angular.module('reclamaciiApp.add.solution.defect.form', ['ngResource'])

    .factory('deleteSolutionDefectFactory', function($resource){
        return $resource('/defect_solution/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .factory('getSolutionDefectFactory', function($resource){
        return $resource('/defect_solution/:id', {id: '@id'},{'query': {method:'GET', isArray:false}});
    })

    .factory('addSolutionFormService', function($rootScope){
        var solutionList = [];
        solutionList.add = function(id, solutionName, executor, appointmentDate, doneDate){
            solutionList.push({
                "idDefectSolution": id,
                "measures": solutionName,
                "executor": executor,
                "appointmentDate": appointmentDate,
                "completeDate": doneDate
            });
        };
        solutionList.deleteSolution = function(id){
            solutionList.splice(id, 1);
            if(solutionList.length === 0){
                $rootScope.$broadcast('solutionListBroadcast',{
                    solutionList: null
                });
            }
        };
        solutionList.setNewSolutionList = function(newSolutionList){
            solutionList.splice(0, solutionList.length);
            if(solutionList.length === 0){
                angular.forEach(newSolutionList, function(value, key) {
                    solutionList.push(value);
                });
            }
        };
        solutionList.clear = function(){
            solutionList.splice(0, solutionList.length);
        };
        return solutionList;
    })

    .controller('addSolutionFormCtrl', function ( $rootScope,
                                                  $scope,
                                                  param,
                                                  allClassDefect,
                                                  pageCacheFactory,
                                                  addSolutionFormService,
                                                  $uibModalInstance,
                                                  getSolutionDefectFactory ) {
        if(param.id != null){
            $scope.editSolutionDefect = true;
            getSolutionDefectFactory.query({id: param.id}).$promise.then(function(data){
                $scope.measures = data.measures;
                $scope.executor = data.executor;
                $scope.appointmentDate = data.appointmentDate;
                $scope.completeDate = data.completeDate;
                $rootScope.$broadcast('solutionExecutorBroadcast',{
                    solutionExecutor: data.executor.name
                });
            });
        }

        $scope.addSolution = function(measures, executor, appointmentDate, doneDate, editSolutionDefect){
            if(editSolutionDefect){
                addSolutionFormService.deleteSolution(param.index);
            }
            addSolutionFormService.add(param.id, measures, executor, appointmentDate, doneDate);
            $rootScope.$broadcast('solutionListBroadcast',{
                solutionList: addSolutionFormService
            });
            $scope.measures = null;
            $scope.executor = null;
            $scope.appointmentDate = null;
            $scope.completeDate = null;
            $uibModalInstance.dismiss('cancel');
        };

        $scope.cancelAddSolutionForm = function(){
            $uibModalInstance.dismiss('cancel');
        };

        $scope.$on('searchExecutorResultBroadcast', function(event, args) {
            if (args.searchExecutorResult.length === 0 || !args.searchExecutorResult) {
                $scope.showButtonAddExecutor = true;
            } else {
                $scope.showButtonAddExecutor = false;
            }
        });

        $scope.$on('searchStrInputBroadcast', function(event, args){
            if(args.searchStrInput === null || args.searchStrInput){
                $scope.showButtonAddExecutor = false;
            }
        });

        $scope.$on('executorItemBroadcast', function(event, args){
            $scope.executor = args.executorInput;
            $scope.showButtonAddExecutor = false;
        })

    })

    .directive('datepickerExecutorAppointmen', function($document, $rootScope) {
        return {
            link: function (scope, element, attr, ctrl) {
                $(function () {
                    $.datepicker.setDefaults(
                        $.extend($.datepicker.regional['ru'])
                    );
                    $(".datepicker").datepicker();
                })
            }
        }
    })

    .directive('datepickerExecutorDone', function($document, $rootScope) {
        return {
            link: function (scope, element, attr, ctrl) {
                $(function () {
                    $.datepicker.setDefaults(
                        $.extend($.datepicker.regional['ru'])
                    );
                    $(".datepicker").datepicker();
                })
            }
        }
    });
