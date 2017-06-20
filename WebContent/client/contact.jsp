<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="${clientBase }/client/"/>
    <title>${site} - 联系我们</title>
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
			<div class="contact">
				<dl>您可以通过以下方式联系我们：
				</dl>
				<dt>Email</dt>
				<!--  <dd>网站反馈、意见、求助请联系 help#postome.com</dd>
            <dd>网站合作请联系 contact#postome.com</dd> -->
				<c:forEach var="cus" items="${connectus }">
					<dd>${cus.contactUsEmail }</dd>
				</c:forEach>
				<dt>联系电话</dt>
				<c:forEach var="cus" items="${connectus }">
					<dd>${cus.contactUsPhone }</dd>
				</c:forEach>
				<dt>联系地址</dt>
				<c:forEach var="cus" items="${connectus }">
					<dd>${cus.contactUsAddress }</dd>
				</c:forEach>
				<dd>扫描上方二维码，或在微信中搜索 postome 添加 PostoMe小邮差 为好友</dd>
			</div>
		</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script>
	//导航栏小三角显示
	$("s").eq(5).addClass("current");
</script>
</body>
</html>