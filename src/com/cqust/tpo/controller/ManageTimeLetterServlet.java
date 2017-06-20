package com.cqust.tpo.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.IUserService;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;
import com.cqust.tpo.service.impl.UserServiceImpl;

/**
 * AttentionQuery(关注量排行榜）、documentQuery(是否公开)、LikelistQuery(点赞排行榜）
 * 浏览所有时光信、是否允许时光信显示到（首页、前台、排行榜）、查看时光信详情
 * @author DongJiJun
 *
 */
@WebServlet(name="/ManageTimeLetterServlet",
urlPatterns={"/managetimeletter/*"})
public class ManageTimeLetterServlet extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素
		if("documentQuery".equals(handle)){
			documentQuery(request,response);
		}else if("likeListQuery".equals(handle)){
			likeListQuery(request,response);
		}else if("attentionListQuery".equals(handle)){
			attentionListQuery(request,response);
		}
	}

	private void attentionListQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		ITimeLetterService itls = new TimeLetterServiceImpl();
		request.setAttribute("timeletterlist", itls.AttentionToplist());
		request.getRequestDispatcher("/admin/attentionListQuery.jsp").forward(request, response);
	}

	private void likeListQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		ITimeLetterService itls = new TimeLetterServiceImpl();
		request.setAttribute("timeletterlist", itls.ClickToplist());
		request.getRequestDispatcher("/admin/likeListQuery.jsp").forward(request, response);
	}
	
	
	/**
	 * 管理员点击时光信管理中的“浏览所有时光信”按钮，将数据库中的相关信息显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void documentQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//IUserService userService = new UserServiceImpl();
		//IManageTimeLetterService lmtls = new ManageTimeLetterServiceImpl();
		
		/*String isOpen = request.getParameter("isOpen");
		String timeletterid = request.getParameter("timeletterId");
		System.out.println(">>>>>>>>>>>>>>");
		System.out.println(isOpen);
		System.out.println(timeletterid);*/
		ITimeLetterService itls = new TimeLetterServiceImpl();
		request.setAttribute("timeletterlist", itls.list());
		request.getRequestDispatcher("/admin/documentQuery.jsp").forward(request, response);
	}
}
