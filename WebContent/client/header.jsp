<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--头部导航开始-->
<div class="shortcut clearfix">
    <div class="w">
        <div class="fl to">给现在一个期许，给未来一个回忆， 给未来的自己写一封信吧</div>
        <div class="fr">
            <c:set var="currentUser" value="${sessionScope.currentUser}"></c:set>
        	<c:choose>
        		<c:when test="${currentUser == null }">
        			<ul>
		                <li><a href="${clientBase }/user/login" id="login">登录</a></li>
		                <li><a href="${clientBase }/user/register" id="register">注册</a></li>
            		</ul>
        		</c:when>
        		<c:otherwise>
        			<ul class="fir-ul">
		                <li><a href="javascript:;" id="user">${currentUser.userName != null ? currentUser.userName : currentUser.userEmail} </a>，欢迎你</li>
		                <ul class="user-ul">
		                    <li><a href="${clientBase }/user/setting">设置</a></li>
		                    <li><a href="${clientBase }/user/exit">退出</a></li>
	                	</ul>
            		</ul>
        		</c:otherwise>
        	</c:choose>
        </div>
    </div>
</div>

<div class="nav clearfix">
    <div class="w">
        <h1>
            时光邮局
            <!--<img src="img/1.png" alt="">-->
        </h1>
        <ul id="links">
            <li><a href="${clientBase }">首页</a><s>▲</s></li>
            <li><a href="${clientBase }/timeLetter/email">时光信</a><s>▲</s></li>
            <li><a href="${clientBase }/letterpaperorder/papermail">纸信</a><s>▲</s></li>
            <li><a href="${clientBase }/timeLetter/openemail">信箱开启</a><s>▲</s></li>
            <li><a href="${clientBase }/timeLetter/browseOwnLetter">我的信箱</a><s>▲</s></li>
            <li><a href="${clientBase }/assistant/seeAssistant">帮助文档</a><s>▲</s></li>
            <li><a href="../connectus?choose=browseuseer">联系我们</a><s>▲</s></li>
        </ul>
        <div class="search">
            <input type="text" placeholder="标题、作者">
            <a href="#"><span></span></a>
        </div>
    </div>
</div>
<!--头部导航结束-->

<script>

</script>