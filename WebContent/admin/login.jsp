
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="zh">
<head>
<base href="${adminBase}/admin/" />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${site } - 管理员登录</title>
<link rel="stylesheet" type="text/css" href="Css/styles.css">

</head>
<body>

	<div class="wrapper">
		<div class="container">
			<h1>时光邮件后台管理</h1>
			<form action="${adminBase }/manager/login" class="form" method="post">
				<input type="text" name="manangerAccount" placeholder="管理员账号">
				<input type="password" name="managerPassword" placeholder="管理员密码"><br>
				<button type="submit" id="login-button">
					<strong>登陆</strong>
				</button>
			</form>
		</div>

		<ul class="bg-bubbles">
			<li></li>
			<li></li>
		</ul>
	</div>



</body>
</html>