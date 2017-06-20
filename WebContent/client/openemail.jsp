<%@ page language="java" import="java.util.*,com.cqust.tpo.models.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	List tl = (List) request.getAttribute("timeletter");
	List atr = (List) request.getAttribute("attentiontoprank");
	List ctr = (List) request.getAttribute("clicktoprank");
//	int adl = (int) request.getAttribute("arrivedletter");

%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 时光邮局</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<!--主页显示开始-->
<%-- <%=adl %> --%>
<%-- 	<c:forEach var="t" items="${timeletter }">
		${t.clickNumber }
	</c:forEach> --%>
        <div class="main">
            <div class="w clearfix">
                    <div class="main-content fl">
                    
                    
           <%
        	for(int j=0; j<tl.size(); j++){
        		TimeLetter t = (TimeLetter) tl.get(j);
        %>
       <div class="email-box clearfix open-box">
        <div class="like fl"><%=t.getClickNumber() %><br>喜欢</div>
                <div class="email fr">
                <!-- ${clientBase }/timeLetter/details -->
                	<input type="hidden" name="timeletterid" value="<%=t.getTimeLetterId() %>"/>
                    <div class="e-head col-blue">
                    	<a href="${clientBase }/lettermessageboard/details?timeletterid=<%=t.getTimeLetterId()%>">
                    		<%=t.getTimeLetterTitle()%>
                    	</a>
                   	</div>            
                    <div class="e-content"><%=t.getTimeLetterContent() %></div>
                    <span class="e-from">来自<%=t.getUser().getUserName() %></span>
                    <span class="e-creattime">创建时间：<%=t.getCreateTime() %></span>
                    <span class="e-sendtime">发送时间：<%=t.getLetterSendTime() %></span>
					<span class="e-type"><%=t.getTimeLetterState() %></span>
                    <span class="fr see"><img src="img/look.png" alt="" width="23" height="23"><%=t.getAttentionNumber() %></span>
                </div>
           </div>
        <%} %>

<%--         <c:forEach var="t" items="${timeletter}">
             <div class="email-box clearfix open-box">
                <div class="like fl">${t.timeletter.clickNumber }<br>喜欢</div>
                <div class="email fr">
                    <div class="e-head col-blue"><a href="${clientBase }/lettermessageboard/details?timeletterid=${t.timeletter.timeLetterId}">${t.timeLetterTitle }</a></div>
                    <div class="e-content"><td>${t.timeletter.timeLetterContent }</td></div>
                  	<span class="e-from">来自${t.user.UserName }</span>
                    <span class="e-creattime">创建时间：${t.timeletter.createTime }</span>
                    <span class="e-sendtime">发送时间：${t.timeletter.letterSendTime }</span>
                    <span class="e-type">${t.timeletter.timeLetterState }</span>
                    <span class="fr see"><img src="img/look.png" alt="" width="23" height="23">${t.timeletter.attentionNumber }</span>
                </div>
            </div>
        </c:forEach> --%>
            <ol>
                <li><a href="#">&lt;前页</a></li>
                <li class="this-page"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">6</a></li>
                <li><a href="#">7</a></li>
                <li><a href="#">8</a></li>
                <li><a href="#">9</a></li>
                <li>...</li>
                <li><a href="#">30</a></li>
                <li><a href="#">31</a></li>
                <li><a href="#">后页></a></li>
            </ol>
        </div>
        
        <div class="attention fr">
            <h3>关注排行榜</h3>
            <hr>
        <%
        	for(int i=0; i<atr.size(); i++){
        		TimeLetter t2 = (TimeLetter) atr.get(i);
        %>
            <ul class="col-blue">
                <li><a href="${clientBase }/lettermessageboard/details?timeletterid=<%=t2.getTimeLetterId()%>">
                    		<%=t2.getTimeLetterTitle()%>
                    	</a><i><img src="img/hot1.gif" alt=""></i> （+<%=t2.getAttentionNumber() %>）</li>
            </ul>
        <%} %>
        </div>
        
        <div class="attention fr">
            <h3>喜爱排行榜</h3>
            <hr>
        <%
        	for(int k=0; k<ctr.size(); k++){
        		TimeLetter t3 = (TimeLetter) ctr.get(k);
        %>
            <ul class="col-blue">
                <li><a href="${clientBase }/lettermessageboard/details?timeletterid=<%=t3.getTimeLetterId()%>">
                    		<%=t3.getTimeLetterTitle()%>
                    	</a></a><s><img src="img/hot2.gif" alt=""></s> &nbsp;（+<%=t3.getClickNumber() %>）</li>

            </ul>
        
        <%} %>
        </div>
    </div>
</div>
<!--主页显示结束-->
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>