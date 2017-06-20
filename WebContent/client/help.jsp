<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase }/client/">
    <meta charset="UTF-8">
    <title>${site} - 帮助文档</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/email.css">
    <script src="js/jquery.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="main">
    <div class="w">
        <div class="contact">
            <dl>使用帮助</dl>
            <c:forEach var="assistant" items="${assistants }">
            <dt>${assistant.assistantTitle }</dt>
            <dd>${assistant.assistantContent }</dd>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>


    