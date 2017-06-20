package com.cqust.tpo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.cqust.tpo.service.IPostGuideService;
import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.service.impl.LetterPaperOrderServiceImpl;
import com.cqust.tpo.service.impl.PostGuideServiceImpl;
import com.cqust.tpo.service.impl.TimeLetterServiceImpl;


@WebServlet(name="/LetterPaperOrderServlet",
urlPatterns={"/letterpaperorder/*"})
public class LetterPaperOrderServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		1.编码集修改
//		2.获得path，最后那个小节
//		3.解析这个小节进入相对应的方法体，完成请求响应动作----service()
		
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html,UTF-8");
		//获取请求的地址，并使用'/'进行分割，获取到处理的方式
		String [] url = request.getRequestURI().split("/");
		String handle = url[url.length - 1]; //获取url中最后一个下标的元素 
		if ("papermail".equals(handle)) {
			try {
				list(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		订单界面
		if ("order".equals(handle)) {
			try {
				order(request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		订单填写完成后提交动作处理
		if ("submitorder".equals(handle)) {
			
			try {
				submitorder(request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//进入我的信箱
		if ("browseOwnLetter".equals(handle)) {
			
			try {
				browseOwnLetter(request, response);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if("submitdelete".equals(handle)){
			try{
				submitdelete(request, response);
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if ("pay".equals(handle)) {
			try {
				pay(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("false");
			}
		}
	}
	
	//用户支付完成后，通过点击超链接，后台获取到uuid的值，通过查询，进行对订单状态的修改
	private void pay(HttpServletRequest request, HttpServletResponse response) {
		
		try {		
			String uuid = request.getParameter("uuid");
			ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
			if(letterpaperorderService!=null){
				LetterPaperOrder lpo = new LetterPaperOrder();
				lpo.setLetterPaperNumber(uuid);
			 	letterpaperorderService.update(lpo);
				response.getWriter().print("pay success");
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//在我的时光信&纸信当中，显示出了用户的所有的纸信，后面有一个删除按钮，在后台获取到每一条纸信的id，通过id进行sql删除语句，进行删除
	private void submitdelete(HttpServletRequest request, HttpServletResponse response) {		
		int id=0;
		try{
			id = Integer.parseInt(request.getParameter("id"));	
		}catch(NumberFormatException e){
			e.printStackTrace();
			deletePart(response, "delete error");
		}
		
		ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
		letterpaperorderService.delete(id);
		
	}

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
	//在用户进入订单填写页面后，在输入框内输入相应的内容，将前台的填写值获取到，然后封装最后存入数据库
	private void submitorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		if(user==null){
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		}
		
		String letterpapername = request.getParameter("letterPaperName");
		String letterpaperaddress = request.getParameter("letterPaperAddress");
		String letterpaperphone = request.getParameter("letterPaperPhone");
		String letterpaperpostnumber = request.getParameter("letterPaperPostNumber");
		String dateofmailing = request.getParameter("dateOfMailing");
		String customername = request.getParameter("customerName");
		String customerphone = request.getParameter("customerPhone");
		String customerqq = request.getParameter("customerQQ");
		
		String guid = UUID.randomUUID().toString();
		
		LetterPaperOrder lpo = new LetterPaperOrder();	
		
		lpo.setUserid(user.getUserId());
		
					
		lpo.setLetterPaperName(letterpapername);
		lpo.setLetterPaperAddress(letterpaperaddress);	
		lpo.setLetterPaperPhone(letterpaperphone);
		lpo.setLetterPaperPostNumber(letterpaperpostnumber);

		
		lpo.setLetterPaperNumber(guid);
		
		try {
			
			System.out.println("dateofmailing:" + dateofmailing);
			lpo.setDateOfMailing(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateofmailing));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lpo.setDateOfMailing(null);
		} //获取所选寄出时间
		lpo.setCustomerName(customername);
		lpo.setCustomerPhone(customerphone);
		lpo.setCustomerQQ(customerqq);
		lpo.setOrderState("0");
		lpo.setLetterPaperState("0");
		lpo.setIsLetterPaperUpdate("Y");
		lpo.setLetterPaperCreateTime(new Date());//获取系统当前时间
		
		ILetterPaperOrderService letterpaperorderService = new LetterPaperOrderServiceImpl();
		
		if(letterpaperorderService!=null)
		{
			letterpaperorderService.insert(lpo);//开始插入
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);//插入成功后跳转到index界面
		}
		else{
			System.out.println("error!");
		}		
	}
	
	//用户在index页面中，点击纸信，将自动跳转自用户帮助当中，后台获取到数据库当中的值然后输出到前台页面当中
	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		IPostGuideService postguideService = new PostGuideServiceImpl();
		if(postguideService.list()!=null)
		{
			request.setAttribute("postguidelist", postguideService.list());
			System.out.println("z-----:"+postguideService.list().size());
			request.getRequestDispatcher("/client/papermail.jsp").forward(request, response);
		}else{
			System.out.println("error");
		}
	}
	
	
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 浏览个人时光信.
	 * 根据用户id来查。
	 * </p>
	 * @param userId  用户id
	 */
	private void browseOwnLetter(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");
		
		if(user==null){
			try {
				request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		int userId = user.getUserId();
	
//		查找此用户的时光信和纸信信息
		ITimeLetterService timeletterservice = new TimeLetterServiceImpl();
		Map<String,List> map = timeletterservice.browseOwnLetter(userId);
		
//		遍历这个Map，而后打印出来
		if(map == null){
			System.out.println("map is null");
		}else{
//			调用方法，打印
			handle(map);
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
		if(map == null){
			System.out.println("map is null");
			return ;
		}
//		 * key:  TimeLetterList   value:List形式的集合，包含该用户所有时光信集合。
//		 * key:  LetterPaperOrderList  value:List形式的集合，包含该用户所有纸质信集合。
		
		List list = map.get("TimeLetterList");
		if(list == null){
//			System.out.println(" TimeLetterList  is null");
		}else{
			TimeLetter timeLetter = null;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				timeLetter = (TimeLetter) iterator.next();
				System.out.println(timeLetter);
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
	//用户点击按钮跳转到填写订单页面
	private void order(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/client/order.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}

