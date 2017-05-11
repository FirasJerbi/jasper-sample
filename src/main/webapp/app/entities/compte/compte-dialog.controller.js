(function() {
    'use strict';

    angular
        .module('jasperReportsSampleApp')
        .controller('CompteDialogController', CompteDialogController);

    CompteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Compte', 'User'];

    function CompteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Compte, User) {
        var vm = this;

        vm.compte = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.users = User.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.compte.id !== null) {
                Compte.update(vm.compte, onSaveSuccess, onSaveError);
            } else {
                Compte.save(vm.compte, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jasperReportsSampleApp:compteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.creationDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
