/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TestDao.java <br>
 * 包名: com.cqust.tpo.test.mty <br>
 * 创建时间: 2016年9月28日 上午11:04:47 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.test.mty;

import java.util.List;

import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.dao.impl.TimeLetterDaoImpl;
import com.cqust.tpo.models.TimeLetter;

/** 
 * 类名: TestDao <br>
 * 描述: 测试dao层编码是否正确<br>
 * 创建时间: 2016年9月28日 上午11:04:47 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TestDao {

	public static void main(String[] args) {
		ITimeLetterDao timedao = new TimeLetterDaoImpl();
		
		List<TimeLetter> list = timedao.findAllByUserId(1);
		
		System.out.println(list);
	}
}
