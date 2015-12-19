'use strict';

roles.controller('ListCtrl', ['$scope', '$filter', 'roles',
    function ($scope, $filter, roles) {
        $scope.loadList = function () {
            roles.all().then(function (response) {
                $scope.roles = response.data.data.data;
                $scope.pageNow = response.data.data.pageStart;
                $scope.pageCount = response.data.data.pageCount;
            });
        };
        //init load data
        $scope.loadList();

        $scope.deleteBook = function ($index, id) {
            if (confirm("确定删除？")) {
                roles.delete(id);
                $scope.roles.splice($index, 1);
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

roles.controller('ViewCtrl', ['$scope', '$routeParams', 'roles',
    function ($scope, $routeParams, roles) {
        //用指令代替了这块功能，该controller和directiveCtrl完全相同
        //books.get($routeParams.id).then(function (book) {
        //    $scope.book = book;
        //});
    }
]);

roles.controller('EditCtrl', ['$scope', '$routeParams', '$location', 'roles',
    function ($scope, $routeParams, $location, roles) {
        roles.get($routeParams.id).then(function (role) {
            $scope.role = role;
        });

        $scope.new = function (role) {
            //console.log(book);
            debugger
            roles.update(role);
            $location.path('/');
        };
    }
]);

roles.controller('NewCtrl', ['$scope', '$location', 'roles',
    function ($scope, $location, roles) {
        $scope.new = function (user) {
            debugger
            console.log(user);
            roles.add(user);
            $location.path('/');
        };
    }
]);

roles.controller('directiveCtrl', ['$scope', '$routeParams', 'roles',
    function ($scope, $routeParams, roles) {
        roles.get($routeParams.id).then(function (role) {
            $scope.role = role;
        });
    }
]);