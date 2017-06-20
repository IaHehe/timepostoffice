<%@ page language="java" import="java.util.*,com.cqust.tpo.models.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
//	List tl = (List) request.getAttribute("dtimeletter");
	List tl = (List)request.getAttribute("dtimeletter");
	List tl2 = (List)request.getAttribute("timeletter");
	List tl3 = (List)request.getAttribute("lettermessageboard");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${clientBase}/client/" />
    <meta charset="UTF-8">
    <title>${site} - 邮件详情</title>
    <link rel="stylesheet" href="css/base.css">    <!--页面公共部分css-->
    <link rel="stylesheet" href="css/index.css">
    <script src="js/jquery.js"></script>
    <script src="js/index.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<!--详情显示开始-->
<%
TimeLetter a = (TimeLetter)tl.get(0);
%>

<div class="main">
    <div class="w clearfix">
        <div class="main-content main-details clearfix">
            <h2><%=a.getTimeLetterTitle() %></h2>
            <div class="details-box clearfix">
                <div class="head fl"><img src="img/head1.jpg" alt=""></div>
                <div class="email email-details fr">
                    <div class="e-head col-blue">来自：<%=a.getUser().getUserName() %>  <%=a.getCreateTime() %>  寄送时间：<%=a.getLetterSendTime() %> <%=a.getTimeLetterState() %></div>
                    <div class="details-content ">
                    <%=a.getTimeLetterContent() %>
                    </div>
                </div>
            </div>
            <div class="guesslike">
                <h6>猜你喜欢</h6>
                <%
                	for(int i=0; i<tl2.size(); i++){
                		TimeLetter t = (TimeLetter) tl2.get(i);
                %>
                <div class="guess-block">
                <a href="${clientBase }/lettermessageboard/details?timeletterid=<%=t.getTimeLetterId()%>">
                    		<img src="img/head1.jpg" alt=""><%=t.getTimeLetterTitle()%>
                    	</a>
                </div>
                <%} %>
               <!--  <div class="guess-block"><a href="#"><img src="img/head2.png" alt="">有一种岁月叫时光</a></div> -->
            </div>
            <%
            	for(int j=0; j<tl3.size(); j++){
            		LetterMessageBoard lmb = (LetterMessageBoard) tl3.get(j);
            %>
            <div class="email-box clearfix">
                <div class="fl"><img src="img/head1.jpg" alt="" style="margin-top: 23px"></div>
                <div class="email fr">
                    <div class="details-content"><%=lmb.getMessageContent() %></div>
                    <span class="e-from">来自<%=lmb.getUser().getUserName() %></span>
                    <span class="e-sendtime"><%=lmb.getMessageCreateTime() %></span>
                    <!-- <span class="fr"><a href="javascript:;">回应</a></span> -->
                </div>
            </div>
            
              <%} %>
            <textarea name="messageboardcontent" class="talk"></textarea>
            <button id="reply"><a href="details.jsp" id="updatemessageboard">回应</a></button>
        </div>
    </div>
</div>
<!--详情显示结束-->
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

<script>
    var reply=document.getElementById("reply");
    var text= $(".talk").innerHTML();
    var email_box=$(".email-box");

    reply.onclick= function () {
        if(text == ""){
            alert("请输入内容");
            return false;  //终止函数执行
        }
        $(".email-box").after("<div class='add clearfix'> <div class='fl'><img src='img/head1.jpg' style='margin-top: 23px'></div> <div class='email fr'> <div class='details-content'>"+text+"</div> <span class='e-from'>来自ws</span> <span class='e-sendtime'>2017-2-3 10:02</span> <span class='fr'><a href='javascript:;'>回应</a></span></div> </div>");
        text.val="";

    }

</script>