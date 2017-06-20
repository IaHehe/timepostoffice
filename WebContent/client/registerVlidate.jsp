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
    <div class="vlidate">
    	<div>感谢你注册时光邮局，请前往您的邮箱验证</div>
    	<div><a href="index.jsp">返回首页</a></div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>