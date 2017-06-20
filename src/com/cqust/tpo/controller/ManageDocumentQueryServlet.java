package com.cqust.tpo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;

/**
 * @author DongJiJun
 *
 */
@WebServlet(name="/ManageDocumentQueryServlet",
urlPatterns={"/managedocumentquery/*"})

public class ManageDocumentQueryServlet extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素
		if("timeletterisOpen".equals(handle)){
			timeletterisOpen(request,response);
		}else if("timeletterisTop".equals(handle)){
			timeletterisTop(request,response);
		}
	}

	private void timeletterisTop(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String isTop = request.getParameter("isTop");
		int timeletterid = Integer.parseInt(request.getParameter("timeletterId"));
		System.out.println(">>>>>>>>>>>>>>");
		System.out.println(isTop);
		System.out.println(timeletterid);
		ITimeLetterService itls = new TimeLetterServiceImpl();
		itls.UpdateTimeLetterisTop(isTop, timeletterid);
	}

	private void timeletterisOpen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String isOpen = request.getParameter("isOpen");
		int timeletterid = 0;
		try {
			timeletterid = Integer.parseInt(request.getParameter("timeletterId"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return;
		}
		System.out.println(">>>>>>>>>>>>>>");
		System.out.println(isOpen);
		System.out.println(timeletterid);
		ITimeLetterService itls = new TimeLetterServiceImpl();
		itls.UpdateTimeLetterisOpen(isOpen, timeletterid);
	}
}
