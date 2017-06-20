<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 注册</title>
    <link rel="stylesheet" href="css/base.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="box">
    <div class="login" id="register_box">
        <img src="img/1.png" alt="">
        <form action="${clientBase }/" id="regist-form" method="POST">
            <input type="email" id="userEmail" name="userEmail" placeholder="邮箱" required><br>
            <span><img src="img/name.png" alt="" class="name"></span>
            <div id="errorDis" style="display:none;font-size:10px;position:absolute;left:25%;top:39px;"></div>
            <input type="password" id="userPassword" name="userPassword" placeholder="密码" required><br>
            <input type="password" id="repeatPassword" placeholder="确认密码" required><br>
            <span><img src="img/pass.png" alt="" class="pass"></span>
            <span><img src="img/pass.png" alt="" class="pass-sec"></span>
            <input type="button" value="注册" class="register-btn" id="register_btn">
            <p>已有账户？去 <a href="${clientBase }/user/login">登录</a></p>
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="js/jquery.js"></script>
<script>
	$(function(){
		//用户输入密码的input 失去焦点事件
		$("#userPassword").focus(function(){
			$("#errorDis").html("").hide();
		});
		//用户输入邮箱的input 失去焦点事件
		$("#userEmail").blur(function(){
			var val = $(this).val();
			var errorBox = $("#errorDis");
			var $this = $(this);
			if(val != "") {
				errorBox.html("");
				var url = $("#regist-form").attr("action") + "user/register";
				var args = {userEmail:val,mark:"check",time:new Date()};
				$.post(url,args,function(data){
					if(data == "1") {
						errorBox.html("用户可用").show();
					}else if(data == "0"){
						errorBox.html("用户已存在,请重新输入").show();
						$this.focus();
					}else{//异常，服务器错误
						alert("服务器错误!");
					}
				})
			}else {
				this.focus();
			}
		});
		 //注册按钮的单击事件
		 $("#register_btn").click(function(){
			 var form = $("#regist-form");
			 var action = form.attr("action");
			 form.attr("action",action+"user/register");
			 form.submit();
		 });
		/*$("#register_btn").click(function(){
			var email = $("#userEmail").val();
			var password = $("#userPassword").val();
			var repeatPassword = $("#repeatPassword");
			var args = {
				userEmail:email,
				userPassword:password,
				date: new Date()
			};
			var action = $("#regist-form").attr("action");
			var url = action + "/user/register";
			$.post(url,args,function(data){
				if(data == 1) {
					window.location = action + "user/registered";
				}else if(data == 0) {
					$("#errorDis").html("注册失败");
				}
			});
		}); */
	})
</script>
</body>
</html>

<script>
    /* var register=document.getElementById("register");
    var form=document.getElementById("regist-form");
    register.onclick= function () {
        form.innerHTML="感谢您注册 时光邮局 ，<br>请进入您的注册邮箱 <a href='#' class='email-a'>42452344@qq.com</a> 进行验证</br><br><a href='index.html'>返回首页</a>"
    }   */
</script>
