/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: ITimeLetterService.java <br>
 * 包名: com.cqust.tpo.service <br>
 * 创建时间: 2016年9月28日 上午12:36:22 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cqust.tpo.models.TimeLetter;

/** 
 * 类名: ITimeLetterService <br>
 * 描述: 时光信Service层 <br>
 * 创建时间: 2016年9月28日 上午12:36:22 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public interface ITimeLetterService extends IService<Integer, TimeLetter>{

	/**
	 * <b>方法作用 </b><br><p>
	 * 通过用户ID获取它的时光信.<br>
	 * 一个Map类型保存数据。
	 * 格式：
	 * key:TimeLetterList   value:List形式的集合，包含该用户所有时光信集合。
	 * key:LetterPaperOrderList  value:List形式的集合，包含该用户所有纸质信集合。
	 * </p>
	 * @param userId 用户ID 
	 * @return  一个map对象，包含了该用户所有的时光信和纸质信集合。
	 * 
	 */
	Map<String, List> browseOwnLetter(int userId);

	
	/**
	 * 时光信关注量排行榜，通过TopAttentionfindAllDesc()得到排行榜链表
	 */
	public List<TimeLetter> AttentionTopRanklist();
	
	
	/**
	 * 时光信点赞量排行榜，通过TopClickfindAllDesc()得到排行版链表
	 * @return
	 */
	public List<TimeLetter> ClickTopRanklist();
	
	/**
	 * 通过时光信Id获取TimeLetter对象
	 * @param timeletterId
	 * @return
	 */
	public List<TimeLetter> findByTimeLetterIdList(int timeletterId);
	
	/**
	 * 通过时光信Id更新时光信关注量
	 * @param timeletterId
	 */
	public void UpdateAttention(int timeletterId);
	
	/**
	 * 信箱开启界面显示，所有的时光信，包含信和用户
	 * @return
	 */
	public List<Map> findAllTimeLetterAndUser();
	
	
	/**
	 * 根据时光信id查询时光信是否公开属性("isOpen"),并更新
	 * @param isopen
	 * @param timeletterid
	 */
	public void UpdateTimeLetterisOpen(String one,int timeletterid);

	/**
	 * 根据时光信id查询时光信是否上排行榜("isTop"),并更新
	 * @param isopen
	 * @param timeletterid
	 */
	public void UpdateTimeLetterisTop(String isTop, int timeletterid);


	/**
	 * 显示所有点赞信息
	 * @return
	 */
	public List<TimeLetter> ClickToplist();


	public List<TimeLetter> AttentionToplist();
}
