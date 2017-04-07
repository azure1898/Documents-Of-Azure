<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<form method="post" action="${ctx}/login">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <input type="submit" value="登录"/>
            </td>
        </tr>
    </table>
</body>
</html>
