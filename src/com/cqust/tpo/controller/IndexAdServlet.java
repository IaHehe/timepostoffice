package com.cqust.tpo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cqust.tpo.models.IndexAd;
import com.cqust.tpo.service.IIndexAdService;
import com.cqust.tpo.service.impl.IndexAdServiceImpl;

/**
 * 首页广告浏览、修改
 * @author 杨秀仕
 * @date 2016/09/27
 */
@WebServlet(name="/IndexAdServlet",urlPatterns={"/indexAd/*"})

public class IndexAdServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素
		
		if("browseIndexAd".equals(handle)) {
			browseIndexAd(request,response);
		}else if("modifyIndexAd".equals(handle)) {
			modifyIndexAd(request,response);
		}
	}

	/**
	 * 管理员修改首页广告
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */	
	private void modifyIndexAd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//查询数据
		IIndexAdService iaService = new IndexAdServiceImpl();
		
		List<IndexAd> indexad = iaService.list();
		request.setAttribute("iAds", indexad);
		request.getRequestDispatcher("/admin/adsAdd.jsp").forward(request, response);
				
	}

	/**
	 * 管理员浏览所有的广告信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void browseIndexAd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		//查询数据
		IIndexAdService iaService = new IndexAdServiceImpl();
		
		List<IndexAd> indexad = iaService.list();
		request.setAttribute("iAds", indexad);
		request.getRequestDispatcher("/admin/adsQuery.jsp").forward(request, response);
	}
}