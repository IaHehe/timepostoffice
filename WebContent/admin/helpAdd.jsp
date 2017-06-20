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
<c:if test="${dealMsg == 'save' }">
<form action="${adminBase}/assistant/saveAssistant" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
        <tr>
            <td class="tableleft">帮助文档标题</td>
            <td><input type="text" name="assistantTitle"/></td>
        </tr>

        <tr>
            <td class="tableleft">帮助文档内容</td>
            <td><textarea id="" cols="30" rows="10" name="assistantContent"></textarea></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button style="margin-left:5px;"type="submit" class="btn btn-primary" type="button"  >添加</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">
                	<a href="${adminBase }/assistant/browseAllAssistant">返回列表</a>
                </button>
            </td>
        </tr>
    </table>
</form>
</c:if>
<c:if test="${dealMsg == 'modify' }">
<form action="${adminBase}/assistant/modifyAssistant" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover m10" style="margin-left:10px;margin-top:3px;">
        <tr>
            <td class="tableleft">帮助文档标题</td>
            <td>
            	<input type="hidden" name="assistantId" value="${assistant.assistantId }"/>
            	<input type="text" name="assistantTitle" value="${assistant.assistantTitle }"/>
            </td>
        </tr>

        <tr>
            <td class="tableleft">帮助文档内容</td>
            <td><textarea id="" cols="30" rows="10" name="assistantContent" >${assistant.assistantContent }</textarea></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button style="margin-left:5px;"type="submit" class="btn btn-primary" type="button"  >修改</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">
                	<a href="${adminBase }/assistant/browseAllAssistant">返回列表</a>
                </button>
            </td>
        </tr>
    </table>
</form>
</c:if>

</body>
</html>

