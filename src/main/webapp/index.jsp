<%--
  Created by IntelliJ IDEA.
  User: huawei
  Date: 2023/11/21
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传图片</title>
</head>
<body>
<%--上传文件，文件与文字相比较起来，属于内容较大，必须使用post方式提交--%>
<%--上传文件，与普通文件有区别，action接收参数也会区别对待，所以声明文件提交的表单为“多部件表单”--%>
<form action="upload" method="post" enctype="multipart/form-data">

    <input type="file" name="fname">
    <br>
    <button>提交</button>

</form>

</body>
</html>
