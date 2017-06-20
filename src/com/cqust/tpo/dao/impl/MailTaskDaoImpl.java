/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailTaskDaoImpl.java <br>
 * 包名: com.cqust.tpo.dao.impl <br>
 * 创建时间: 2016年9月27日 下午2:48:20 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.cqust.tpo.dao.IMailTaskDao;
import com.cqust.tpo.models.MailTask;
import com.cqust.tpo.utils.DateUtil;
import com.cqust.tpo.utils.quartz.QuartzConstant;
import com.cqust.tpo.utils.quartz.TimeScheduling;

/** 
 * 类名: MailTaskDaoImpl <br>
 * 描述: 邮件任务访问Dao实现类 <br>
 * 创建时间: 2016年9月27日 下午2:48:20 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailTaskDaoImpl implements IMailTaskDao{

	/** 
	 * scheduler : 任务调度表。 
	 */
	private static Scheduler scheduler = TimeScheduling.getScheduler();
	
	/** 
	 * jobDetail : jobDetail，邮件任务。
	 */
	private static JobDetail jobDetail = null;
	
	/** 
	 * flag : 标识。因为如果没有触发器与jobDetail关联，它无法进入调度表。
	 * 约定：为true时，jobDetail已经进入调度表；
	 * 为false时，jobDetail没有进入调度表。
	 */
	private static boolean flag = false;
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 将这个触发器添加到调度表，与jobDetail任务关联。
	 * </p>
	 * @param trigger 触发器
	 * @return 是否成功
	 */
	private static boolean addToScheduler(Trigger trigger){
		if(trigger == null)
			return false;
		try {
			flag = scheduler.checkExists(jobDetail.getKey());	
			if(flag){
				scheduler.scheduleJob(trigger);
			}
			else{
				scheduler.scheduleJob(jobDetail, trigger);
				flag = true;
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 创建邮件任务类
	 * </p>
	 */
	static {
		jobDetail = JobBuilder.newJob(MailDaoImpl.class)
		.withIdentity(QuartzConstant.JOB_DETAIL_NAME.getName(), QuartzConstant.JOB_DETAIL_GROUP_NAME.getName())
		.build();
		try {
			flag = scheduler.checkExists(jobDetail.getKey());
//			scheduler.addJob(jobDetail, false);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 验证邮件任务合法性.
	 * 验证内容包括id，时间。
	 * 如果时间已经过去了或者超过了范围，甚至null，返回false，其他情况返回true.
	 * </p>
	 * @param mailTask 邮件任务
	 * @return 是否合法，返回true表示合法;返回false表示不合法
	 */
	private boolean validate(MailTask mailTask){
		if(mailTask == null){
			System.out.println("mailTask is null");
			return false;
		}
//		验证时光信ID是否存在
		if(mailTask.getTimeLetterId() == null){
			System.out.println("mailTask id  is null");
			return false;
		}
//		验证日期合法性，规则：日期要在现在之后并且在2199年以前，（2199是Quartz框架的限制）
		Date date = mailTask.getSendTime();
		if(date == null){
			System.out.println("mailTask date is null");
			return false;
		}
		Date now = new Date();
//		System.out.println("ppppppp   "+ DateUtil.toDateString(date));
//		System.out.println("ppppppp   "+ DateUtil.toDateString(now));
//		System.out.println(now.after(date));
		System.out.println("now : " + now );
		System.out.println("sendTime : " + date);
		if(now.after(date)){
			System.out.println("now is before of " + date);
			return false;
		}
		if(date.getYear() > 2199){
			System.out.println("date.yeaer:" + date.getYear());
			return false;
		}
//		以上条件都都不满足才会是一个合法的时间日期
		System.out.println(" validate MailTask is true");
		return true;
	}
	
	/** (non-Javadoc)
	 * 添加一个邮件任务.
	 * @see com.cqust.tpo.dao.IDAO#doInsert(java.lang.Object)
	 * @param mailTask 邮件任务MailTask实例
	 * @return 插入成功与否，成功返回true；失败返回false
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public boolean doInsert(MailTask mailTask) throws Exception {
//		System.out.println(validate(mailTask));
		if(!validate(mailTask)){
			System.out.println("此邮件任务不合法  insert");
			return false;
		}
//		任务步骤：
//		1.创建触发器
//		2.整合到Scheduler任务调度表
		
//		1  创建触发器
		SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
				.forJob(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
				.withIdentity(QuartzConstant.TRIGGER_NAME_PREFIX.getName() + mailTask.getTimeLetterId().toString(), 
						QuartzConstant.TRIGGER_GROUP_NAME.getName())
				.startAt(mailTask.getSendTime())
				.usingJobData(QuartzConstant.JOB_DATA_TRIGGER_ID.getName(), mailTask.getTimeLetterId())
				.build();
//		2.添加任务调度到任务调度表
//		scheduler.scheduleJob(simpleTrigger);
		addToScheduler(simpleTrigger);
//		System.out.println(simpleTrigger.getKey());
		return true;
	}


	/** (non-Javadoc)
	 * 更新一个邮件任务.
	 * 删掉重建，参看doDelete(),doInsert()方法
	 * @see com.cqust.tpo.dao.IDAO#doUpdate(java.lang.Object)
	 * @see #doInsert(MailTask)
	 * @see #doDelete(Integer)
	 * @param mailTask 邮件任务MailTask实例。
	 * @return 更新成功与否，成功返回true，失败返回false
	 * @throws Exception  数据库或者Quartz容器抛出异常
	 */
	@Override
	public boolean doUpdate(MailTask mailTask) throws Exception {
		if(!validate(mailTask)){
			System.out.println("此邮件任务不合法----update");
			return false;
		}
//		任务步骤：
//		1  删掉触发器
		boolean flag = doDelete(mailTask.getTimeLetterId());
//		2.调用插入方法，更新
		return flag ? doInsert(mailTask) : flag;
	}

	/** (non-Javadoc)
	 * 删除一个邮件任务.
	 * @see com.cqust.tpo.dao.IDAO#doDelete(java.lang.Object)
	 * @param id 根据邮件任务ID（也就是数据库时光信ID），删除一个邮件任务
	 * @return 删除触发器成功与否。
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public boolean doDelete(Integer id) throws Exception {
		return scheduler.unscheduleJob(new TriggerKey(QuartzConstant.TRIGGER_NAME_PREFIX.getName() + id.toString(), 
				QuartzConstant.TRIGGER_GROUP_NAME.getName() ));
	}

	/** (non-Javadoc)
	 * 根据邮件任务ID找邮件任务
	 * @see com.cqust.tpo.dao.IDAO#findById(java.lang.Object)
	 * @param id 邮件任务ID，也就是数据库时光信ID
	 * @return 返回这个任务实例，不存在返回null
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public MailTask findById(Integer id) throws Exception {
		Trigger trigger = scheduler.getTrigger(new TriggerKey(QuartzConstant.TRIGGER_NAME_PREFIX.getName() + id.toString(), 
				QuartzConstant.TRIGGER_GROUP_NAME.getName() ));
		if(trigger == null){
			return null;
		}
		return new MailTask(id, trigger.getStartTime());
	}

	/** (non-Javadoc)
	 * 获取所有邮件任务.
	 * @see com.cqust.tpo.dao.IDAO#findAll()
	 * @return 邮件任务集合
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public List<MailTask> findAll() throws Exception {
		List<Trigger> list = (List<Trigger>) scheduler.getTriggersOfJob(jobDetail.getKey());
		if(list == null)
			return null;
//		遍历list，转化为MailTask集合，得到结果返回
		List<MailTask> result = new ArrayList<MailTask>(list.size());
		
		Trigger trigger = null;
		for (Iterator<Trigger> iterator = list.iterator(); iterator.hasNext();) {
			trigger = iterator.next();
			result.add(triggerConvertToMailTask(trigger));
		}
		return result;
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 将Trigger转化为MailTask
	 * </p>
	 * @param trigger Trigger实例
	 * @return 邮件任务Bean
	 */
	private MailTask triggerConvertToMailTask(Trigger trigger){
		MailTask mailTask = new MailTask();
		JobDataMap jobdatamap = trigger.getJobDataMap();
//		jobdatamap.
		Integer id = trigger.getJobDataMap().getInt(QuartzConstant.JOB_DATA_TRIGGER_ID.getName());
		mailTask.setTimeLetterId(Integer.valueOf(id));
		mailTask.setSendTime(trigger.getStartTime());
		return mailTask;
//		MailTask mailTask = new MailTask();
//		Integer id = trigger.getJobDataMap().getInt(QuartzConstant.JOB_DATA_TRIGGER_ID.getName());
//		mailTask.setTimeLetterId(Integer.valueOf(id));
//		mailTask.setSendTime(trigger.getStartTime());
//		return mailTask;
	}

	/** (non-Javadoc)
	 * 获取指定范围的邮件任务集合
	 * 就是从所有任务集合中找从firstResult开始，找最多maxResult条记录集合。
	 * 规则：
	 * 假如firstResult或者maxResult为负数，返回null.
	 * maxResult如果为0，返回null。
	 * @see com.cqust.tpo.dao.IDAO#findAll(int, int)
	 * @param firstResult 开始点
	 * @param maxResult 最多返回条数
	 * @return 邮件任务集合
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public List<MailTask> findAll(int firstResult, int maxResult) throws Exception {
		if(firstResult < 0 || maxResult <= 0){
			return null;
		}
		List<Trigger> list = (List<Trigger>) scheduler.getTriggersOfJob(jobDetail.getKey());
		if(list == null)
			return null;
//		遍历list，转化为MailTask集合，得到结果返回
		List<MailTask> result = new ArrayList<MailTask>(list.size());
		
//		计算得到结尾
		int size = list.size();
		if(size < firstResult)
			return null;
		if(size > (firstResult + maxResult))
			size = firstResult + maxResult;
//		遍历位置，而后转化输出
		for(int i = firstResult; i < size; i++){
			result.add(triggerConvertToMailTask(list.get(i)));
		}
		return result;
	}

	/** (non-Javadoc)
	 * 获得邮件任务数量.
	 * @see com.cqust.tpo.dao.IDAO#getAllCount()
	 * @return 一个整数，邮件任务总数。
	 * @throws Exception 数据库或者Quartz容器抛出异常
	 */
	@Override
	public int getAllCount() throws Exception {
		List<Trigger> list = (List<Trigger>) scheduler.getTriggersOfJob(jobDetail.getKey());
		return list == null ? 0 : list.size();
	}
}
