module = require('main_module');

function Controller($state, $stateParams, AuthService) {

    var vm = this;
    vm.username = undefined;
    vm.password = undefined;

    vm.login = login;

    function login() {
        AuthService.login(vm.username, vm.password).then(
            function (response) {
                console.log(response);
            },
            function (err) {
                console.log(err);
            });
    }

}


Controller.$inject = ['$state', '$stateParams', 'AuthService'];
require('login.scss');
module.component('login', {
    controller: Controller,
    templateUrl: require('./login.html')
});