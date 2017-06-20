package com.cqust.tpo.controller;
/***********************************************************************
 * Module:  ConnectUsServlet.java
 * Author:  matengyu
 * Purpose: Defines the Class ConnectUsServlet
 ***********************************************************************/

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.models.ContactUs;
import com.cqust.tpo.models.PostGuide;
import com.cqust.tpo.service.IConnectUsService;
import com.cqust.tpo.service.IPostGuideService;
import com.cqust.tpo.service.impl.ConnectUsServiceImpl;
import com.cqust.tpo.service.impl.PostGuideServiceImpl;

/** 联系我们Servlet
 * 
 * @pdOid 9ddb292f-d089-4ed5-8c10-3689341b599e */
@WebServlet(name="connectusservlet",
	urlPatterns={"/connectus"})
public class ConnectUsServlet extends HttpServlet{
   /** @pdRoleInfo migr=no name=IConnectUsService assc=association6 mult=0..1 */
   private IConnectUsService iConnectUsService;
   /** @pdRoleInfo migr=no name=ConnectUs assc=association62 mult=0..1 */
   private ContactUs connectUs;
   
   /** @throws IOException 
 * @throws ServletException 
 * @pdOid 9fbae186-2826-4808-80f1-ca7672c3591a */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: implement
		
		System.out.println("ServletPath:" + request.getRequestURI());

//		Enumeration<String> enums = request.getParameterNames();
//		while(enums.hasMoreElements()){
//			System.out.println(enums.nextElement());
//		}
		
		String choose = request.getParameter("choose");
		System.out.println("choose:" + choose);
		if (choose == null || choose.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/client/index.jsp");
			rd.forward(request, response);
			return;
		}

		if (choose.equals("browse")) {
			browseConnectUs(request, response);
		}
		if (choose.equals("modify"))
		{
			modifyConnectUs(request, response);
		}
		if (choose.equals("gotochange")) {
			goToChange(request, response);
		}
		if (choose.equals("browseuseer")) {
			browseConnectUsUser(request, response);
		}

	}
   
   /** 管理员和用户共用。浏览联系我们信息。除了页面展示没有按钮之外其他一致。其实调用的Service层方法是一样的。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid c0c0cd81-0fc5-4ab2-83e1-e5694bef5c9e */
   public void browseConnectUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   System.out.println("asd");
	   IConnectUsService icu=new ConnectUsServiceImpl();
	   request.setAttribute("connectus", icu.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("/admin/contactQuery.jsp");
	   rd.forward(request, response);
   }
   
   public void browseConnectUsUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // TODO: implement
		   System.out.println("asd");
		   IConnectUsService icu=new ConnectUsServiceImpl();
		   request.setAttribute("connectus", icu.list()); 
		   RequestDispatcher rd=request.getRequestDispatcher("/client/contact.jsp");
		   rd.forward(request, response);
	   }
   
   /** 管理员，修改联系我们信息。
 * @throws IOException 
 * @throws ServletException 
    * 
    * @pdOid 65f59dd0-4eed-44ef-9de7-0dae89e8760b */
   public void modifyConnectUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO: implement
	   IConnectUsService icu=new ConnectUsServiceImpl();
	   ContactUs pg=new ContactUs();
	   pg.setContactUsAddress(request.getParameter("address"));
	   pg.setContactUsEmail(request.getParameter("email"));
	   String idStr = request.getParameter("id");
	   System.out.println("---->"+idStr);
	   try {
		   int id = Integer.parseInt(idStr);
		   pg.setContactUsId(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	   pg.setContactUsPhone(request.getParameter("tel"));
	  
	   icu.update(pg);
	   request.setAttribute("connectus", icu.list()); 
	   RequestDispatcher rd=request.getRequestDispatcher("/admin/contactQuery.jsp");
	   rd.forward(request, response);
   }
   
   public void goToChange(HttpServletRequest request, HttpServletResponse response)
   {
	   
	   
	   try {
		   int  id=Integer.parseInt(request.getParameter("id"));
		   //查询
		   System.out.println(id);
		   request.setAttribute("id", id);
		   RequestDispatcher rd=request.getRequestDispatcher("/admin/contactChange.jsp");
		rd.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
   }

}
