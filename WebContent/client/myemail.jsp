<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="mt" uri="/WEB-INF/tld/mt.tld" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base href="${clientBase }/client/"/>
    <title>${site} - 我的信箱</title>
    <link rel="stylesheet" href="${clientBase }/client/css/base.css">
    <link rel="stylesheet" href="${clientBase }/client/css/email.css">
    <script src="${clientBase }/client/js/jquery.js"></script>
    <script src="${clientBase }/client/js/index.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="myemail">
    <div class="w">
        <div class="my">
            <table>
                <th>邮件管理</th>
                <tr>
                    <td>邮件名称</td>
                    <td>创建时间</td>
                    <td>发送时间</td>
                    <td>收件人邮箱</td>
                    <td>我的邮箱</td>
                    <td>操作</td>
                </tr>
                <%-- 
                <form action="${clientBase }/user/index" method="POST">
    <td>${letterPaperOrder.letterPaperNumber }</td>
    <td>${letterPaperOrder.letterPaperCreateTime }</td>
    <td>${letterPaperOrder.dateOfMailing }</td>
    <td>${letterPaperOrder.recipientAddress }</td>
    <td>${letterPaperOrder.recipientPostNumber }</td>
    <td>${letterPaperOrder.letterPaperPrice }</td>
    <td><input type="submit" id="delete_btn" id="bl" value="删除" class="delete-btn"></td>
    </form> --%>
					<c:forEach var="timeletter" items="${timeletterlist }">
						<tr id="aim">
							<td>${timeletter.timeLetterTitle }</td>
							<td><mt:toDateString date="${timeletter.createTime }" /></td>
							<td><mt:toDateString date="${timeletter.letterSendTime }" /></td>
							<td>${timeletter.letterRecipent }</td>
							<td>${timeletter.timeLetterState }</td>
							<td>
								<input name="id" value="${timeletter.timeLetterId }" type="hidden"/>
								<input type="button" id="bl" value="删除" class="delete-btn">
							</td>
						</tr>
					</c:forEach>
				</table>
            <table>
                <th>纸信管理</th>
                <tr>
                    <td>纸信名称</td>
                    <td>创建时间</td>
                    <td>寄送时间</td>
                    <td>收件人地址</td>
                    <td>邮编</td>
                    <td>寄存费</td>
                    <td>操作</td>
                </tr>
				<c:forEach var="letterPaperOrder" items="${letterPaperOrderList }">
				
				<tr>
				    <td>${letterPaperOrder.letterPaperNumber }</td>
				    <td><mt:toDateString date="${letterPaperOrder.letterPaperCreateTime }"/></td>
				    <td><mt:toDateString date="${letterPaperOrder.dateOfMailing }" format="yyyy-MM-dd"/></td>
				    <td>${letterPaperOrder.letterPaperAddress }</td>
				    <td>${letterPaperOrder.letterPaperPostNumber }</td>
				    <td>${letterPaperOrder.letterPaperPrice }</td>
				    <td>
				    <input name="id" value="${letterPaperOrder.letterPaperId }" type="hidden"/>
				    <input type="button" id="delete_btn" value="删除" class="delete_btn2"></td>
				</tr>
				</c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
 <script src="js/jquery.js"></script> 
<script type="text/javascript">
	<!--  操作按钮点击处理事件  -->
	$(".delete-btn").click(function(){
		var id = $(this).parent().find("input[name='id']").val();
		var path = "delete?id=" + id;
		$.ajax({
			url:path, 
			success:function(data){
				var flag = false;
				/* console.log("data:" + data);
				console.log("size:" + data.length);
				console.log("boolean:" + (data == "success")); */
				if(data == "success"){
					flag = true;
				}
				/* console.log("flag:" + flag); */
				if(flag){
					console.log("delete success");
					console.log("html:" + $(this).val());
					$(this).parent("tr").remove();
				}else{
					alert("delete  error");
				}
			}
		});
	});
	$(".delete_btn2").click(function()
	{
		var id=$(this).parent().find("input[name='id']").val();
		var path = "submitdelete?id=" + id;
		console.log("path::" + path);
		$.post(path,function(data){
		console.log("server say:" + data);
		});
	});
</script>
</body>
</html>