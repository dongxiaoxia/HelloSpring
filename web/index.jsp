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
    <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<h1>Hello Spring</h1>
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
<%--<sec:authentication property="name"/>
<sec:authentication property="authorities" var="authorities" scope="page"/>
<c:forEach item="${authorities}" var="authority">
    ${authority.authority}
</c:forEach>--%>

<div id="stage" style="background-color:#eee;">
</div>
<script type="text/javascript" language="javascript">
    $(document).ready(function () {
        $.getJSON('/api/user/page', function (jd) {
            debugger
            $('#stage').append('<table>');
            for (var i = 0; i < jd.data.records.length; i++) {
                $('#stage').append("<tr><td> Userame: <a href='/api/user/get?id= " + jd.data.records[i].id + "'>" + jd.data.records[i].username + "</a></td>" + '<td style="padding-left: 100px"></td>' + '<td>Password : ' + jd.data.records[i].password + '</td><//tr>');
            }
            $('#stage').append('</table>');
        });
    });
</script>

</body>
</html>
