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
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>反馈内容</th>
        <th>反馈时间</th>
        <th>反馈查看状态</th>
        <th>操作</th>
    </tr>
    </thead>

    <tr>
        <td>2016.07.22</td>
        <td>2016.07.22</td>
        <td>2016.07.22</td>
        <td><button>删除</button></td>


    </tr>
</table>

</body>
</html>

