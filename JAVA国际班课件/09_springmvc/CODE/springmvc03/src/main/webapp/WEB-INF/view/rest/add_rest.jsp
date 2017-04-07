<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>新增用户</title>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#user_data").submit(function(){
        		$.ajax({
        			url  : "${ctx}/user/add",
        			type : "post",
        			data : $("#user_data").serialize(),
        			success : function(data){
        				//alert(data.success);
        				alert(data.message);
        			}
        		});
        		return false;
        	});
        });
    </script>
</head>
<body>
    新增用户-add_rest.jsp
    <hr/>
    <form id="user_data">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="realName"/></td>
            </tr>
            <tr>
                <td>年龄</td>
                <td><input type="text" name="age"/></td>
            </tr>
            <tr>
                <td>生日</td>
                <td><input type="text" name="birthday" onClick="WdatePicker()" class="Wdate"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                   <input type="submit" value="保存"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
