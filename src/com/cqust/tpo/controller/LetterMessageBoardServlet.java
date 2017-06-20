package com.cqust.tpo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.ILetterMessageBoardService;
import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.impl.LetterMessageBoardServiceImpl;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;

/**
 * 时光信详情页：留言、链接其他详情页、查询
 * @author DongJiJun
 *
 */
@WebServlet(name="/LetterMessageBoardServlet",
urlPatterns={"/lettermessageboard/*"})
	
public class LetterMessageBoardServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素
		if("details".equals(handle)){
			details(request,response);
		}else if("reply".equals(handle)){
			reply(request,response);
		}
	}
	
	private void reply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/client/details.jsp").forward(request, response);
	}

	/**
	 * 信箱开启页面点击时光信标题，进入时光信详情页，传入当前登陆用户对象USer,时光信id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ILetterMessageBoardService ilmbs = new LetterMessageBoardServiceImpl();
		ITimeLetterService itls = new TimeLetterServiceImpl();
		int getTimeLetterId = Integer.parseInt(request.getParameter("timeletterid"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		
		//根据时光信Id将关注量加一
		itls.UpdateAttention(getTimeLetterId);
//		String id = user.getUserName();
//		System.out.println(id);
		if(getTimeLetterId >= 0){
			//根据时光信Id显示时光信详情
			request.setAttribute("dtimeletter",itls.findByTimeLetterIdList(getTimeLetterId));
			//根据时光信Id显示留言板
			request.setAttribute("lettermessageboard", ilmbs.list(getTimeLetterId));
			//随机生成“猜你喜欢”
			request.setAttribute("timeletter",itls.ClickTopRanklist());
			//根据时光信Id,生成留言板
			//request.setAttribute("", arg1);
			request.getRequestDispatcher("/client/details.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/client/openemail.jsp").forward(request, response);
		}
	}
	
}
