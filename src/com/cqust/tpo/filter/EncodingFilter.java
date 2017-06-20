package com.cqust.tpo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 字符编码过滤
 */
@WebFilter(filterName="/EncodingFilter",urlPatterns={"/*"},
	initParams = {
			@WebInitParam(name="endcoding",value="UTF-8")
	})
public class EncodingFilter implements Filter {
	protected String encoding = null;
	protected FilterConfig config;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		// 设置编码
		this.encoding = filterConfig.getInitParameter("endcoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			// 得到指定的编码
			String encode = getEncoding();
			if (encode != null) {
				// 设置request的编码
				request.setCharacterEncoding(encode);
				//response.setCharacterEncoding(encode);
				response.setContentType("text/html,"+encode);
			}
		}
		chain.doFilter(request, response);
	}

	protected String getEncoding() {
		return encoding;
	}

	public void destroy() {
		this.encoding  = null;
		this.config= null;

	}
}
