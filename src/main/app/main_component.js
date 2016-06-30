module = require('main_module');

var templateUrl = require('./main.html');
require('main.scss');
module.component('main', {
    templateUrl: templateUrl
});