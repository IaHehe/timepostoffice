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
        <a href="${adminBase }/assistant/saveAssistant">添加文档</a>
    </button>
</form>
<c:if test="${msg != null}">
	<h2 style="text-align: center">${msg }</h2>
</c:if>
<c:if test="${msg == null }">
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>帮助信息标题</th>
        <th>帮助信息内容</th>
        <th>帮助信息创建时间</th>
        <th colspan="2">操作</th>

    </tr>
    </thead>
	<c:forEach var="assistant" items="${assistants }">
    <tr>
        <td>${assistant.assistantTitle }</td>
        <td>${assistant.assistantContent}</td>
        <td>
        	${assistant.assistantTime }
        <td>
            <button type="button"> <a href="${adminBase }/assistant/modifyAssistant?assistantId=${assistant.assistantId}">修改</a></button>
        </td>
        <td>
            <button class="del-btn" type="button" del-url="${adminBase }/assistant/deleteAssistant?aid=${assistant.assistantId }">删除</button>
        </td>

    </tr>
    </c:forEach>
</table>
</c:if>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$(".del-btn").click(function(){
			//获取tr下第一个td的文本
			//var $tr = $(this).parent().parent();
			//alert($tr.children('td').eq(0).text());
			
			var flag = confirm("确定要删除这条帮助信息吗?");
			if(flag) {
				//获取当前按钮所在的行的tr节点
				var $tr = $(this).parent().parent();
				var url = $(this).attr("del-url");
				var args = {time:new Date()};
				
				$.post(url,args,function(data){
					if(data == "1") { //返回 1 表示删除成功
						alert("删除成功!")
						$tr.remove();
					}else { // 返回 0 表示删除失败
						alert("删除失败!");
					}
				});
			}
			return false; //取消超链接的默认行为
		});
	})

</script>

</body>
</html>

