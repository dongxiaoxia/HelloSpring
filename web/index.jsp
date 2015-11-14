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
  </head>
  <body>
<h1>Hello Spring</h1>
I coming!!<br>
<sec:authentication property="principal" var="authentication"/> <sec:authorize
        ifAllGranted="ROLE_USER">可以访问</sec:authorize> 用户名：${authentication.username }<br/>
<sec:authentication property="name"/>
<sec:authentication property="authorities" var="authorities" scope="page"/>
<c:forEach item="${authorities}" var="authority">
  ${authority.authority}
</c:forEach>

<sec:authorize ifAllGranted="ROLE_ADMIN,ROLE_USER">
  admin and user
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
  admin or user
</sec:authorize>

<sec:authorize ifNotGranted="ROLE_ADMIN">
  not admin
</sec:authorize>
  </body>
</html>
