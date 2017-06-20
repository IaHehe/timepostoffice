package com.cqust.tpo.controller;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
/**
 * 系统初始化，设置站点名和设置基地址
 */
@WebServlet(name="/InitServlet",value={"/init"},loadOnStartup=1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		context.setAttribute("clientBase", context.getContextPath());
		context.setAttribute("adminBase", context.getContextPath());
		context.setAttribute("site", "时光邮局");
	}
}
