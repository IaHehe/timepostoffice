package com.cqust.tpo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.impl.LetterPaperOrderServiceImpl;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;
import com.cqust.tpo.utils.DateUtil;
/**
 * 浏览个人时光信、写时光信、浏览所有时光信、浏览个人时光信详情、删除个人时光信、点击量、点赞量
 * @author 董继军
 * @date 2016/9/27
 */
@WebServlet(name="/TimeLetterServlet",
	urlPatterns={"/timeLetter/*"})
public class TimeLetterServlet extends HttpServlet{

	private static final long serialVersionUID = -1283888342353257603L;

	/** (non-Javadoc)
	 * service方法，导航性质的工作原理.<br>
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request Http请求对象
	 * @param response Http响应对象
	 * @throws ServletException Servlet异常
	 * @throws IOException IO异常
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		// 获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String[] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; // 获取url中最后一个下标的元素
		if ("openemail".equals(handle)) {
			// System.out.println("jin lai le");
			try {
				list(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("email".equals(handle)) {
			try {
				email(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("browseOwnLetter".equals(handle)) {
			// 查看自己的时光信
			HttpSession session = request.getSession();
			if (session == null) {
				return;
			}
			User user = (User) session.getAttribute("currentUser");
			if (user == null) {
				response.sendRedirect("../");
				return;
			}
			request.setAttribute("userid", user.getUserId());
			browseOwnLetter(request, response);
		} else if ("delete".equals(handle)) {
			// 删除时光信，用户在个人信箱页面点击删除使用该方法
			// System.out.println("id:" + request.getParameter("id"));
			HttpSession session = request.getSession();
			if (session == null) {
				return;
			}
			User user = (User) session.getAttribute("currentUser");
			if (user == null) {
				response.sendRedirect("../");
				return;
			}
			delete(request, response, user);
		} else if ("submitdelete".equals(handle)) {
			int id = 0;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				deletePart(response, "delete error");
			}

			ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
			letterpaperorderService.delete(id);

		}
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 删除时光信，用户在个人信箱页面点击删除使用该方法
	 * </p>
	 * @param request 请求对象
	 * @param response 响应对象
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response, User user) {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
//			获取ID失败
			deletePart(response, "error");
		}
		
//		验证是否再删除自己的时光信
		
//		如果是，则删除这封时光信
		ITimeLetterService timeService = new TimeLetterServiceImpl();
		timeService.delete(id);
		deletePart(response, "success");
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 删除动作小片段程序.
	 * 向客户端输出响应信息
	 * </p>
	 * @param msg 传递到客户端的消息
	 */
	private void deletePart(HttpServletResponse response, String msg){
		if("".equals(msg))
			return;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
		return ;
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 浏览个人时光信和纸信.
	 * 根据用户id来查。
	 * </p>
	 * @param userId  用户id
	 */
	private void browseOwnLetter(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.parseInt(request.getAttribute("userid").toString());
//		System.out.println("查找用户自己的时光信。。");
//		System.out.println("用户id为：" + userId);
//		查找此用户的时光信和纸信信息
		ITimeLetterService timeletterservice = new TimeLetterServiceImpl();
		Map<String,List> map = timeletterservice.browseOwnLetter(userId);
		
//		遍历这个Map，而后打印出来
		if(map == null){
			System.out.println("map is null");
		}else{
//			调用方法，打印
//			handle(map);
		}
		
		
//		转向页面，在页面上呈现
		try {
			request.setAttribute("timeletterlist", map.get("TimeLetterList"));
			request.setAttribute("letterPaperOrderList", map.get("LetterPaperOrderList"));
			request.getRequestDispatcher("/client/myemail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("未实现-----转向页面");
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * browseOwnLetter,查看数据是否得到
	 * </p>
	 * @param map Map对象
	 */
	private void handle(Map<String, List> map) {
		// TODO Auto-generated method stub
//		System.out.println("9999999999999   xiewuxie  ");
		if(map == null){
			System.out.println("map is null");
			return ;
		}
//		 * key:  TimeLetterList   value:List形式的集合，包含该用户所有时光信集合。
//		 * key:  LetterPaperOrderList  value:List形式的集合，包含该用户所有纸质信集合。
		
		List list = map.get("TimeLetterList");
		if(list == null){
			System.out.println(" TimeLetterList  is null");
		}else{
			TimeLetter timeLetter = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				timeLetter = (TimeLetter) iterator.next();
//				System.out.println(timeLetter);
			}
		}
		
		list = map.get("LetterPaperOrderList");
		if(list == null){
			System.out.println(" LetterPaperOrderList  is null");
		}else {
			LetterPaperOrder letterPaperOrder = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				letterPaperOrder = (LetterPaperOrder) iterator.next();
//				System.out.println(letterPaperOrder);
			}
		}
	}
	
	private void email(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("currentUser");
		if(user == null){
			request.getRequestDispatcher("/user/login").forward(request, response);
			return;
		}
		if(user.getUserId() == 0){
			request.getRequestDispatcher("/client/email.jsp").forward(request, response);
		}
		else{
			ITimeLetterService itls = new TimeLetterServiceImpl();
			TimeLetter timeletter = new TimeLetter();
			Date date = DateUtil.convertToDate(request.getParameter("sendtime"));
			timeletter.setTimeLetterTitle(request.getParameter("title"));
			timeletter.setLetterSendTime(date);
			timeletter.setTimeLetterImg(request.getParameter("imgs"));
			timeletter.setTimeLetterContent(request.getParameter("content"));
			timeletter.setLetterRecipent(request.getParameter("recipientemail"));
			timeletter.setIsOpen(request.getParameter("timelisopen"));
			timeletter.setUser(user);
			itls.insert(timeletter);
			request.getRequestDispatcher("/client/email.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * 点击开启信箱，进行时光信浏览
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ITimeLetterService timeletterService = new TimeLetterServiceImpl();
		//判断时光信List对象是否为空
		if(timeletterService.list() != null){
			//将数据集合放入request对象中命名为timeletter
			request.setAttribute("timeletter", timeletterService.list());
			request.setAttribute("attentiontoprank", timeletterService.AttentionTopRanklist());
			request.setAttribute("clicktoprank", timeletterService.ClickTopRanklist());
			
			//导航到listFlower.jsp页面（请求转向）
			request.getRequestDispatcher("/client/openemail.jsp").forward(request, response);
		}else{
			System.out.println("123124123512354231");
			//关于方法调用出错的处理货提示
		}
	}
}
