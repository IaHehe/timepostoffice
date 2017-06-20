<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 登录</title>
    <link rel="stylesheet" href="css/base.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="box">
    <div class="register-success">
    	<div>恭喜您，注册成功  去<a href="${clientBase }/user/login">登录</a></div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>