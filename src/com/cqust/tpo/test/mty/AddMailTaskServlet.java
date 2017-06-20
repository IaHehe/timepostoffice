/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: AddMailTaskServlet.java <br>
 * 包名: com.cqust.tpo.test.mty <br>
 * 创建时间: 2016年9月27日 下午5:31:22 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.test.mty;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Scheduler;

import com.cqust.tpo.dao.impl.MailTaskDaoImpl;
import com.cqust.tpo.models.MailTask;
import com.cqust.tpo.utils.mail.MailFile;
import com.cqust.tpo.utils.mail.MailUtil;
import com.cqust.tpo.utils.quartz.TimeScheduling;

/** 
 * 类名: AddMailTaskServlet <br>
 * 描述: 动态添加任务到调度表 <br>
 * 创建时间: 2016年9月27日 下午5:31:22 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
@WebServlet(value="/handle/*")
public class AddMailTaskServlet extends HttpServlet {

	/** (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param req 请求
	 * @param resp 响应
	 * @throws ServletException Servlet异常
	 * @throws IOException io异常
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo().substring(1);
		
		System.out.println("本次请求输出     S ------------------");
//		System.out.println("path:" + path);
		if (path.equalsIgnoreCase("add")) {
			add();
		} else if (path.equalsIgnoreCase("delete")) {
			delete(Integer.parseInt(req.getParameter("id")));
		} else if (path.equalsIgnoreCase("update")) {
			update(Integer.parseInt(req.getParameter("id")));
		} else if (path.equalsIgnoreCase("list")) {
			list();
		}else if(path.equalsIgnoreCase("addjobmail")){
			addjobmail();
		}else {
			System.out.println("path   路径未处理：" + path);
		}
		System.out.println("本次请求输出     E *******************");
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 添加邮件用于测试是否成功结合。
	 * </p>
	 */
	private void addjobmail() {
		Random rd = new Random();
		rd.nextInt(50);
		
//		获取下一分钟的起始点
		Date date = getnextMinute();
//		随机安排任务 1分钟内的
		MailTaskDaoImpl mailTaskDaoImpl = new MailTaskDaoImpl();
		MailTask mailTask = null;
		for(int i = 1 ;i < 7;i++){
			mailTask = new MailTask();
			int seconds = rd.nextInt(50);
//			System.out.println("---->seconds: " + seconds);
			date.setSeconds(seconds);
			mailTask.setSendTime(date);
			mailTask.setTimeLetterId(i);
			try {
				mailTaskDaoImpl.doInsert(mailTask);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		响应成功添加任务
//		MailFile mailFile = new MailFile();
//		mailFile.setFile(new File("src/com/cqust/tpo/utils/mail/90.jpg"));
//		mailFile.setFileId("imss");
//		boolean flag = MailUtil.createTimeLetter("me","1842468951@qq.com",mailFile, "nnn","<div>akdjfkasfksk</div>   <img src=\'cid:imss\' />");
//		boolean flag = MailUtil.createTimeLetter("me","matengyu@sohu.com",mailFile, "nnn","<div>akdjfkasfksk</div>   <img src=\'cid:imss\' alt=\'ioio\' />");
//		boolean flag = false;
//		flag = MailUtil.createTimeLetter("me","1842468951@qq.com",null, "nnn333","kskfj");
//		System.out.println(flag);
		
	}
	
	private Date getnextMinute(){
		GregorianCalendar gdate = new GregorianCalendar();
		Date date = gdate.getTime();
		Date newdate = new Date(date.getYear(), date.getMonth(), 
				date.getDay(), date.getHours(), date.getMinutes()+1, 0);
		return newdate;
//		System.out.println(newdate);
//		System.out.println(gdate.toString());
//		GregorianCalendar beginDate = new GregorianCalendar(date., date.getMonth(), 
//				date.getDay(), date.getHours(), date.getMinutes(), date.getMinutes());
//		GregorianCalendar beginDate = new GregorianCalendar(date.getYear(), date.getMonth(), 
//				date.getDay(), date.getHours(), date.getMinutes(), date.getMinutes());
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 删掉id对应的任务
	 * </p>
	 * @param id 邮件任务id
	 */
	private void delete(Integer id) {
		MailTaskDaoImpl mdao = new MailTaskDaoImpl();
		try {
			mdao.doDelete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 更新邮件任务的ID
	 * </p>
	 * @param id 邮件任务id
	 */
	private void update(Integer id) {
		GregorianCalendar gc = new GregorianCalendar(2022, 10, 5, 3, 6, 5);
		MailTaskDaoImpl mdao = new MailTaskDaoImpl();
		try {
			mdao.doUpdate(new MailTask(id, gc.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 
	 * </p>
	 */
	private void list() {
		MailTaskDaoImpl mdao = new MailTaskDaoImpl();
		try {
			List<MailTask> list = mdao.findAll();
			if(list == null){
				System.out.println("list is null");
				return;
			}
			MailTask mailTask = null;
			for (Iterator<MailTask> iterator = list.iterator(); iterator.hasNext();) {
				mailTask = iterator.next();
				System.out.println(mailTask);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(){
		MailTask mailTask = new MailTask();
		Scheduler scheduler = TimeScheduling.getScheduler();
		MailTaskDaoImpl mdao = new MailTaskDaoImpl();
		
		int count = 2;
		for(int i = 0; i < count; i++,begin++){
			mailTask.setTimeLetterId(begin);
			mailTask.setSendTime(new GregorianCalendar(2016,8,27,22,18,9+i*6).getTime());
			try {
				mdao.doInsert(mailTask);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/** 
	 * begin : 编号起始值，模拟数据库主键。测试用
	 */
	private static int begin = 60;
}
