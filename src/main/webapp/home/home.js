angular.module('reclamaciiApp.home', ['ui.router','ngResource'])

    .config(function ($stateProvider) {
        $stateProvider
            .state('home', {
                url: '/',
                views: {
                    'content': {
                        templateUrl: 'home/home.html',
                        controller: 'homeCtrl'
                    }
                },
                data: {
                    pageTitle: 'Главная'
                }
            });

    })

    .factory('allTypeProduct', function($resource){
        return $resource('/product_type/all', {},{});
    })
    .factory('allConsumer', function($resource){
        return $resource('/consumer/all', {},{});
    })
    .factory('allOenExecutor', function($resource){
        var id = 26; //id department OEN
        return $resource('http://192.168.31.200:8888/Security/role_members/department_users/'+id, {},{});
    })
    .factory('allClassDefect', function($resource){
        return $resource('/class_defect/all', {},{});
    })

    .factory('sortFactory', function($resource){
        return $resource('/record/sort', {}, {'query': {method: 'GET', isArray:false}});
    })

    .factory('paramsFactory',function(){
        var param = {
            size: 100,
            productType: '',
            classDefect: '',
            user: '',
            dateFrom: '',
            dateTo: '',
            consumer: '',
            productName: '',
            number: ''
        };
        return param;
    })

    .controller('homeCtrl', function ($rootScope,
                                      $scope,
                                      $location,
                                      pageCacheFactory,
                                      allTypeProduct,
                                      allConsumer,
                                      allOenExecutor,
                                      allClassDefect,
                                      sortFactory,
                                      paramsFactory) {
        $scope.refres = true;

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

        //filter here
        var getResultSortQuery = function (params) {
            $scope.refres = false;
            sortFactory.query(params,
            function(response){
                //rollback
            },
            function(error){
                alert(error.statusText);
            }).$promise.then(function(data){
                $rootScope.$broadcast("filterEvent", {
                    content: data.content,
                    page: data.page,
                    links: data.links
                });
            });
        };
        //filter off
        $scope.filterOff = function(){
            $scope.btnFiltrOff = true;

            paramsFactory.productType = '';
            paramsFactory.classDefect = '';
            paramsFactory.user = '';
            paramsFactory.dateFrom = '';
            paramsFactory.dateTo = '';
            paramsFactory.consumer = '';
            paramsFactory.productName = '';
            paramsFactory.number = '';

            getResultSortQuery(paramsFactory);

            $scope.dateFrom = null;
            $scope.dateTo = null;
            $scope.type = null;
            $scope.oenExecutor = null;
            $scope.classDefect = null;
            $rootScope.$broadcast('resetFilter', {
                searchStrConsumer: null
            });
            $scope.productName = null;
            $scope.number = null;
        };
        //dateFrom
        $scope.selectedDateFrom = function(){
            if($scope.dateFrom != null){
                paramsFactory.dateFrom = $scope.dateFrom;
            }else{
                paramsFactory.dateFrom = '';
            }
            getResultSortQuery(paramsFactory);
        };
        //dateTo
        $scope.selectedDateTo = function(){
            if($scope.dateTo != null){
                paramsFactory.dateTo = $scope.dateTo;
            }else{
                paramsFactory.dateTo = '';
            }
            getResultSortQuery(paramsFactory);
        };
        //type product
        $scope.selectedTypeProduct = function(){
            if($scope.type != null){
                paramsFactory.productType = $scope.type.idProductType;
            }else{
                paramsFactory.productType = '';
            }
            getResultSortQuery(paramsFactory);
        };
        //oen executor
        $scope.selectedOenExecutor = function(){
            if($scope.oenExecutor != null){
                paramsFactory.user = $scope.oenExecutor.details;
            }else{
                paramsFactory.user = '';
            }
            getResultSortQuery(paramsFactory);
        };
        //class defect
        $scope.selectedClassDefect = function(){
            if($scope.classDefect != null){
                paramsFactory.classDefect = $scope.classDefect.idClassDefect;
            }else{
                paramsFactory.classDefect = '';
            }
            getResultSortQuery(paramsFactory);
        };
        //consumer
        $scope.$on('consumerItemBroadcast', function(event, args){
            paramsFactory.consumer = args.consumerInput.name;
            if(args.consumerInput.name) {
                getResultSortQuery(paramsFactory);
            }else{
                if(!$scope.refres && !$scope.btnFiltrOff) {
                    getResultSortQuery(paramsFactory);
                }
            }
        });
        //product name
        $scope.$watch('productName', function(){
            if($scope.productName) {
                paramsFactory.productName = $scope.productName;
                getResultSortQuery(paramsFactory);
            } else{
                if(!$scope.refres && !$scope.btnFiltrOff) {
                    paramsFactory.productName = '';
                    getResultSortQuery(paramsFactory);
                }
            }
        });
        //number
        $scope.$watch('number', function(){
            if($scope.number ){
                paramsFactory.number = $scope.number;
                getResultSortQuery(paramsFactory);
            }else{
                if(!$scope.refres && !$scope.btnFiltrOff) {
                    paramsFactory.number = '';
                    getResultSortQuery(paramsFactory);
                }
            }
        });
    })

    .directive('dateSortFrom', function($document, $rootScope){
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

    .directive('dateSortTo', function($document, $rootScope){
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
