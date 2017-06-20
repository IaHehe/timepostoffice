<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.cqust.tpo.models.LetterPaperOrder"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	List lpo = (List) request.getAttribute("letterpaperorder");
%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<base href="${adminBase}/admin/" />
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script src="js/jquery.js"></script>

<style type="text/css">
body {
	font-size: 20px;
	padding-bottom: 40px;
	background-color: #e9e7ef;
}

.detail-show {
	width: 800px;
	height: 300px;
	background-color: #fff;
	position: absolute;
	left: 23%;
	top: 18%;
	display: none;
}
</style>
</head>
<body>
	<form class="form-inline definewidth m20" action="#" method="get">
		<font color="#777777"><strong>关键字：</strong></font> <input type="text"
			name="menuname" id="menuname" class="abc input-default"
			placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>

	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>纸信编号</th>
				<th>收信人姓名</th>
				<th>寄出日期</th>
				<th>订单价格</th>
				<th>订单状态</th>
				<th>纸信状态</th>
				<th>纸信信息是否允许修改</th>
				<th>操作</th>

			</tr>
		</thead>
		<c:forEach var="letterpaperorder" items="${papermailquerylist}">
			<tr>
				<td>${letterpaperorder.letterPaperNumber }</td>
				<td>${letterpaperorder.letterPaperName }</td>
				<td>${letterpaperorder.dateOfMailing}</td>
				<td>${letterpaperorder.letterPaperPrice }</td>
				<td>
				 <select name="letterpaperstate" id="letterPaperState" >   
        			<option value=1>初始态</option>   
        			<option value=2>等待接收</option>   
        			<option value=3>保管中</option>   
        			<option value=4>已寄出</option>	
     			 </select>
     			 </td>
			<%-- 	<td>${letterpaperorder.letterPaperState }</td> --%>
				<td>${letterpaperorder.orderState }</td>
				<td><img src="images/open.png" alt="Y" class="toggle"></td>
				<td>
				    <input name="id" value="${letterpaperorder.letterPaperId }" type="hidden"/>

					<button type="button" id="details_btn" letter-id="${letterpaperorder.letterPaperId }" class="details-btn">详细信息</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:forEach var="letterpaperorder" items="${papermailquerylist}">
	<div class="detail-show" id="detail_show${letterpaperorder.letterPaperId }">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<th>收信人地址</th>
				<th>收信人邮编</th>
				<th>收信人电话</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>客户QQ</th>
			</tr>
			<tr>

 				<td>${letterpaperorder.letterPaperAddress }</td>
				<td>${letterpaperorder.letterPaperPostNumber }</td>
				<td>${letterpaperorder.letterPaperPhone }</td>
				<td>${letterpaperorder.customerName }</td>
				<td>${letterpaperorder.customerPhone }</td>
				<td>${letterpaperorder.customerQQ }</td> 

			</tr>
		</table>
		<button style="margin: 150px 350px;" id="close" class="close-showBox">关闭</button>
	</div>
	</c:forEach>
	
</body>
<script>
	var letter_id;
	var showBox;
	//详情信息框弹出
	$(".details-btn").click(function() {
		letter_id = $(this).attr("letter-id");
		showBox = "#detail_show"+letter_id;
		$(showBox).show();
	});
	//详情信息框关闭
	$(".close-showBox").click(function() {
		$(showBox).hide();
	});
	

	//点击开关的事件
	$(".toggle").click(function() {
		if ($(this).attr("alt") == "Y") {
			$(this).attr("src", "images/close.png");
			$(this).attr("alt", "N");
		} else {
			$(this).attr("src", "images/open.png");
			$(this).attr("alt", "Y");
		}
	});
</script>

</html>