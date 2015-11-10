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
<%--<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    user:<input type="text" name="j_username"/><br/>
    password:<input type="password" name="j_password"/>
    <input type="submit" value="登录"/>
</form>--%>


<form action="/api/user/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>记住我：</td>
            <td><input type="checkbox" name="_spring_security_remember_me"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="登录"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>