package com.cqust.tpo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.models.Manager;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.IManagerService;
import com.cqust.tpo.service.IUserService;
import com.cqust.tpo.service.impl.ManagerServiceImpl;
import com.cqust.tpo.service.impl.UserServiceImpl;

/**
 * 帮助信息模块
 * @author 邹东军
 * @date 2016/9/28
 */
@WebServlet(name="/ManagerServlet",urlPatterns={"/manager/*"})
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		// request.getRequestURI:返回 请求行中URL的查询串前面的部分
		String URI = request.getRequestURI();
		String[] uris = URI.split("/");
		// 将 请求行中URL的查询串前面的部分 以 '/'分割来得到处理请求的方法名
		String handle = uris[uris.length - 1];
		if ("login".equals(handle)) {
			login(request, response);
		} else if("browseAllUsers".equals(handle)) {
			browseAllUsers(request,response);
		}else if("searchUsers".equals(handle)) {
			searchUsers(request,response);
		}else if("forbid".equals(handle)) { //是否禁止用户写信
			forbid(request,response);
		}
	}

	/**
	 * 是否禁止用户写信
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void forbid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		User user = new User();
		user.setUserId(userId);
		
		IUserService userService = new UserServiceImpl();
		boolean flag = userService.update(user, request.getParameter("isForbid"));
		if(flag) {
			response.getWriter().write("1");
		}
	}

	private void searchUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTxt = request.getParameter("searchTxt");
		
		//新建 UserServiceImpl对象并实例化
		IUserService userService = new UserServiceImpl();
		
		List<User> users = userService.list(searchTxt);
		if(users.size() == 0) {
			request.setAttribute("msg", "没有查询到数据!");
		} else {
			request.setAttribute("users", users);
		}
		
		request.getRequestDispatcher("/admin/userQuery.jsp").forward(request, response);
	}

	/**
	 * 管理员浏览所有的用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void browseAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//新建 UserServiceImpl对象并实例化
		IUserService userService = new UserServiceImpl();
		
		List<User> users = userService.list();
		if(users.size() > 0) {
			request.setAttribute("users", users);
		}else {
			request.setAttribute("msg", "没有数据!");
		}
		
		request.getRequestDispatcher("/admin/userQuery.jsp").forward(request, response);
	}

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("manangerAccount") == null) {
			request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
		} else {
			Manager loginManager = new Manager();
			loginManager.setManagerAccount(request.getParameter("manangerAccount"));
			loginManager.setManagerPassword(request.getParameter("managerPassword"));
			
			IManagerService ms = new ManagerServiceImpl();
			boolean flag = ms.checkManager(loginManager) == true ? true : false;
			
			if(flag) {
				request.getSession().setAttribute("currentManager", ms.get(loginManager.getManagerAccount()));
				request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			}else {//用户不存在的处理
				System.out.println("manager login:账号户或密码错误");
			}
		}
	}

}
