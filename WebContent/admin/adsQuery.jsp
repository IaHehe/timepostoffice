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

    </style>
</head>
<body>
<form class="form-inline definewidth m20" action="#" method="get">
    <font color="#777777"><strong>关键字：</strong></font> <input type="text"
                                                              name="menuname" id="menuname" class="abc input-default"
                                                              placeholder="" value="">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>
    &nbsp;&nbsp;
    <button type="button"  id="addnew">
        <a href="adsAdd.html">添加广告</a>
    </button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>广告标题</th>
        <th>广告内容</th>
        <th>创建时间</th>
        <th colspan="2">操作</th>

    </tr>
    </thead>
<c:forEach var="iAd" items="${iAds}">
    <tr>
      <td>${iAd.indexAdTitle}</td>
        <td>${iAd.indexAdContent}</td>
        <td>${iAd.indexAdTime}</td>
        <td>
            <button type="button"> <a href="adsAdd.html">修改</a></button>
        </td>
        <td>
            <button type="button">删除</button>
        </td>
    </tr>
</c:forEach>
</table>

</body>
</html>

