<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/10/28
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloSpring</title>
    <script src="http://apps.bdimg.com/libs/angular.js/1.3.9/angular.min.js"></script>
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body ng-app="myApp"  ng-controller="userController">
<h1 style="text-align: center">Hello Spring</h1>
<sec:authentication property="principal" var="authentication"/>
<sec:authorize ifAllGranted="ROLE_USER1">可以访问</sec:authorize>

<div>
    用户：<a href="">${authentication.username }</a> <span style="margin-left: 50px">您的身份为：
    <sec:authorize ifAllGranted="ROLE_ADMIN">
        admin
    </sec:authorize>

<sec:authorize ifAnyGranted="ROLE_USER" ifNotGranted="ROLE_ADMIN">
    user
</sec:authorize>

</span> <a style="margin-left: 50px" href="/api/user/logout">退出</a> <br/>
</div>



<div class="container">

    <h3>Users</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>编辑</th>
            <th>用户名</th>
            <th>密码</th>
            <th>昵称</th>
            <th>真实姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>状态</th>
            <th>等级</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="user in users">
            <td>
                <button class="btn" ng-click="openEditForm(user.id)">
                    <span class="glyphicon glyphicon-pencil"></span>编辑
                </button>
                <button class="btn" ng-click="deleteUser(user.id)">
                    <span class="glyphicon glyphicon-pencil"></span>删除
                </button>
            </td>
            <td><a href="/api/user/get?id={{user.id}}">{{ user.username }}</a></td>
            <td>{{ user.password }}</td>
            <td>{{ user.nickname }}</td>
            <td>{{ user.realname }}</td>
            <td>{{ user.age }}</td>
            <td>{{ user.sex }}</td>
            <td>{{ user.email }}</td>
            <td>{{ user.status }}</td>
            <td>{{ user.level }}</td>
        </tr>
        </tbody>
    </table>

    <hr>
    <button class="btn btn-success" ng-click="editUser('new')" data-toggle="modal" data-target="#myModal">
        <span class="glyphicon glyphicon-user"></span>创建新用户
    </button>
    <hr>

</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form class="form-horizontal" ng-submit="addUser(user)">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    创建新用户
                </h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" ng-model = "user.username" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" ng-model="user.password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickname" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="nickname" ng-model="user.nickname" placeholder="Nickname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realname" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="realname" ng-model="user.realname" placeholder="Realname">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="age" ng-model="user.age" placeholder="Age">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sex" class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="sex" ng-model="user.sex" placeholder="Sex">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="email" ng-model="user.email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="status" ng-model="user.status" placeholder="Status">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="level" class="col-sm-2 control-label">等级</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="level" ng-model="user.level" placeholder="Level">
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="submit" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal -->
</div>
</body>
</html>
<script>
    var app = angular.module('myApp', []);

    app.config(function ($httpProvider) {
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

        // Override $http service's default transformRequest
        $httpProvider.defaults.transformRequest = [function (data) {
            /**
             * The workhorse; converts an object to x-www-form-urlencoded serialization.
             * @param {Object} obj
             * @return {String}
             */
            var param = function (obj) {
                var query = '';
                var name, value, fullSubName, subName, subValue, innerObj, i;

                for (name in obj) {
                    value = obj[name];

                    if (value instanceof Array) {
                        for (i = 0; i < value.length; ++i) {
                            subValue = value[i];
                            fullSubName = name + '[' + i + ']';
                            innerObj = {};
                            innerObj[fullSubName] = subValue;
                            query += param(innerObj) + '&';
                        }
                    } else if (value instanceof Object) {
                        for (subName in value) {
                            subValue = value[subName];
                            fullSubName = name + '[' + subName + ']';
                            innerObj = {};
                            innerObj[fullSubName] = subValue;
                            query += param(innerObj) + '&';
                        }
                    } else if (value !== undefined && value !== null) {
                        query += encodeURIComponent(name) + '='
                        + encodeURIComponent(value) + '&';
                    }
                }

                return query.length ? query.substr(0, query.length - 1) : query;
            };

            return angular.isObject(data) && String(data) !== '[object File]'
                    ? param(data)
                    : data;
        }];
    });
    app.controller('userController', function ($scope, $http) {
        $http.get("/api/user/page")
                .success(function (response) {
                    $scope.users = response.data.records;
                });
        $scope.http = function(method,url,data,success,error){
            $http({
             method: method,
             url: url,
             data: data
             }).
             success(success).
             error(error);
        }
        //添加用户
        $scope.addUser =function(user){
            var success = function(){
                alert("add user success!")
            };
            var failure = function(){
                alert("add user failure")
            }
            $scope.http('POST','/api/user/add',user,success(),failure);
        }
        //删除用户
        $scope.deleteUser = function(id){
            var success = function(){
                alert("delete user success!")
            };
            var failure = function(){
                alert("delete user failure")
            }
            $scope.http("POST","/api/user/delete",{id:id},success(),failure);
        }
        //打开编辑表单
        $scope.openEditForm = function(id){
                $http({
                    method: "POST",
                    url: "/api/user/get",
                    data: {id:id}
                }).success(function(response){
                    $scope.user = response.data;
                    $('#myModal').modal()
                }).error(function(response){
                    alert("error");
                });


        }

        //编辑用户
        $scope.updateUser = function(id){
            var success = function(){
                alert("delete user success!")
            };
            var failure = function(){
                alert("delete user failure")
            }
            var data = {id:id};
            $scope.http("POST","/api/user/delete",data,success(),failure);
        }
    });
</script>