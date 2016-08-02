angular.module('reclamaciiApp.edit', ['ngResource'])

    .factory('saveRecordFactory', function($resource){
        return $resource('/record', {},{'save': {method:'POST'}});
    })

    .factory('editRecordFactory', function($resource){
        return $resource('/record/:id', {id: '@id'},{'query': {method:'GET', isArray:false}});
    })

    .factory('deleteRecordFactory', function($resource){
        return $resource('/record/:id', {id: '@id'},{'delete': { method: 'DELETE' }});
    })

    .factory('deleteListRecordFactory', function($resource){
        return $resource('/record/delete', {},{'delete': { method: 'DELETE' }});
    })

    .controller('editCtrl', function ($rootScope,
                                      $scope,
                                      $http,
                                      $location,
                                      pageCacheFactory,
                                      allTypeProduct,
                                      allConsumer,
                                      allOenExecutor,
                                      allClassDefect,
                                      $uibModal,
                                      addCauseFormService,
                                      addSolutionFormService,
                                      saveRecordFactory,
                                      editRecordFactory,
                                      deleteRecordFactory,
                                      deleteCauseDefectFactory,
                                      deleteSolutionDefectFactory) {
        $scope.dataTypes = pageCacheFactory.get("type");
        if(!$scope.dataTypes){
            $scope.dataTypes = allTypeProduct.query();
            pageCacheFactory.put("type", $scope.dataTypes);
        }

        $scope.dataConsumers = pageCacheFactory.get("consumer");
        if(!$scope.dataConsumers){
            $scope.dataConsumers = allConsumer.query();
            pageCacheFactory.put("consumer", $scope.dataConsumers);
        }

        $scope.dataOenExecutors = pageCacheFactory.get("oen_executor");
        if(!$scope.dataOenExecutors){
            $scope.dataOenExecutors = allOenExecutor.query();
            pageCacheFactory.put("oen_executor", $scope.dataOenExecutors);
        }

        $scope.dataClassDefects = pageCacheFactory.get("class_defect");
        if(!$scope.dataClassDefects){
            $scope.dataClassDefects = allClassDefect.query();
            pageCacheFactory.put("class_defect", $scope.dataClassDefects);
        }

        $scope.listRecordId = [];
        $scope.check_all_items = function(){
            if($scope.checkedAll) {
                $scope.checked = true;
                $rootScope.$broadcast('checkedBroadcast', {checked: true});
                angular.forEach($scope.items.content, function (item) {
                    $rootScope.$broadcast('listAddItemBroadcast', {item: item});
                });
            } else {
                $scope.checked = false;
                $rootScope.$broadcast('checkedBroadcast', {checked: false});
                angular.forEach($scope.items.content, function (item) {
                    $rootScope.$broadcast('listRemoveItemBroadcast', { item: item})
                });
            }
            angular.forEach($scope.items.content, function (item) {
                item.checked = $scope.checkedAll;
                ($scope.listRecordId.indexOf(item.idRecord) === -1 && $scope.checked) ?
                    $scope.listRecordId.push(item.idRecord) :
                    $scope.listRecordId.splice($scope.listRecordId.indexOf(item.idRecord), 1);
                $rootScope.$broadcast('listRecordIdBroadcast', { listRecordIdBroadcast: $scope.listRecordId})
            });
        };

        $scope.addItemToList = function(id, checked, item){
            if($scope.listRecordId.indexOf(id) === -1 && checked) {
                $scope.listRecordId.push(id);
                $rootScope.$broadcast('listAddItemBroadcast', { item: item})
            } else {
                $scope.listRecordId.splice($scope.listRecordId.indexOf(id), 1);
                $rootScope.$broadcast('listRemoveItemBroadcast', { item: item})
            }
            $rootScope.$broadcast('listRecordIdBroadcast', { listRecordIdBroadcast: $scope.listRecordId})
        };

        //name product
        $scope.$on('nameItemBroadcast', function(event, args){
            $scope.nameItem = args.nameInput;
        });

        //consumer
        $scope.$on('searchConsumerResultBroadcast', function(event, args) {
            if (args.searchConsumerResult.length === 0 || !args.searchConsumerResult) {
                $scope.showButtonAddConsumer = true;
            } else {
                $scope.showButtonAddConsumer = false;
            }
        });

        $scope.$on('searchConsumerBroadcast', function(event, args){
            if(args.searchStrInput === null || args.searchStrInput){
                $scope.showButtonAddConsumer = false;
            }
        });

        //consumer for save to db
        $scope.$on('consumerItemBroadcast', function(event, args){
            $scope.consumerItem = args.consumerInput;
            $scope.showButtonAddConsumer = false;
        });

        //manage causes
        $scope.$on('causeListBroadcast', function(event, args){
            $scope.causeList = args.causeList;
        });

        $scope.showCreateCauseForm = function(){
            $uibModal.open({
                templateUrl:'../add_cause/add.cause.tmpl.html',
                controller: 'addCauseFormCtrl'
            });
        };

        $scope.deleteCause = function(id, idDefect, name){
            var result = confirm('Удалить дефект \"'+name+'\" ?');
            if(result){
                if(angular.isDefined(idDefect)) {
                    deleteCauseDefectFactory.delete({id: idDefect},
                        function (resp, headers) {
                            //success callback
                        },
                        function (err) {
                            // error callback
                            alert(err.statusText);
                        }
                    );
                }
                addCauseFormService.deleteCause(id);
            }
        };

        //manage solutions
        $scope.$on('solutionListBroadcast', function(event, args){
            $scope.solutionList = args.solutionList;
        });

        $scope.showCreateSolutionForm = function(){
            $uibModal.open({
                templateUrl:'../add_solution/add.solution.tmpl.html',
                controller: 'addSolutionFormCtrl',
                resolve: {
                    param: function () {
                        return {'id' : null };
                    }
                }
            });
        };

        $scope.deleteSolution = function(id, idSolution, name){
            var result = confirm('Удалить решение \"'+name+'\" ?');
            if(result){
                if(angular.isDefined(idSolution)) {
                    deleteSolutionDefectFactory.delete({id: idSolution},
                        function (resp, headers) {
                            //success callback
                        },
                        function (err) {
                            // error callback
                            alert(err.statusText);
                        }
                    );
                }
                addSolutionFormService.deleteSolution(id);
            }
        };

        $scope.editSolution = function(index, id){
            $uibModal.open({
                templateUrl:'../add_solution/add.solution.tmpl.html',
                controller: 'addSolutionFormCtrl',
                resolve: {
                    param: function () {
                        return {'index': index, 'id' : id };
                    }
                }
            });
        };

        //item
        $scope.add = function(){
            $rootScope.$broadcast("editableBroadcast", {
                editable: true,
                new: true
            });
            //date
            $scope.idRecord = null;
            $scope.dateAddItem = null;
            //executor
            $scope.oenExecutor = null;
            $scope.$watch('oenExecutor', function(){
                angular.element('#oenExecutor').val('');
            });
            //product type
            $scope.type = null;
            $scope.$watch('type', function(){
                angular.element('#type').val('');
            });
            //product name
            $scope.nameItem = null;
            //product number
            $scope.numberProduct = null;
            //consumer
            $rootScope.$broadcast('editItemConsumerBroadcast', {
                editItemConsumerBroadcast: ''
            });
            $scope.ctp = null;
            //fixed solution defect
            $scope.solution = null;
            //act
            $scope.act = null;
            //causes list
            addCauseFormService.clear();
            $scope.causeList = addCauseFormService;
            //solution list
            addSolutionFormService.clear();
            $scope.solutionList = addSolutionFormService;
            //recoveryProduct
            $scope.idRecoveryProduct = null;
            $scope.dateAppoinedRecoveryProduct = null;
            $scope.dateToRecoveryProduct = null;
            //shipment
            $scope.idShipment = null;
            $scope.dateAppoinedShipmentProduct = null;
            $scope.dateToShipmentProduct = null;
            $scope.waybill = null;
            //result reklamaciya
            $scope.chekedReklamacia = null;
        };

        $scope.edit = function(id){
            $rootScope.$broadcast("editableBroadcast", {
                editable: true,
                new: false
            });
            $scope.editableItem = editRecordFactory.query({id: id}).$promise.then(function(data){
                //date
                $scope.idRecord = data.idRecord;
                $scope.dateAddItem = new Date(data.entryDate*1000).toLocaleDateString();
                $scope.$watch('dateAddItem',function(){
                    angular.element('#dateAddItem').val($scope.dateAddItem);
                });
                //executor
                angular.forEach($scope.dataOenExecutors, function(value, key) {
                    if(value.usersByIdUser.details === data.user){
                        $scope.oenExecutor = value;
                    }
                });
                //product type
                angular.forEach($scope.dataTypes, function(value, key) {
                    if(value.name === data.ctp.productType.name){
                        $scope.type = data.ctp.productType;
                    }
                });
                //product name
                $scope.nameItem = data.productName;
                $rootScope.$broadcast('productNameBroadcast', {
                    productNameBroadcast: data.productName
                });
                //product number
                $scope.numberProduct = data.number;
                $scope.$watch('numberProduct', function(){
                    angular.element("#numberProduct").val($scope.numberProduct);
                });
                //consumer
                $rootScope.$broadcast('editItemConsumerBroadcast', {
                    editItemConsumerBroadcast: data.ctp.consumer.name
                });
                $scope.ctp = data.ctp;
                //solution to fix defect
                $scope.solution = data.solutions;
                $scope.$watch('solution', function(){
                    angular.element("#solution").val($scope.solution);
                });
                //act
                $scope.act = data.actNumber;
                $scope.$watch('act', function(){
                    angular.element("#act").val($scope.act);
                });
                //causes list
                addCauseFormService.setNewCausesList(data.defects);
                $scope.causeList = addCauseFormService;
                //solution list
                addSolutionFormService.setNewSolutionList(data.defectSolutions);
                $scope.solutionList = addSolutionFormService;
                //recoveryProduct
                $scope.idRecoveryProduct = data.recoveryProduct.idRecoveryProduct;
                $scope.dateAppoinedRecoveryProduct = data.recoveryProduct.appointmentRecoveryDate;
                $scope.$watch('dateAppoinedRecoveryProduct', function(){
                    angular.element("#dateAppoinedRecoveryProduct").val(data.recoveryProduct.appointmentRecoveryDate);
                });
                $scope.dateToRecoveryProduct = data.recoveryProduct.completeRecoveryDate;
                $scope.$watch('dateToRecoveryProduct', function(){
                    angular.element("#dateToRecoveryProduct").val(data.recoveryProduct.completeRecoveryDate);
                });
                //shipment
                $scope.idShipment = data.shipment.idShipment;
                $scope.dateAppoinedShipmentProduct = data.shipment.appointmentDate;
                $scope.$watch('dateAppoinedShipmentProduct', function(){
                    angular.element("#dateAppoinedShipmentProduct").val(data.shipment.appointmentDate);
                });
                $scope.dateToShipmentProduct = data.shipment.completeDate;
                $scope.$watch('dateToShipmentProduct', function(){
                    angular.element("#dateToShipmentProduct").val(data.shipment.completeDate);
                });
                $scope.waybill = data.shipment.waybill;
                $scope.$watch('waybill', function(){
                    angular.element("#waybill").val(data.shipment.waybilll);
                });
                //result reklamaciya
                $scope.chekedReklamacia = data.chekedReklamacia;
                if($scope.chekedReklamacia === 1){
                    $scope.$watch('chekedReklamacia', function(){
                        angular.element('#chekedReklamacia').prop('checked', true);
                    });
                } else {
                    $scope.$watch('chekedReklamacia', function(){
                        angular.element('#chekedReklamacia').prop('checked', false);
                    });
                }
            });
        };

        $scope.save = function(){
            if($scope.dateAddItem === null || angular.isUndefined($scope.dateAddItem)){
                alert("Пустое поле \"Дата внесения\"!")
            } else if($scope.nameItem === null || angular.isUndefined($scope.nameItem)){
                alert("Пустое поле \"Наименование изделия\"!")
            } else if($scope.causeList === null || angular.isUndefined($scope.causeList)){
                alert("Недобавлены дефекты на изделие!")
            } else if($scope.solutionList === null || angular.isUndefined($scope.solutionList)){
                alert("Недобавлены меры по дефектам!")
            } else {
                var nameItem, idRecord, resultReklamaciya;
                if (!$scope.oenExecutor.details) {
                    $scope.oenExecutor.details = $scope.oenExecutor.usersByIdUser.details
                }
                if(!$scope.ctp){
                    $scope.ctp = {
                        "productType": $scope.type,
                        "consumer": $scope.consumerItem
                    };
                }
                $scope.chekedReklamacia ? resultReklamaciya = 1 : resultReklamaciya = null;
                $scope.idRecord ? idRecord = $scope.idRecord : idRecord = 0;
                angular.isUndefined($scope.nameItem.name) ? nameItem = $scope.nameItem :  nameItem = $scope.nameItem.name;
                var full_record = {
                    "idRecord": idRecord,
                    "entryDate": $scope.dateAddItem,
                    "user": $scope.oenExecutor.details,
                    "productName": nameItem,
                    "number": $scope.numberProduct,
                    "solutions": $scope.solution,
                    "actNumber": $scope.act,
                    "chekedReklamacia": resultReklamaciya,
                    "ctp": $scope.ctp,
                    "recoveryProduct": {
                        "idRecoveryProduct": $scope.idRecoveryProduct,
                        "appointmentRecoveryDate": $scope.dateAppoinedRecoveryProduct,
                        "completeRecoveryDate": $scope.dateToRecoveryProduct
                    },
                    "shipment": {
                        "idShipment": $scope.idShipment,
                        "appointmentDate": $scope.dateAppoinedShipmentProduct,
                        "completeDate": $scope.dateToShipmentProduct,
                        "waybill": $scope.waybill
                    },
                    "defects": $scope.causeList,
                    "defectSolutions": $scope.solutionList
                };
                saveRecordFactory.save(full_record).$promise.then(function(responseData){
                    $rootScope.$broadcast("editableBroadcast", {
                        editable: false,
                        update: true
                    });
                });
            }
            //$scope.items = itemFactory.query();
        };

        $scope.delete = function(id, page, name){
            var result = confirm('Удалить рекламацию по агрегату \"'+name+'\" ?');
            if(result){
                deleteRecordFactory.delete({id:id},
                    function (resp, headers) {
                        //success callback
                        $rootScope.$broadcast("editableBroadcast", {
                            editable: false,
                            update: true
                        });
                    },
                    function (err) {
                        // error callback
                        alert(err.statusText);
                    }
                );
            }
        };

        $scope.cancel = function(){
            $rootScope.$broadcast('editItemConsumerBroadcast', {
                editItemConsumerBroadcast: ''
            });
            $rootScope.$broadcast("editableBroadcast", {
                editable: false,
                update: false
            });
        };
    })

    .directive('datepickerAddItem', function($document, $rootScope) {
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

    .directive('dateAppointedRecoveryProduct', function($document, $rootScope){
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

    .directive('dateToRecoveryProduct', function($document, $rootScope){
        return {
            link: function (scope, element, attr, ctrl) {
                $(function () {
                    $.datepicker.setDefaults(
                        $.extend($.datepicker.regional['ru'])
                    );
                    $(".datepicker").datepicker();
                })
            }
        }    })

    .directive('dateAppointedShipmentProduct', function($document, $rootScope){
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

    .directive('dateToShipmentProduct', function($document, $rootScope){
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



