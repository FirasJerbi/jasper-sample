(function () {
    'use strict';

    angular
        .module('jasperReportsSampleApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
