/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: MailFile.java <br>
 * 包名: com.cqust.tpo.utils.mail <br>
 * 创建时间: 2016年9月29日 上午3:19:18 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.utils.mail;

import java.io.File;

/** 
 * 类名: MailFile <br>
 * 描述: 邮件文件.<br>
 * 创建时间: 2016年9月29日 上午3:19:18 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class MailFile {

	/** 
	 * fileId : 文件对应的键值
	 */
	private String fileId;
	/** 
	 * file : 文件对象
	 */
	private File file = null;
	
	
	/**
	 * <b>工作原理</b><br><p>
	 * 无参构造方法.
	 * </p> 
	 */
	public MailFile(){
	}
	
	
	/**
	 * @return 获取  fileId 值/对象
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @return 获取  file 值/对象
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param fileId 设置 fileId 值/对象
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @param file 设置 file 值/对象
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
