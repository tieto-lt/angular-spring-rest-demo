module = require('main_module');

function Controller(ItemService) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.items = [];

    vm.$onInit = function() {
        _loadList();
    }

    //Underscore because private function which is not exposed in controller interface
    function _loadList() {
        ItemService.all().then(
            function (response) {
                vm.items = response.data;
            },
            function(err) {
                console.log('Error', err);
            });
    }
}

//Angular Component Configuration
Controller.$inject = ['ItemService'];

module.component('itemList', {
    controller: Controller,
    templateUrl: require('./list.html')
});