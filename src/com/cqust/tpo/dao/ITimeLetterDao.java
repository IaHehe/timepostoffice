/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: ITimeLetterDao.java <br>
 * 包名: com.cqust.tpo.dao <br>
 * 创建时间: 2016年9月28日 上午1:05:32 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.dao;

import java.util.Date;
import java.util.List;

import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;

/** 
 * 类名: ITimeLetterDao <br>
 * 描述: TimeLetterDao,访问TimeLetter数据接口 <br>
 * 创建时间: 2016年9月28日 上午1:05:32 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public interface ITimeLetterDao extends IDAO<Integer, TimeLetter>{
	/**
	 * <b>方法作用 </b><br><p>
	 * 通过UserID获取数据,找到所有此用户的list集合
	 * </p>
	 * @param userid 用户id
	 * @return 此用户的未删除的消息
	 */
	public List findAllByUserId(int userid);
	

	/**
	 * 关注量排行榜
	 * @return
	 */
	public List<TimeLetter> TopAttentionfindAllDesc();
	
	/**
	 * 点赞排行版
	 * @return
	 */
	public List<TimeLetter> TopClickfindAllDesc();
	
	/**
	 * 根据时光信Id返回TimeLetter对象
	 * @return
	 */
	public List<TimeLetter> findtimeLetterById(int timeletterid);
	
	/**
	 * 根据时光信Id更新时光信关注量
	 * @param timeletterid
	 */
	public boolean UpdateAttentionByTLId(int timeletterid);
	
	
	/**
	 * 通过时光信id获得用户id
	 * @param id
	 * @return
	 */
	public User getUserByTimeLetterId(Integer id);
	/**
	 * @param isopen
	 * @param timeletterid
	 */
	public boolean updatetimeletterisOpen(String one, int timeletterid);

	
	/**
	 * @param isTop
	 * @param timeletterid
	 */
	public boolean updatetimeletterisTop(String isTop, int timeletterid);

	public List<TimeLetter> ManageTopClickfindAllDesc();

	public List<TimeLetter> ManageTopAttentionfindAllDesc();


	/**
	 * <b>方法作用 </b><br><p>
	 * 
	 * </p>
	 * @param createTime
	 * @param letterSendTime
	 * @return 
	 */
	public int getTimeLetterIdByCreateAndSendTime(Date createTime, Date letterSendTime);
	
}