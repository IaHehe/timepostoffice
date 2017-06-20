/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: DateUtil.java <br>
 * 包名: com.cqust.tpo.utils <br>
 * 创建时间: 2016年9月28日 上午11:38:34 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 类名: DateUtil <br>
 * 描述: 日期格式化工具 <br>
 * 创建时间: 2016年9月28日 上午11:38:34 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class DateUtil {
	/**
	 * <b>方法作用 </b><br><p>
	 * 按照给定的格式格式化一个字符串，获得Date类型的数据。
	 * 如果有异常，将打印并返回一个null对象
	 * </p>
	 * @param dateStr 日期字符串
	 * @param format 格式化格式
	 * @return 日期类型数据
	 */
	public static Date convertToDate(String dateStr, String format){
		if(dateStr == null)
			return null;
		if(format == null || format.equals("")){
			return null;
		}
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			System.out.println("日期类型转换异常");
			return null;
		}
	}

	/**
	 * <b>方法作用 </b><br><p>
	 * 使用本类的默认字符串格式化传递过来的字符串。
	 * 实现调用{@link #convertToDate(String, String)}
	 * </p>
	 * @see #convertToDate(String, String)
	 * @param dateStr 日期字符串
	 * @return 日期对象
	 */
	public static Date convertToDate(String dateStr) {
		return convertToDate(dateStr, format_default);
	}
	
	/** 
	 * format_default : 默认格式化字符串
	 */
	public static final String format_default = "yyyy-MM-dd hh:mm:ss";
	
	/** 
	 * format_US : us格式
	 */
	public static final String format_US = "EEE MMM dd HH:mm:ss z yyyy";

	/**
	 * <b>方法作用 </b><br><p>
	 *  将日期以字符串形式输出
	 * </p>
	 * @param date 日期
	 * @return 字符串形式的日期
	 */
	public static String toDateString(Date date){
		if(date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format_default);
		return sdf.format(date);
	}
	/**
	 * <b>方法作用 </b><br><p>
	 *  将日期以字符串形式输出,使用指定的格式
	 * </p>
	 * @param date 日期
	 * @param format 日期新的格式
	 * @return 字符串形式的日期
	 */
	public static String toDateString(Date date,String format){
		if(date == null)
			return "";
		if(format == null || format.equals("")){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	
	/**
	 * <b>方法作用 </b><br><p>
	 * 日期从默认的字符串格式转变为定制格式的字符串
	 * 默认格式化形式字符串
	 * </p>
	 * @param dateStr 日期字符串
	 * @return 新的日期格式字符串
	 */
	public static String toDateStrToString(String dateStr, String newFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(format_US, java.util.Locale.US);
		
		try {
			return toDateString(sdf.parse(dateStr), newFormat);
		} catch (ParseException e) {
			System.out.println("格式化日期时出现异常");
		}
		return "";
	}
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 日期从默认的字符串格式转变为定制格式的字符串
	 * 默认格式化形式字符串
	 * </p>
	 * @param dateStr 日期字符串
	 * @return 新的日期格式字符串
	 */
	public static String toDateStrToString(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(format_US, java.util.Locale.US);
		
		try {
			return toDateString(sdf.parse(dateStr));
		} catch (ParseException e) {
			System.out.println("格式化日期时出现异常");
		}
		return "";
	}
	/**
	 * <b>方法作用 </b><br><p>
	 * 计算两个日期之间的天数差。
	 * 格式：
	 * daysBetween(Date, Date)前面比后面早的天数，写反了会出现赋值
	 * 仅有计算邮件价格时使用
	 * </p>
	 * @param smdate  开始时间
	 * @param bdate 结束时间
	 * @return 相差的天数
	 */
	public static int daysBetween(Date smdate,Date bdate){  
		try {
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24);  
			return Integer.parseInt(String.valueOf(between_days));
		} catch (NumberFormatException e) {
			return 0;
		}     
	} 
	/**
	 * <b>方法作用 </b><br><p>
	 * 测试日期格式化代码的正确性
	 * </p>
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		System.out.println(toDateStrToString(date.toString(),"yy-MM-dd---MM --MM--mm" ));
	}
}
