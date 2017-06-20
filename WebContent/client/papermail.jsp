<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.cqust.tpo.models.PostGuide"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	List pg = (List) request.getAttribute("letterpaperorder");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 邮寄指南</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/email.css">
    <script src="js/jquery.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="main">
		<div class="w">
			<div class="email">
				<div class="myemail paper">
				
					<tr>
						<p>现在您可以将亲笔信寄交时光邮局，由时光邮局为您提供安全、优质的保管和寄送服务。</p>
						<c:forEach var="postguide" items="${postguidelist}">
						<dt>${postguide.postGuideId}.${postguide.postGuideTitle }</dt>
						<dd>${postguide.postGuideContent }</dd>
						</c:forEach>
						<button>
							<a href="${clientBase }/letterpaperorder/order">已写完纸信，现在去填写订单</a>
						</button>
					</tr>
				
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>