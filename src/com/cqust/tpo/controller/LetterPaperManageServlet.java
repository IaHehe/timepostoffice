package com.cqust.tpo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqust.tpo.models.LetterPaperOrder;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.ILetterPaperOrderService;
import com.cqust.tpo.service.IPostGuideService;
import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.impl.LetterPaperOrderServiceImpl;
import com.cqust.tpo.service.impl.PostGuideServiceImpl;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;


@WebServlet(name="/LetterPaperManageServlet",
urlPatterns={"/letterpapermanage/*"})
public class LetterPaperManageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		1.编码集修改
//		2.获得path，最后那个小节
//		3.解析这个小节进入相对应的方法体，完成请求响应动作----service()
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素 
		if ("papermailquery".equals(handle)) {
			try {
				list(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("false");
			}
		}
		if("submitdetails".equals(handle)){
			try{
				submitdetails(request,response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
//		订单界面

	
}

	//根据ID来查询sql语句，将查询到的值传入到后台管理界面，点击详情按钮显示详情
	private void submitdetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id =0;
		try{
			System.out.println("GETIN");
			id = Integer.parseInt(request.getParameter("lpId"));	
		}catch(NumberFormatException e){
			e.printStackTrace();
		}	
		ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
		LetterPaperOrder result = letterpaperorderService.get(id);
		request.setAttribute("letterpaper", result);
		request.getRequestDispatcher("/admin/paperMailQuery.jsp").forward(request, response);
		System.out.println(result);
	}


	//查询Letterpaper里所有的属性，然后将得到的需要显示的值封装，传递到后台管理员管理纸信订单中
	private void list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
		if(letterpaperorderService.list()!=null)
		{
			try {
				request.setAttribute("papermailquerylist", letterpaperorderService.list());
				request.getRequestDispatcher("/admin/paperMailQuery.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("error");
		}
	}
		
	
}

