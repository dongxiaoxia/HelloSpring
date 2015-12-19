'use strict';

resources.controller('ListCtrl', ['$scope', '$filter', 'resources',
    function ($scope, $filter, resources) {
        $scope.loadList = function () {
            resources.all().then(function (response) {
                $scope.resources = response.data.data.data;
                $scope.pageNow = response.data.data.pageStart;
                $scope.pageCount = response.data.data.pageCount;
            });
        };
        //init load data
        $scope.loadList();

        $scope.deleteBook = function ($index, id) {
            if (confirm("确定删除？")) {
                resources.delete(id);
                $scope.resources.splice($index, 1);
            }
        };

        //设置分页
        //初始化分页参数
        $scope.itemsPerPage = 10;
        $scope.currentPage = 0;

        $scope.prevPage = function () {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };

        $scope.prevPageDisabled = function () {
            return $scope.currentPage == 0;
        };

        //书名搜索关键词，主要用于更新books数组
        $scope.bookFilterdInput = '';

        $scope.pageCount1 = function () {
            if ($scope.books) {
                //根据用户输入来过滤更新数组，主要用来更新页数
                $scope.updatePage = $filter('bookname')($scope.books, $scope.bookFilterdInput);
                //向上取整求出总页数
                return Math.ceil($scope.updatePage.length / $scope.itemsPerPage);
            } else {
                return false;
            }
        };

        $scope.nextPage = function () {
            if ($scope.currentPage < $scope.pageCount()) {
                $scope.currentPage++;
            }
        };

        $scope.nextPageDisabled = function () {
            return $scope.currentPage + 1 == $scope.pageCount1();
        };

        $scope.$watch('bookFilterdInput', function () {
            //console.log('change');
            if ($scope.pageCount1() <= $scope.currentPage) {
                $scope.currentPage = 0;
            }
        })

        $scope.$watch('itemsPerPage', function () {
            //console.log('change');
            if ($scope.pageCount1() <= $scope.currentPage) {
                $scope.currentPage = 0;
            }
        })
    }
]);

resources.controller('ViewCtrl', ['$scope', '$routeParams', 'resources',
    function ($scope, $routeParams, users) {
        //用指令代替了这块功能，该controller和directiveCtrl完全相同
        //books.get($routeParams.id).then(function (book) {
        //    $scope.book = book;
        //});
    }
]);

resources.controller('EditCtrl', ['$scope', '$routeParams', '$location', 'resources',
    function ($scope, $routeParams, $location, resources) {
        resources.get($routeParams.id).then(function (resource) {
            $scope.resource = resource;
        });

        $scope.new = function (user) {
            //console.log(book);
            debugger
            resources.update(user);
            $location.path('/');
        };
    }
]);

resources.controller('NewCtrl', ['$scope', '$location', 'resources',
    function ($scope, $location, resources) {
        $scope.new = function (user) {
            debugger
            console.log(user);
            users.add(user);
            $location.path('/');
        };
    }
]);

resources.controller('directiveCtrl', ['$scope', '$routeParams', 'resources',
    function ($scope, $routeParams, resources) {
        resources.get($routeParams.id).then(function (resource) {
            $scope.resource = resource;
        });
    }
]);