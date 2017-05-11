(function() {
    'use strict';

    angular
        .module('jasperReportsSampleApp')
        .controller('CompteDetailController', CompteDetailController);

    CompteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Compte', 'User'];

    function CompteDetailController($scope, $rootScope, $stateParams, previousState, entity, Compte, User) {
        var vm = this;

        vm.compte = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jasperReportsSampleApp:compteUpdate', function(event, result) {
            vm.compte = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
