<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="${adminBase }/manager/searchUsers" method="post">
    <font color="#777777"><strong>关键字：</strong></font> <input type="text"
                                                              name="searchTxt" id="menuname" class="abc input-default"
                                                              placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
    &nbsp;&nbsp;

</form>
<c:if test="${msg != null }">
	<h2>${msg }</h2>
</c:if>
<c:if test="${msg == null }">
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>用户昵称</th>
        <th>邮箱</th>
        <th>头像</th>
        <th>创建时间</th>
        <th>上次登录时间</th>
        <th>禁止写时光信</th>


    </tr>
    </thead>
	<c:forEach var="user" items="${users }">
		<tr>
	        <td>${user.userName }</td>
	        <td>${user.userEmail}</td>
	        <td>${user.userName }的头像</td>
	        <td>${user.createTime }</td>
	        <td>${user.lastTime }</td>
	        <c:if test="${user.isForbidWrite == 'Y' }">
	        	<td><img src="images/open.png" alt="Y" class="toggle" id=${user.userId }></td>
	        </c:if>
	        <c:if test="${user.isForbidWrite == 'N' }">
	        	<td><img src="images/close.png" alt="N" class="toggle" id=${user.userId }></td>
	        </c:if>
    	</tr>	
	</c:forEach>
</table>
</c:if>
</body>
</html>
<script>
	//点击开关的事件
	$(".toggle").click(function () {
	    var userId = $(this).attr("id");
	    var isForbid = $(this).attr("alt") == "Y" ? "N" : "Y";
	    var alt = $(this).attr("alt");
	    var $this = $(this);
	
	     $.ajax({
	        url:"/timepostoffice/manager/forbid",
	        type:'post',
	        data:{
	            userId: userId,
	            isForbid:isForbid,
	        },
	        dataType:'json',
	        success: function (data) {
	            if (data == "1")
	            {
	            	if($this.attr("alt") == "Y") {
	            		$this.attr("src", "images/close.png");
	                	$this.attr("alt","N");
	            	} else {
	                	$this.attr("src", "images/open.png");
	                	$this.attr("alt","Y");
	                }
	            }
	        }
	    }); 
	});
</script>