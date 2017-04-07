<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var user = {
                "username": "zhangsan",
                "realName": "张三",
                "age": 28,
                "birthday": "2016-12-08 17:19:07"
            };
            alert(user);
            alert(JSON.stringify(user));
            $.ajax({
                type:"POST",
                url:"${ctx}/user/add",
                dataType:"json",
                contentType:"application/json",
                //stringify 用于把JavaScript对象序列化为JSON字符串 JSON.parse(str);
                data:JSON.stringify(user),
                success:function(data){
                    alert(data.message)
                }
            });
        });
    </script>
</head>
<body>

</body>
</html>
