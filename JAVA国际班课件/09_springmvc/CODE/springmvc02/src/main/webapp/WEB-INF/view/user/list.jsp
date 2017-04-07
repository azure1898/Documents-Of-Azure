<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    用户列表
    <br><a href="${ctx}/user/add">新增</a>
    <hr/>

    <table width="50%" align="left">
        <tr>
            <td>用户名</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>生日</td>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <td>${user.username}</td>
                <td>${user.realName}</td>
                <td>${user.age}</td>
                <td>
                    <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
