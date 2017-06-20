package com.cqust.tpo.controller;
/***********************************************************************
 * Module:  FeedBackServlet.java
 * Author:  DELL
 * Purpose: Defines the Class FeedBackServlet
 ***********************************************************************/

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.models.FeedBack;
import com.cqust.tpo.service.IFeedBackService;
import com.cqust.tpo.service.impl.FeedBackServiceImpl;

/** 在线反馈Servlet
 * 
 * @pdOid cd650c21-96fb-4a20-bc5c-a469b15d605b */
@WebServlet(name="feedbackservlet",
	urlPatterns={"/feedback"})
public class FeedBackServlet extends HttpServlet {
   /** @pdRoleInfo migr=no name=IFeedBackService assc=association4 mult=0..1 */
   private IFeedBackService iFeedBackService;
   /** @pdRoleInfo migr=no name=feedback assc=association46 mult=0..1 */
   private FeedBack feedback;
   
   /** 管理员，修改反馈看过的未看过的状态。
    * 
    * @pdOid cb670c1b-9fa8-40a9-925a-d11597922e29 */
   private void modifyFeedBackState() {
      // TODO: implement
   }
   
   /** @pdOid f01ebf03-23ac-40ec-94fd-fbc46acba705 */
     @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	 String choose=request.getParameter("choose");
      	 if(choose==null||choose.equals(""))
      		 {
      		 	RequestDispatcher rd=request.getRequestDispatcher("/client/index.jsp");
      		 	rd.forward(request, response);
      		 }
    	 if(choose.equals("feedback"))
    		 feedBack(request,response);
    	 if(choose.equals("save"));
    	 	save(request,response);
	}
  
   
   /** 前往用户反馈页面
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid a075fc15-8f3d-468d-a1a4-72dbc75bfacf */
   public void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   IFeedBackService ifb=new FeedBackServiceImpl();
	   request.setAttribute("feedback", ifb.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("");
	   rd.forward(request, response);
   }
   
   /** 保存反馈信息到数据库
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 9fedb730-dd8b-4f3c-80b2-81cff3fea0ac */
   public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   IFeedBackService ifb=new FeedBackServiceImpl();
	   FeedBack fb=new FeedBack();
	   ifb.insert(fb);
	   request.setAttribute("postguide", ifb.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("");
	   rd.forward(request, response);
   }
   
   /** 管理员，浏览所有反馈信息，看过的未看过的。页面呈现时根据Bean字段区分。
    * 
    * @pdOid 728ba6ec-581f-4861-a6dc-7f216f5405de */
   public void browseAllFeedBack() {
      // TODO: implement
   }
   
   /** 管理员，查看单个反馈详情，通过在线反馈信息的ID作为标识。方法内要调用modifyFeedBackState将此反馈信息状态修改为查看过（读过）。
    * 
    * @pdOid d45a10c6-a383-497e-bd0b-779a6f1bf554 */
   public void browseFeedBack() {
      // TODO: implement
   }

}