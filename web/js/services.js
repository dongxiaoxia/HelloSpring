'use strict';

book.factory('users', ['$http', function ($http) {
    var url = "api/user/";
    var factory = {};

    factory.all = function () {
        var users = $http.get(url + 'page').then(function (resp) {
            return resp.data.data.records;
        });
        return users;
    };

    factory.get = function (id) {
        var book = $http.get(url + '/' + id).then(function (resp) {
            return resp.data;
        });
        return book;
    };

    factory.add = function (user) {
        $http.post(url + 'add', user).then(function (resp) {
            return resp;
        });
    };

    factory.update = function (book) {
        $http.put(url + '/' + book.id, book).then(function (resp) {
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