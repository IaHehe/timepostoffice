/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: SchedulerListener.java <br/>
 * 包名: com.cqust.tpo.utils.quartz <br/>
 * 创建时间: 2016年9月26日 上午10:32:46 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.quartz;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.ee.servlet.QuartzInitializerListener;

import com.cqust.tpo.utils.StringUtil;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

/**
 * 类名: SchedulerListener <br>
 * 描述: ServletContextListener接口的实现类 <br>
 * 之所以不直接使用Quartz的监听器类，是为了在Quartz启动后获得一个Scheduler对象，完成TimeScheduler类的初始化。
 * 这个Scheduler就是本应用中唯一的一个时间调度表。
 * 创建时间: 2016年9月26日 上午10:32:46
 * 
 * @author 马腾宇 Tengyu Ma mty2015@126.com
 * @version 1.0.0
 * @since 1.6
 */
public class SchedulerListener implements ServletContextListener {

	/** 
	 * ql : Quartz框架提供的监听器类，用来启动Quartz容器。
	 */
	private QuartzInitializerListener ql = new QuartzInitializerListener();

	/** (non-Javadoc)
	 * 销毁ServletContext,监听器销毁事件触发，执行此代码。
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * @param sce ServletContextEvent实例，上下文事件实例
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("销毁servletContext.S");
//		TimeScheduling.getScheduler().getContext().
		ql.contextDestroyed(sce);
		System.out.println("已执行销毁动作,信息输出");
		getSchedulersAndPrintInfo(sce);
		System.out.println("销毁servletContext.E");
		
//		关闭数据库连接线程，避免内存泄漏，造成不安全
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		Driver d = null;
		while (drivers.hasMoreElements()) {
			try {
				d = drivers.nextElement();
				DriverManager.deregisterDriver(d);
				System.out.println(String.format("ContextFinalizer:Driver %s deregistered", d));
			} catch (SQLException ex) {
				System.out.println(String.format("ContextFinalizer:Error deregistering driver %s", d) + ":" + ex);
			}
		}
		try {
//			连接清理线程关闭
			AbandonedConnectionCleanupThread.shutdown();
		} catch (InterruptedException e) {
			System.out.println("ContextFinalizer:SEVERE problem cleaning up: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/** (non-Javadoc)
	 * 创建ServletContext事件，触发事件,执行此代码。
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * @param sce  ServletContextEvent实例，上下文事件实例
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("创建ServletContext.S");
//		调用Quartz框架的监听器类，启动quartz框架
		ql.contextInitialized(sce);
		getSchedulersAndPrintInfo(sce);
		
//		初始化自定义的TimeScheduler类的调度表。
//		1.获得ServletContext上下文中保存的Scheduler保存的SchedulerFactory实例
//		2.获取一个Scheduler,整个项目运行就是用这个调度表
		SchedulerFactory schedulerFactory = (SchedulerFactory) sce.getServletContext().getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		if(schedulerFactory == null){
			System.out.println("从ServletContext中获取SchedulerFactory失败");
		}
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TimeScheduling.setScheduler(scheduler);
			System.out.println("TimeScheduling 初始化成功");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		System.out.println("创建ServletContext.E");
	}

	/**
	 * <b>方法作用 </b><br/><p>
	 * 获取所有调度表，而后打印出每个调度表的详细信息.
	 * 步骤：
	 * 1.获取应用上下文
	 * 2.得到调度工厂
	 * 3.获得所有调度表，遍历，输出详情信息。
	 * </p>
	 * @param sce 应用上下文事件
	 */
	private void getSchedulersAndPrintInfo(ServletContextEvent sce) {
		if(sce == null){
			System.out.println("ServletContextEvent  is  null");
			return;
		}
		ServletContext sc = sce.getServletContext();
		SchedulerFactory sf = (SchedulerFactory) sc.getAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY);
		try {
			Collection<Scheduler> collection = sf.getAllSchedulers();
			if(collection == null){
				System.out.println("所有调度表为null");
				return;
			}
			System.out.println("所有调度表个数:" + collection.size());
//			遍历所有调度表，输出调度表内的信息
			for (Iterator<Scheduler> iterator = collection.iterator(); iterator.hasNext();) {
				Scheduler scheduler = iterator.next();
//				输出单个调度表信息
				System.out.println(StringUtil.schedulerToString(scheduler));
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
