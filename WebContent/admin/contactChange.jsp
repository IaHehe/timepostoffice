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
            background-color:#e9e7ef;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
</head>
<body>
 <form action="${adminBase}/connectus" method="get" class="definewidth m20" enctype="multipart/form-data">
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="email" required name="email"/></td>
        </tr>

        <tr>
            <td class="tableleft">电话</td>
            <td><input type="tel" required name="tel"/></td>
        </tr>
        <tr>
            <td class="tableleft">地址</td>
            <td><input type="text" name="address"/><input type="hidden" name="id" value="${id}"  /></td>
           
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
				<input type="hidden" name="choose" value="modify"/>
                <input style="margin-left:5px;" type="submit" class="btn btn-primary" value="保存"></input> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">
                <a href="${adminBase}/connectus?choose=browse">返回列表</a></button>
            </td>
        </tr>
    </table>
</form>

</body>
</html>

