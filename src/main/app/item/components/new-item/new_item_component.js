module = require('main_module');

function Controller($state, ItemService) {
    var vm = this;

    vm.item = {};

    vm.create = create;
    vm.errors = [];

    function create() {
        ItemService.create(vm.item).then(
            function () {
                $state.go('root.itemList');
            },
            function (err) {
                if (err.status === 400) {
                    vm.errors = err.data;
                } else {
                    console.log('Error', err);
                }
            }
        );
    }

}
Controller.$inject = ['$state', 'ItemService'];

//You can also require SASS files related to this component
require('./new_item.scss');
module.component('itemNew', {
    controller: Controller,
    templateUrl: require('./new_item.html')
});