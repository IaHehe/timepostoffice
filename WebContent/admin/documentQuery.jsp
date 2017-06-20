<%@ page language="java" import="java.util.*,com.cqust.tpo.models.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");	
	response.setContentType("text/html;charset=UTF-8");
	List tl = (List) request.getAttribute("timeletterlist");
	//List atr = (List) request.getAttribute("attentiontoprank");
	//List ctr = (List) request.getAttribute("clicktoprank");
	//int adl = (int) request.getAttribute("arrivedletter");
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${adminBase}/admin/" />
    <title>${site }</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
<script src="js/jquery.js"></script>

<style type="text/css">
body {font-size: 20px;
	padding-bottom: 40px;
	background-color: #e9e7ef;
}
#detail{
	width: 800px;
	height: 300px;
	background-color: #fff;
	position: absolute;
	left: 15%;
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
		&nbsp;&nbsp;
	</form>
	<table style="table-layout:fixed" class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th style="display:none">333</th>
				<th>标题</th>
				<th>内容</th>
				<th>图片</th>
				<th>发信人</th>
				<th>收信人</th>
				<th>发送时间</th>
				<th>时光信状态</th>
				<th>是否公开</th>
				<th>操作</th>

			</tr>
		</thead>
		<%
			for(int i=0; i<tl.size(); i++){
				TimeLetter t = (TimeLetter) tl.get(i);
		%>
		<tr>
			<td style="display:none"><input style="display:none" value="<%=t.getTimeLetterId() %>" id="timeletterid" /></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterTitle() %></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterContent()%></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterImg() %></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getUser().getUserEmail() %></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getLetterRecipent() %></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getLetterSendTime() %></td>
			<td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterState() %></td>
			<td><img src="images/open.png" alt="Y" class="toggle" ></td>
			<td>
				<button type="submit" id="details">详细信息</button>
			</td>


		</tr>
		<%} %>
	</table>
<div id="detail">
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th>创建时间</th>
			<th>是否允许上排行榜</th>
			<th>是否禁止显示到信箱开启</th>
			<th>是否推荐</th>
			<th>关注量</th>
			<th>点赞量</th>
		</tr>
		<tr>
			<td>是</td>
			<td><img src="images/open.png" alt="Y" class="toggle" ></td>
			<td><img src="images/open.png" alt="Y" class="toggle" ></td>
			<td><img src="images/open.png" alt="Y" class="toggle" ></td>
			<td>11</td>
			<td>11</td>
		</tr>
	</table>
	<button style="margin: 100px 300px;" id="close">关闭</button>
</div>

</body>
</html>

<script>
//	//详情信息弹出框
	var details=document.getElementById("details");
	var detail_show=document.getElementById("detail");
	var close=document.getElementById("close");
	details.onclick= function () {
		detail_show.style.display="block";
	}
	close.onclick= function () {
		detail_show.style.display="none";
	}

	//点击开关的事件
	$(".toggle").click(function () {
		if ($(this).attr("alt") == "Y") {
			$(this).attr("src", "images/close.png");
			$(this).attr("alt","N");
		} else {
			$(this).attr("src", "images/open.png");
			$(this).attr("alt","Y");
		}
	});
	$(".toggle").click(function () {
//		console.log("11111111");
		var tlid = $(this).parent().parent().find("#timeletterid").val();
//		console.log("tlid:" + tlid);
		$.ajax({
			url: "/timepostoffice/managedocumentquery/timeletterisOpen",
			type: 'post',
			data: {
				isOpen: $(this).attr("alt"),
				timeletterId: tlid
			},
			dataType: 'json',
			success: function (data) {
				if (data == "1") {
					if ($(this).attr("alt") == "Y") {
						$(this).attr("src", "images/close.png");
						$(this).attr("alt","N");
					} else {
						$(this).attr("src", "images/open.png");
						$(this).attr("alt","Y");
					}
				}
			}
		});
	});

</script>
