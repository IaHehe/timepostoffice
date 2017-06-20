<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 订单填写</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/email.css">
    <script src="js/jquery.js"></script>
    <script src="js/laydate.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="main">
    <div class="w">
        <div class="email">
            <div class="myemail">
                <p><!--<strong>我们的地址是重庆市大学城东路20号重庆科技学院</strong><br>-->
                    现在您可以将亲笔信寄交时光邮局，由为您提供安全、优质的保管和寄送服务。</p>
                <form action="${clientBase }/letterpaperorder/submitorder" method="POST">
                    <label>收件人姓名</label><input type="text" id="letterPaperName" name="letterPaperName" placeholder="收件姓名" required><br>
                    <label>收件人地址</label><input type="text" id="letterPaperAddress" name="letterPaperAddress" placeholder="收件人地址" required><br>
                    <label>收件人电话</label><input type="text" id="letterPaperPhone" name="letterPaperPhone" placeholder="收件人电话" required><br>
                    <label>邮编</label><input type="text" id="letterPaperPostNumber" name="letterPaperPostNumber" placeholder="邮编" required><br>
                    <label>寄送时间</label><input type="text" id="dateOfMailing" name="dateOfMailing" placeholder="寄送时间" class="laydate-icon sendtime" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" required><br>
                    <label>您的姓名</label><input type="text" id="customerName" name="customerName" placeholder="您的姓名" required><br>
                    <label>您的电话</label><input type="text" id="customerPhone" name="customerPhone" placeholder="您的电话" required><br>
                    <label>您的QQ</label><input type="text" id="customerQQ" name="customerQQ" placeholder="您的QQ" required><br>

                   <input type="submit" id="myemail-btn" value="我要寄送" class="myemail-btn">
                </form>
            </div>
        </div>
    </div>
</div>

<div class="mask"></div>
<div class="pay clearfix">
    <div class="close">×</div>
    <img src="img/pay.jpg" alt="">
    <button class="cancel">取消</button>
    <button class="gopay">去支付</button>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<script>
	/* $("#myemail-btn").click(function(){
		var recipientname = $("#recipientName").val();
		var recipientaddress = $("#recipientAddress").val();
		var recipientphone = $("#recipientPhone").val();
		var recipientpostnumber = $("#recipientPostNumber").val();
		var dateofmailing = $("#dateOfMailing").val();
		var customername = $("#customerName").val();
		var customerphone = $("#customerPhone").val();
		var customerqq = $("#customerQQ").val();
		 var args = {
			recipientName:recipientname,
			recipientAddress:recipientaddress,
			recipientPhone:recipientphone,
			recipientPostNumber:recipientpostnumber,
			dateOfMailing:dateofmailing,
			customerName:customername,
			customerPhone:customerphone,
			customerQQ:customerqq,
		}; 
		var url="../user/submitorder";
		$.post(/* url, */args,function(data){
			if(data == 1) {
				console.log("yes");
				window.location = "${clientBase }/user/index";
			}else if(data == 0) {
				console.log("error");
				$("#errorDis").html("错误");
			}
		});
	}); */
	
	$(".myemail-btn").click(function () {
        $(".pay").show();
        $(".mask").show();
    })
    $(".close,.cancel").click(function () {
        $(".pay").hide();
        $(".mask").hide();
</script>
</body>
</html>