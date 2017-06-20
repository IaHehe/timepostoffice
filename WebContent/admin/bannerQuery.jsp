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
 <script src="js/jquery.js"></script>

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
        <th>时光信标题</th>
        <th>时光信内容</th>
        <th>图片</th>
        <th>是否显示到banner</th>

    </tr>
    </thead>

    <tr>
        <td>蒹葭</td>
        <td>策</td>
        <td><a href="#">小强</a></td>
        <td><img src="images/open.png" alt="Y" class="toggle" ></td>
    </tr>
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
</script>