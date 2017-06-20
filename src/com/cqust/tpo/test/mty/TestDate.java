/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TestDate.java <br>
 * 包名: com.cqust.tpo.test.mty <br>
 * 创建时间: 2016年9月27日 下午3:23:44 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.test.mty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 类名: TestDate <br>
 * 描述: 测试时间<br>
 * 创建时间: 2016年9月27日 下午3:23:44 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TestDate {

	/**
	 * <b>方法作用 </b><br><p>
	 * 
	 * </p>
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
//		boolean bool = handle();
//		System.out.println(bool);
		System.out.println(daysBetween("2015-9-22", "2015-10-22"));
	}
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		Calendar cal = Calendar.getInstance();    
		cal.setTime(sdf.parse(smdate));    
		long time1 = cal.getTimeInMillis();                 
		cal.setTime(sdf.parse(bdate));    
		long time2 = cal.getTimeInMillis();         
		long between_days=(time2-time1)/(1000*3600*24);  
		
		return Integer.parseInt(String.valueOf(between_days));     
	}  

	/**
	 * <b>方法作用 </b><br><p>
	 * 测试日期类的使用
	 * </p>
	 */
	private static boolean handle() {
		Date date = new Date(2022,11,2,4,5,6);
		if(date == null)
			return false;
		Date now = new Date();
//		if(now.after(date))
//			return false;
		System.out.println("kkk   " + now.after(date));
		return true;
	}

}
