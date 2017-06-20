
<%@ page language="java" import="java.util.*,com.cqust.tpo.models.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");	
	response.setContentType("text/html;charset=UTF-8");
	List tl = (List) request.getAttribute("timeletterlist");
%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <base href="${adminBase}/admin/" />
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
        .detail{
            width: 500px;
            height: 500px;
            background-color: #fff;
            position: absolute;
            left: 35%;
            top: 18%;
        }
        .sidebar-nav {
            padding: 9px 0;
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
<table style="table-layout:fixed" class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
    	<th style="display:none">333</th>
        <th>标题</th>
        <th>内容</th>
        <th>点赞量</th>
        <th>禁止显示到排行榜</th>

    </tr>
    </thead>
	<% 
		for(int i=0; i<tl.size(); i++){
			TimeLetter t = (TimeLetter) tl.get(i);
	%>
    <tr>
        <td style="display:none"><input style="display:none" value="<%=t.getTimeLetterId() %>" id="timeletterid" /></td>
        <td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterTitle() %></td>
        <td style="overflow:hidden;text-overflow:ellipsis" nowrap><%=t.getTimeLetterContent() %></td>
        <td ><%=t.getClickNumber() %></td>
        <td style="overflow:hidden;text-overflow:ellipsis" nowrap><img src="images/open.png" alt="Y" class="toggle" ></td>
    </tr>
    <%} %>
</table>

</body>
</html>

<script>
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
		var tlid = $(this).parent().parent().find("#timeletterid").val();
		$.ajax({
			url: "/timepostoffice/managedocumentquery/timeletterisTop",
			type: 'post',
			data: {
				isTop: $(this).attr("alt"),
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