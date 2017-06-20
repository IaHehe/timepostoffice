/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailDaoImpl.java <br>
 * 包名: com.cqust.tpo.dao.impl <br>
 * 创建时间: 2016年9月27日 下午4:09:41 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.dao.impl;

import java.io.File;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.utils.StringUtil;
import com.cqust.tpo.utils.mail.MailFactory;
import com.cqust.tpo.utils.mail.MailFile;
import com.cqust.tpo.utils.mail.MailUtil;
import com.cqust.tpo.utils.quartz.QuartzConstant;

/** 
 * 类名: MailDaoImpl <br>
 * 描述: 邮件Dao实现. <br>
 * 创建时间: 2016年9月27日 下午4:09:41 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailDaoImpl implements Job{

	/** (non-Javadoc)
	 * 发送邮件任务.
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 * @param context job执行时的上下文路径
	 * @throws JobExecutionException 任务执行时可能触发异常
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap jobDataMap = context.getMergedJobDataMap();
//		System.out.println("本次Job运行-------------S-------");
		
//		步骤：
//		1.获取邮件id号
		int timeLetterId = jobDataMap.getInt(QuartzConstant.JOB_DATA_TRIGGER_ID.getName());
//		System.out.println("发邮件 --  id   " + timeLetterId);
//		2.获取邮件信息
//			调用timeLetterDao，获得邮件实例
		ITimeLetterDao timeLetterDao = new TimeLetterDaoImpl();
		TimeLetter timeletter = null;
		boolean flag = true;
		try {
			timeletter = timeLetterDao.findById(timeLetterId);
			if(timeletter == null){
//				System.out.println("获取时光信信息时找不到.终止发送邮件");
				return ;
			}
//			isDelete == N  && timeLetterState  ==  0
//			时光信未删除并且时光信未寄送时，发送
			if("N".equalsIgnoreCase(timeletter.getIsDelete()) && timeletter.getTimeLetterState().equals("0")){
				MailFile mailFile = null;
				String img = timeletter.getTimeLetterImg();
				if(StringUtil.isNotNullAndEmpty(img)){
					mailFile = new MailFile();
					mailFile.setFile(new File(img));
				}
	//		3.调用MailUtil,发出邮件
//				System.out.println("开始发送邮件");
				flag = MailUtil.createTimeLetter("时光邮寄项目小组", timeletter.getLetterRecipent(), 
						mailFile , timeletter.getTimeLetterTitle(), timeletter.getTimeLetterContent());
				
//				4.修改数据库字段，将时光信状态修改为已发送。
				timeletter.setTimeLetterState("1");
				timeLetterDao.doUpdate(timeletter);
			}
//		5.给出结果
//			System.out.println("任务调度报告---> id： " + timeLetterId + " 状态：" + (flag ? "success" : "fail"));
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("获取时光信信息有异常.终止发送邮件");
		}finally{
//			System.out.println("本次Job运行-------------S-------");
		}
	}

}
