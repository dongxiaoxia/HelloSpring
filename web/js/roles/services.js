'use strict';

roles.factory('roles', ['$http', function ($http) {
    var url = "http://localhost/api/role/";
    var factory = {};

    factory.all = function () {
        var data = {"resource": null, "pageStart": 1, "pageSize": 10};
        //$http({
        //
        //    method: "post",
        //
        //    data: data,//Form Data = {"id":1,"value":"hello"}
        //
        //    url: "/api/user/page",
        //
        //    headers: { "Content-Type": "application/x-www-form-urlencoded" },
        //
        //    success: function (d) { console.log(d); }
        //
        //});
        var roles = $http.post(url + 'page', data).then(function (resp) {
            return resp;
        });
        return roles;
    };

    factory.get = function (id) {
        var book = $http.get(url + 'get?id=' + id).then(function (resp) {
            debugger
            return resp.data.data;
        });
        return book;
    };

    factory.add = function (user) {
        $http.post(url + 'add', user).then(function (resp) {
            return resp;
        });
    };

    factory.update = function (user) {
        debugger
        /*user._method = 'put';*/
        $http.put(url + 'update', user).then(function (resp) {
            return resp;
        });
    };

    factory.delete = function (id) {
        $http.delete(url + 'delete?id=' + id).then(function (resp) {
            return resp;
        });
    };

    return factory;
}]);