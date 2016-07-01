var module = require('main_module');

function Service ($http) {

    this.get = function(id) {
        return $http.get('/api/items/' + id);
    };

    this.all = function() {
        return $http.get('/api/items');
    };

    this.update = function(item) {
        return $http.put('/api/items/' + item.id, item);
    };

    this.create = function(item) {
        return $http.post('/api/items/', item);
    };

    this.remove = function(id) {
        return $http.delete('/api/items/' + id);
    };
}

Service.$inject = ['$http'];
module.service('ItemService', Service);
