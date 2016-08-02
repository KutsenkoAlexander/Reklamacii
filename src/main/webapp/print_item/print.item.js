angular.module('reclamaciiApp.print.item', ['ngResource'])

    .factory('listSelectedItemFactory', function(){
        var listSelectedItem = [];
        listSelectedItem.add = function(item){
            if(listSelectedItem.indexOf(item) == -1){
                listSelectedItem.push(item);
            }
        };
        listSelectedItem.clear = function(){
            listSelectedItem.splice(0, listSelectedItem.length);
        };
        listSelectedItem.removeItem = function(item){
            listSelectedItem.splice(listSelectedItem.indexOf(item.idRecord), 1);
        };
        return listSelectedItem;
    })

    .controller('printItemCtrl', function($scope, listSelectedItemFactory){
        $scope.$on('itemPrintBroadcast', function(event, args){
            listSelectedItemFactory.clear();
            listSelectedItemFactory.add(args.itemPrint);
            $scope.items = listSelectedItemFactory;
        });
        $scope.$on('listAddItemBroadcast', function(event, args){
            listSelectedItemFactory.add(args.item);
            $scope.items = listSelectedItemFactory;
        });
        $scope.$on('listRemoveItemBroadcast', function(event, args){
            listSelectedItemFactory.removeItem(args.item);
            $scope.items = listSelectedItemFactory;
        });
    });