package com.cqust.tpo.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤所有的 jsp 页面
 */
@WebFilter(filterName="/JspFilter",urlPatterns={"/*"})
public class JspFilter implements Filter {

    /**
     * Default constructor. 
     */
    public JspFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 过滤所有的jsp页面
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		String URL = req.getRequestURI();
		if(URL != null && URL.endsWith(".jsp")) {
			String [] urls = URL.split("/");
			if("admin".equals(urls[urls.length - 2]) || "manager".equals(urls[urls.length - 2])) {
				res.sendRedirect(req.getContextPath() + "/manager/login");
				return ;
			}else {
				res.sendRedirect(req.getContextPath());
				return;
			}
		} else {
			chain.doFilter(request, response); 
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
