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
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>用户昵称</th>
        <th>建议内容</th>
        <th>创建时间</th>
        <th>操作</th>

    </tr>
    </thead>

    <tr>
        <td>蒹葭</td>
        <td>策</td>
        <td><a href="#">小强</a></td>
        <td>
            <button type="button">删除</button>
        </td>

    </tr>
</table>

</body>
</html>

