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
    <div class="login">
        <img src="img/1.png" alt="">
        <form action="${clientBase }" method="POST" id="login_form">
            <input type="email" id="userEmail" placeholder="邮箱" required><br>
            <span><img src="img/name.png" alt="" class="name"></span>
            <div id="errorDis" style="display:none;font-size:10px;position:absolute;left:25%;top:39px;">123</div>
            <input type="password" id="userPassword" placeholder="密码" required><br>
            <span><img src="img/pass.png" alt="" class="pass"></span>
            <input type="button" id="login_btn" value="登录" class="login-btn">
            <p>没有账户？去 <a href="${clientBase }/user/register">注册</a></p>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="js/jquery.js"></script>
<script>
	$("#login_btn").click(function(){
		var email = $("#userEmail").val();
		var password = $("#userPassword").val();
		var args = {
			userEmail:email,
			userPassword:password,
		};
		var url = $("#login_form").attr("action") + "/user/login";
		$.post(url,args,function(data){
			if(data == 1) {
				window.location = $("#login_form").attr("action");
			}else if(data == 0) {
				$("#errorDis").html("用户名或密码错误").show();
				$("#userPassword").val("");
				$("#userPassword").focus();
			}
		});
	});
</script>
</body>
</html>