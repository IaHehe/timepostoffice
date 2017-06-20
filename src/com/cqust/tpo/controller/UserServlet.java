package com.cqust.tpo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cqust.tpo.models.User;
import com.cqust.tpo.service.IUserService;
import com.cqust.tpo.service.impl.UserServiceImpl;
import com.cqust.tpo.utils.SendMailUtil;

/**
 * 用户登录、注册、退出、维护个人信息
 * 
 * @author 邹东军
 * @date 2016/9/27
 */
@WebServlet(name = "/UserServlet", urlPatterns = { "/user/*" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");*/
		// request.getRequestURI:返回 请求行中URL的查询串前面的部分
		String URI = request.getRequestURI();
		String[] uris = URI.split("/");
		// 将 请求行中URL的查询串前面的部分 以 '/'分割来得到处理请求的方法名
		String handle = uris[uris.length - 1];
		if ("login".equals(handle)) {
			login(request, response);
		} else if ("register".equals(handle)) {
			register(request, response);
		} else if ("exit".equals(handle)) {
			exit(request, response);
		} else if ("vlidate".equals(handle)) {
			vlidate(request, response);
		} else if ("setting".equals(handle)) {
			setting(request, response);
		} else if ("upload".equals(handle)) {
			upload(request, response);
		}
	}

	/**
	 * 用户修改个人信息，并实现文件上传
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("static-access")
	private void upload(HttpServletRequest request, HttpServletResponse response) {
		/********* 文件上传组件： 处理文件上传 ************/
		String path = "";
		String userName = "";
		String newPassword = "";
		@SuppressWarnings("unused")
		String oldPassword = "";
		String fileName = "";

		try {
			// 1. 文件上传工厂
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 创建文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置上传表单文件名的编码
			// 相当于：request.setCharacterEncoding("UTF-8");
			upload.setHeaderEncoding("UTF-8");

			// 3. 判断： 当前表单是否为文件上传表单
			if (upload.isMultipartContent(request)) {
				// 4. 把请求数据转换为一个个FileItem对象，再用集合封装
				@SuppressWarnings("unchecked")
				List<FileItem> list = upload.parseRequest(request);
				// 遍历： 得到每一个上传的数据
				for (FileItem item : list) {
					// 判断：普通文本数据
					if (item.isFormField()) {
						// 普通文本数据
						String fieldName = item.getFieldName(); // 表单元素名称
						if (fieldName.equals("userName")) {
							// item.getString("UTF-8"); 指定编码
							userName = item.getString("UTF-8");// 表单元素名称， 对应的数据
						}else if(fieldName.equals("newPassword")) {
							newPassword = item.getString();
						}else if(fieldName.equals("oldPassword")) {
							oldPassword = item.getString();
						}
					}
					// 上传文件(文件流) ----> 上传到upload目录下
					else {
						// 普通文本数据
						// String fieldName = item.getFieldName(); // 表单元素名称
						String name = item.getName(); // 文件名
						//文件名重名 对于不同用户readme.txt文件，不希望覆盖！ 后台处理： 给用户添加一个唯一标记!
						// a. 随机生成一个唯一标记
						String id = UUID.randomUUID().toString();
						// b. 与文件名拼接
						name = id + name;
						
						// 获取上传基路径
						path = getServletContext().getRealPath("/upload");
						
						fileName = "upload/"+name;
						
						// 创建目标文件
						 File file = new File(path,name);
						//
						// // 工具类，文件上传
						 item.write(file);
						 item.delete(); //删除系统产生的临时文件
					}
				}
				//-----------------------
				User user = (User) request.getSession().getAttribute("currentUser");
				if(!("".equals(userName))) {
					user.setUserName(userName);
				}
				if(!("".equals(newPassword))) {
					user.setUserPassword(newPassword);
				}
				if(!("".equals(fileName))) {
					user.setUserImg(fileName);
				}
				IUserService us = new UserServiceImpl();
				if(us.update(user)) {
					//request.getRequestDispatcher("/client/index.jsp").forward(request, response);
					response.sendRedirect("../");
				}
			} else {
				System.out.println("当前表单不是文件上传表单，处理失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录后点击‘设置’跳转到setting.jsp
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser != null) {
			request.setAttribute("user", currentUser);
			request.getRequestDispatcher("/client/setting.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/user/login").forward(request, response);
		}

	}

	/**
	 * 当用户进入邮箱验证账户后，点击链接回到本站，将session中的vlidateUser取出插入数据库，并将页面转发到注册成功页！
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void vlidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		User vlidateUser = (User) session.getAttribute("vlidateUser");
		String sid = (String) session.getAttribute("sid"); // 获取session中的sid
		String vlidateSid = request.getParameter("sid"); // 获取从邮箱跳转回来的请求串中的sid

		IUserService userService = new UserServiceImpl();
		boolean isRegistered = false;

		session.invalidate();// 注销session（让session失效）
		//当 session 注销以后，sid为 null 
		if (sid != null && sid.equals(vlidateSid)) {//用请求串中的sid与session中获取的sid进行对比
			boolean isInsert = userService.insert(vlidateUser);
			isRegistered = isInsert;// == true ? true : false;
		} else {
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);
		}
		if (isRegistered) {
			request.getRequestDispatcher("/client/registerSuccess.jsp").forward(request, response);
		}else {
			System.out.println("你已经注册成功了，不要再点了!");
		}
	}

	/**
	 * 用户退出系统
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取HttpSession对象
		HttpSession session = request.getSession();
		// 移除存储在Session的用户对象
		// session.removeAttribute("currentUser");
		session.invalidate();
		response.sendRedirect("../");// 页面重定向到项目根目录
	}

	/**
	 * 用户注册<br>
	 * <li>用户在页面顶部点击‘注册’按钮，页面转发到注册页面register.jsp</li>
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IUserService userService = new UserServiceImpl();
		// 用户在页面顶部点击注册按钮，跳转到注册页
		// 根据请求中是否带有userEmail值来判断是跳转页面还是注册
		if (request.getParameter("userEmail") == null) {// 用户从header.jsp页面点击‘注册’按钮跳转到注册页面
			request.getRequestDispatcher("/client/register.jsp").forward(request, response);
		} else {// 如果不是从页面顶部点击注册按钮
			// 在注册页面的邮箱输入框中失去焦点时，异步查询输入的邮箱账号是否存在
			if ("check".equals(request.getParameter("mark"))) { // 如果是异步查询邮箱账号
				// 获取用户输入的邮箱账号
				String userEmail = request.getParameter("userEmail");
				User hasUser = userService.get(userEmail); // 调用Service获取User对象
				if (hasUser.getUserEmail() != null) { // 如果获取的用户的邮箱账号为
														// null，表示此账号可用
					response.getWriter().write("0");
				} else {// 账号已存在，需要用户重新输入
					response.getWriter().write("1");
				}
			} else {
				User newUser = new User();
				newUser.setUserEmail(request.getParameter("userEmail"));
				newUser.setUserPassword(request.getParameter("userPassword"));

				HttpSession session = request.getSession();
				// 获取sessionId
				String sessionId = session.getId();
				session.setAttribute("vlidateUser", newUser); // 将User对象放入session，在用户进入邮箱点击链接后取出插入数据库
				session.setAttribute("sid", sessionId);// 将客户端的sessionId放入session中
				// 发送邮件
				try {
					SendMailUtil.MailUtil(newUser.getUserEmail(), sessionId);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				request.getRequestDispatcher("/client/registerVlidate.jsp").forward(request, response);
			}
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 用户在页面顶部点击登录按钮，跳转到登录页
		if (request.getParameter("userEmail") == null) {// 在页面顶部点击登录按钮直接跳转到登录页面
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		} else { //用户在登录页点击登录按钮，提交form表单
			User user = new User();
			user.setUserEmail(request.getParameter("userEmail"));
			user.setUserPassword(request.getParameter("userPassword"));

			IUserService userService = new UserServiceImpl();

			if (userService.checkUser(user)) {
				User currentUser = userService.get(user.getUserEmail());
				userService.update(currentUser, new Date());
				request.getSession().setAttribute("currentUser", currentUser);
				response.getWriter().write("1");
			} else {
				response.getWriter().write("0");
			}
		}
	}

}
