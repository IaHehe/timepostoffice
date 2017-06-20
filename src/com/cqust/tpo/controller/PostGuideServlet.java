package com.cqust.tpo.controller;
/***********************************************************************
 * Module:  PostGuideServlet.java
 * Author:  matengyu
 * Purpose: Defines the Class PostGuideServlet
 ***********************************************************************/

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.dao.IPostGuideDao;
import com.cqust.tpo.dao.impl.PostGuideDaoImpl;
import com.cqust.tpo.models.PostGuide;
import com.cqust.tpo.service.IPostGuideService;
import com.cqust.tpo.service.impl.PostGuideServiceImpl;

/** @pdOid 337a7214-94ae-4626-b64b-167d0b310b05 */
@WebServlet(name="postguideservlet",urlPatterns={"/postguide"})
public class PostGuideServlet extends HttpServlet{
   /** @pdRoleInfo migr=no name=IPostGuideService assc=association9 mult=0..1 */
   private IPostGuideService iPostGuideService;
   /** @pdRoleInfo migr=no name=PostGuide assc=association56 mult=0..1 */
   private PostGuide postGuide;
   
   /** @pdOid eeda916f-1f41-4350-a4c4-73fc118c69db */
   @Override
  	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	 String choose=request.getParameter("choose");
      	 
      	 
      	 if(choose==null||choose.equals(""))
      	 {
   		 	RequestDispatcher rd=request.getRequestDispatcher("/client/index.jsp");
   		 	rd.forward(request, response);
   		 }
      	 PostGuide pg=new PostGuide();

      	 if(choose.equals("browsePostGuide"))
      		 browsePostGuide(request,response);
      	 if(choose.equals("save"))
      	 	 savePostGuide(request,response);
      	 if(choose.equals("modify"))
      		modifyPostGuide(request,response);
      	 if(choose.equals("delete"))
      		deletePostGuide(request,response);
  	}
   
   /** 查看纸信指南，管理员和用户都要用到。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 98456dcc-dd0a-436b-9ff5-3a0595f3b0df */
   public void browsePostGuide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   //request.getSession().getAttribute("")
	   IPostGuideService ip=new PostGuideServiceImpl();
	   System.out.println(ip.list());
	  request.setAttribute("postguide", ip.list()); 
	  RequestDispatcher rd=request.getRequestDispatcher("/client/papermail.jsp");
	   rd.forward(request, response);
	  
   }
   
   /** 删除纸信指南，管理员使用，使用指南id作为标识。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 4788a543-b359-458a-a698-75de8577dc0f */
   public void deletePostGuide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   //request.getSession().getAttribute("");
	   IPostGuideService ip=new PostGuideServiceImpl();
	   int id = 0;
	   ip.delete(id);
	   request.setAttribute("postguide", ip.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("/paper.jsp");
	   rd.forward(request, response);
   }
   
   /** 修改纸信指南，管理员使用，使用指南id作为标识。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 8dfbbc3f-89d4-45bf-8e3c-fa55bea1af8a */
   public void modifyPostGuide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   IPostGuideService ip=new PostGuideServiceImpl();
	   PostGuide pg=new PostGuide();
	   ip.update(pg);
	   request.setAttribute("postguide", ip.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("/paper.jsp");
	   rd.forward(request, response);
   }
   
   /** 保存纸信指南，管理员使用。数据库新增一条记录。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 64c46dc4-f987-4b52-a998-6c6989a6bfc4 */
   public void savePostGuide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   IPostGuideService ip=new PostGuideServiceImpl();
	   //request.getParameter("")
	   PostGuide pg=new PostGuide();
	   ip.insert(pg);
	   request.setAttribute("postguide", ip.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("");
	   rd.forward(request, response);
   }

}