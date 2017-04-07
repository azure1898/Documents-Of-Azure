<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>上传图片</title>
</head>
<body>
<form action="/upload1" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/></br>
    <input type="submit" value="提交"/>
</form>
<form action="/upload2" method="post" enctype="multipart/form-data">
    <input type="file" name="myfiles"/></br>
    <input type="file" name="myfiles"/></br>
    <input type="file" name="myfiles"/></br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
