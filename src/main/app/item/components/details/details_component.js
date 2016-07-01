var module = require('main_module');

function Controller($state, $stateParams, ItemService) {
    //Controller body goes here

    var vm = this;

    vm.item = {};
    vm.itemId = $stateParams.id;
    vm.errors = [];

    vm.update = update;
    vm.remove = remove;

    vm.$onInit = function() {
        _loadItem();
    };

    function update() {
        ItemService.update(vm.item).then(
            function () { console.log('Update success'); },
            function (err) {
                if (err.status === 400) {
                    vm.errors = err.data;
                } else {
                    console.log('Error', err);
                }
            });
    }

    function remove() {
        ItemService.remove(vm.itemId).then(
            function () { $state.go('root.itemList'); },
            function (err) {
                console.log('Error', err);
            });
    }

    function _loadItem() {
        if (vm.itemId) {
            ItemService.get(vm.itemId).then(
                function (response) {
                    vm.item = response.data;
                },
                function (err) {
                    console.log('Error', err);
                });
        }
    }
}


Controller.$inject = ['$state', '$stateParams', 'ItemService'];

module.component('itemDetails', {
    controller: Controller,
    templateUrl: require('./details.html')
});
