<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 个人信息设置</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/email.css">
    <script src="js/jquery.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<!--设置开始-->
<div class="main">
    <div class="w">
        <div class="setting">
            <!-- <form action="${clientBase }/user/upload" method="POST" enctype="multipart/form-data"> -->
                <label></label><img class="headpic"></img>
                <input type="file" id="headFile" name="userImg" value="tupian" style="display: none" accept="image/*">
                <input type="button" class="headchange" value="选择图片"><br>
                <div class="msg"></div>
                <label>昵称</label><input type="text" name="userName" value="${user.userName == null ? '' : user.userName }" required><br>
                <label>邮箱</label><input type="email" name="userEmail" value="${user.userEmail }" disabled required><br>
                <label>原密码</label><input type="password" name="newPassword"><br>
                <label>新密码</label><input type="password" name="odlPassword"><br>
                <input type="submit" value="保存" class="set-btn">
           <!--  </form> -->
        </div>
    </div>
</div>
<!--设置结束-->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
<script>
    $(".headchange").click(function () {
        $("#headFile").click();
    })
    $("#headFile").change(function () {
        var file=this.files[0]?this.files[0]:null;
        var formData = new FormData();
        formData.append('userImg', file);
        $.ajax({
        	url: '${clientBase }/user/upload',
        	data: formData,
        	type: 'post',
        	procache: false,
		    processData: false,
		    contentType: false,
		    success: function(data) {
		    	console.log(JSON.stringify(data));
		    }
        });
        /* if(!file){
            return false;
        }
        var file_reader=new FileReader();
        file_reader.onload=function(){
            var img_url=this.result;
            $(".headpic").attr("src",img_url);
        }
        file_reader.readAsDataURL(file); */
    })
</script>