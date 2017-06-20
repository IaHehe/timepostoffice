package com.cqust.tpo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.models.Assistant;
import com.cqust.tpo.service.IAssistantService;
import com.cqust.tpo.service.impl.AssistantServiceImpl;

/**
 * 帮助信息模块
 * @author 邹东军
 * @date 2016/9/28
 */
@WebServlet(name="/AssistantServlet",urlPatterns={"/assistant/*"})
public class AssistantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		// request.getRequestURI:返回 请求行中URL的查询串前面的部分
		String URI = request.getRequestURI();
		String[] uris = URI.split("/");
		// 将 请求行中URL的查询串前面的部分 以 '/'分割来得到处理请求的方法名
		String handle = uris[uris.length - 1];
		if("seeAssistant".equals(handle)) {
			seeAssistant(request, response);
		} else if("browseAllAssistant".equals(handle)) {
			browseAllAssistant(request, response);
		} else if("saveAssistant".equals(handle)) {
			saveAssistant(request,response);
		} else if("modifyAssistant".equals(handle)) {
			modifyAssistant(request,response);
		} else if("deleteAssistant".equals(handle)) {
			deleteAssistant(request,response);
		}
	}

	/**
	 * 管理员删除帮助文档信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteAssistant(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		IAssistantService asService = new AssistantServiceImpl();
		if (asService.delete(aid)) { //调用 Service 层方法，删除成功返回 1
			response.getWriter().write("1");
		} else { // 删除失败返回 0
			response.getWriter().write("0");
		}
	}

	/**
	 * 管理员修改帮助文档信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void modifyAssistant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAssistantService asService = new AssistantServiceImpl();
		if(request.getParameter("assistantTitle") == null) {
			int assId = Integer.parseInt(request.getParameter("assistantId"));
			request.setAttribute("assistant", asService.get(assId));
			request.setAttribute("dealMsg", "modify");
			request.getRequestDispatcher("/admin/helpAdd.jsp").forward(request, response);
		} else {
			Assistant ast = new Assistant();
			ast.setAssistantId(Integer.parseInt(request.getParameter("assistantId")));
			ast.setAssistantTitle(request.getParameter("assistantTitle"));
			ast.setAssistantContent(request.getParameter("assistantContent")); 
			
			if(asService.update(ast)) {
				request.getRequestDispatcher("/assistant/browseAllAssistant").forward(request, response);
			}
		}
	}

	/**
	 * 管理员添加帮助文档信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void saveAssistant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("assistantTitle") == null) {
			request.setAttribute("dealMsg", "save");
			request.getRequestDispatcher("/admin/helpAdd.jsp").forward(request, response);
		} else {
			Assistant ast = new Assistant();
			//ast.setAssistantId(Integer.parseInt(request.getParameter("assistantTitle")));
			ast.setAssistantTitle(request.getParameter("assistantTitle"));
			ast.setAssistantContent(request.getParameter("assistantContent")); 
			ast.setAssistantTime(new Date());
			
			IAssistantService asService = new AssistantServiceImpl();
			
			asService.insert(ast);
			request.getRequestDispatcher("/assistant/browseAllAssistant").forward(request, response);
		}
	}

	/**
	 * 后台管理帮助文档---管理员查看所有文档信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void browseAllAssistant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAssistantService asService = new AssistantServiceImpl();
		
		List<Assistant> assistants = asService.list();
		
		if(assistants == null || assistants.size() == 0) {
			request.setAttribute("msg", "没有帮助文档信息!");
		}else {
			request.setAttribute("assistants", assistants);
		}
		
		request.getRequestDispatcher("/admin/helpQuery.jsp").forward(request, response);
	}

	/**
	 * 用户浏览帮助信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void seeAssistant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//声明IAssistantService 接口并实例化
		IAssistantService asService = new AssistantServiceImpl();
		//调用IAssistantService接口的list()方法获取所有的帮助信息的集合
		List<Assistant> assistants = asService.list();
		request.setAttribute("assistants", assistants);//将获取到的帮助信息集合保存到Request请求中
		//转发页面
		request.getRequestDispatcher("/client/help.jsp").forward(request, response);
	}

}
