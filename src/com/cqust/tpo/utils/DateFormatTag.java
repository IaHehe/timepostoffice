/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: DateFormatTag.java <br>
 * 包名: com.cqust.tpo.utils <br>
 * 创建时间: 2016年9月28日 下午3:57:52 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


/** 
 * 类名: DateFormatTag <br>
 * 描述: 日期格式化标签 <br>
 * 创建时间: 2016年9月28日 下午3:57:52 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class DateFormatTag extends SimpleTagSupport{

	/** (non-Javadoc)
	 * 将日期格式化后以字符串形式输出
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 * @throws JspException  jsp异常
	 * @throws IOException IO流异常
	 */
	@Override
	public void doTag() throws JspException, IOException {
		JspWriter pw = this.getJspContext().getOut();
		String result = null;
		if(format == null || format.trim().equals("")){
			result = DateUtil.toDateString(date);
		}else{
			result = DateUtil.toDateString(date, format);
		}
		pw.print(result);
//		String str = null;
//		if("".equals(format))
//			str = DateUtil.toDateString(date,format);
//		else
//			str = DateUtil.toDateString(date);
//		pw.print(str);
	}
	
	/** 
	 * date : 日期变量
	 */
	private Date date = null;

	/** 
	 * format : 日期格式化格式
	 */
	private String format = null;

	/**
	 * @param date 设置 date 值/对象
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param format 设置 format 值/对象
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
}
