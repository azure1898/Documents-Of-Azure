<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.1/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/datatables/datatables.css" />
    <script type="text/javascript" src="${ctx}/static/js/datatables/datatables.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
        	
        	 $('#example').DataTable({
                 "ajax": "${ctx}/user/list",
                 "columns": [
                     {"data": "username"},
                     {"data": "realName"},
                     {"data": "age"},
                     {"data": "birthday"}
                 ]
             });
        	/**
           $.ajax({
        	   url : "${ctx}/user/list",
        	   type: "get",
        	   success : function(data,status){
        		   alert(data.data[0].realName);
        	   }
           }); 
        	**/
        });
    </script>
</head>
<link>
用户列表
<br><a href="${ctx}/user/add">新增</a>
<hr/>
<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
	    <tr>
	        <td>用户名</td>
	        <td>姓名</td>
	        <td>年龄</td>
	        <td>生日</td>
	    </tr>
    </thead>
    <tfoot>
	    <tr>
	        <td>用户名</td>
	        <td>姓名</td>
	        <td>年龄</td>
	        <td>生日</td>
	    </tr>
    </tfoot>
</table>
</body>
</html>
