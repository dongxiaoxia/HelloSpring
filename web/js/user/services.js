'use strict';

book.factory('users', ['$http', function ($http) {
    var url = "http://localhost/api/user/";
    var factory = {};

    factory.all = function () {
        var data = {"user": null, "pageStart": 1, "pageSize": 10};
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
        var users = $http.post(url + 'page', data).then(function (resp) {
            return resp;
        });
        return users;
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

    factory.page = function (user, pageStart, pageSize) {
        debugger
        var data = {"user": user, "pageStart": pageStart, "pageSize": pageSize};
        var users = $http.post(url + 'page', data).then(function (resp) {
            return resp;
        });
        return users;
    };
    return factory;
}]);