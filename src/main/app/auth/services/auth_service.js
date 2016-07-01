module = require('main_module');

function Service ($http, $httpParamSerializer, $cookies) {

    this.login = login;

    function login(username, password) {
        var data = { grant_type:"password", username: username, password: password, client_id: "web-ui" };
        var encoded = btoa("web-ui:");
        var req = {
            method: 'POST',
            url: "/oauth/token",
            headers: {
                "Authorization": "Basic " + encoded,
                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
            },
            data: $httpParamSerializer(data)
        };
        return $http(req).then(
            function(data) {
                $http.defaults.headers.common.Authorization= 'Bearer ' + data.data.access_token;
                $cookies.put("access_token", data.data.access_token);
                return data;
            });
    }
}

Service.$inject = ['$http', '$httpParamSerializer', '$cookies'];
module.service('AuthService', Service);
