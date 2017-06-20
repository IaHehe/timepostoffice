/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: ILetterPaperOrderDao.java <br>
 * 包名: com.cqust.tpo.dao <br>
 * 创建时间: 2016年9月28日 上午1:37:06 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.dao;

import java.util.List;

import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.LetterPaperOrder;

/** 
 * 类名: ILetterPaperOrderDao <br>
 * 描述: 纸质信Dao层接口. <br>
 * 创建时间: 2016年9月28日 上午1:37:06 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public interface ILetterPaperOrderDao extends IDAO<Integer, LetterPaperOrder> {
	/**
	 * <b>方法作用 </b><br><p>
	 * 通过用户ID，找到所有此用户的list集合
	 * </p>
	 * @param userid 用户id
	 * @return 此用户的纸质信信息
	 */
	public List findAllByUserId(int userid) ;
	
	public LetterPaperOrder findById(String uuid);
	
	public boolean updateStateto1(String uuid);
	
}
