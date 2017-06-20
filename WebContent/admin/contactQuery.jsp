<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${adminBase}/admin/" />
    <title>${site }</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />


    <style type="text/css">
        body {font-size: 20px;
            padding-bottom: 40px;
            background-color: #e9e7ef;
        }
        #detail{
            width: 500px;
            height: 500px;
            background-color: #fff;
            position: absolute;
            left: 35%;
            top: 18%;
            display: none;
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
    &nbsp;&nbsp;

</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>联系我们邮箱</th>
        <th>联系我们电话</th>
        <th>联系我们地址</th>
        <th colspan="2">操作</th>
    </tr>
    </thead>

    <c:forEach var="cu" items="${connectus }">
	    <tr>
	        <td>${cu.contactUsEmail }</td>
	        <td>${cu.contactUsPhone }</td>
	        <td>${cu.contactUsAddress }</td>
	        <td><button><a href="${adminBase}/connectus?choose=gotochange&id=${cu.contactUsId}">修改</a></button></td>
	    </tr>
    </c:forEach>
</table>


</body>
</html>


