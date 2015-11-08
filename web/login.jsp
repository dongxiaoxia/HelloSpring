<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
</head>
<body>
<h3>用户登录</h3>
${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message} <!-- 输出异常信息 -->
<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    user:<input type="text" name="j_username"/><br/>
    password:<input type="password" name="j_password"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>