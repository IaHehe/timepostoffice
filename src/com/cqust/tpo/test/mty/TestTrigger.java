/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TestTrigger.java <br>
 * 包名: com.cqust.tpo.test.mty <br>
 * 创建时间: 2016年9月27日 下午2:02:36 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.test.mty;

import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/** 
 * 类名: TestTrigger <br>
 * 描述: 测试Trigger <br>
 * 创建时间: 2016年9月27日 下午2:02:36 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TestTrigger {

	/**
	 * <b>方法作用 </b><br><p>
	 * 
	 * </p>
	 * @param args
	 */
	public static void main(String[] args) {
		Trigger trigger = TriggerBuilder.newTrigger().build();
	}

}
