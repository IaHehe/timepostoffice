/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TimeLetterImpl.java <br>
 * 包名: com.cqust.tpo.service.impl <br>
 * 创建时间: 2016年9月28日 上午12:37:40 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cqust.tpo.dao.ILetterPaperOrderDao;
import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.dao.impl.LetterPaperOrderDaoImpl;
import com.cqust.tpo.dao.impl.MailTaskDaoImpl;
import com.cqust.tpo.dao.impl.TimeLetterDaoImpl;
import com.cqust.tpo.dao.impl.UserDaoImpl;
import com.cqust.tpo.models.MailTask;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.service.ITimeLetterService;
import com.cqust.tpo.utils.DateUtil;

/** 
 * 类名: TimeLetterServiceImpl <br>
 * 描述: 时光信Service实现类 <br>
 * 创建时间: 2016年9月28日 上午12:37:40 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TimeLetterServiceImpl implements ITimeLetterService{
	private IUserDao userDao = new UserDaoImpl();
	private ITimeLetterDao timeletterDao = new TimeLetterDaoImpl();

	@Override
	public void insert(TimeLetter vo) {
		// TODO Auto-generated method stub
		try {
			timeletterDao.doInsert(vo);
//			以创建时间为基准，查找刚插入的数据
			int timeLetterId = timeletterDao.getTimeLetterIdByCreateAndSendTime(vo.getCreateTime(), vo.getLetterSendTime());
			
//			System.out.println(vo);
//			添加到Quartz框架
			MailTask mt = new MailTask();
			mt.setTimeLetterId(timeLetterId);
			mt.setSendTime(vo.getLetterSendTime());
//			将此任务信息插入数据库
			System.out.println(mt);
			new MailTaskDaoImpl().doInsert(mt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.service.IService#update(java.lang.Object)
	 * @param vo
	 */
	@Override
	public void update(TimeLetter vo) {
		// TODO Auto-generated method stub
		
	}

	/** (non-Javadoc)
	 * 通过时光信id删除此时光信.
	 * @see com.cqust.tpo.service.IService#delete(java.lang.Object)
	 * @param id 时光信Id
	 */
	@Override
	public void delete(Integer id) {
		try {
			timeLetterDao.doDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.service.IService#get(java.lang.Object)
	 * @param id
	 * @return
	 */
	@Override
	public TimeLetter get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		int count;
		try {
			count = timeletterDao.getAllCount();
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<TimeLetter> list() {
		try {
			List<TimeLetter> timeletterlist = timeletterDao.findAll();
			return timeletterlist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	/** (non-Javadoc)
	 * @see com.cqust.tpo.service.IService#list(int, int)
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@Override
	public List<TimeLetter> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	/** (non-Javadoc)
	 * 获取指定用户的时光信和纸质信信息.
	 * 规则：
	 * 调用Dao相关方法，组装数据而后放入Map对象
	 * 纸质信和时光信都是这样处理，最后返回。
	 * 
	 * 返回值：
	 * 一个Map类型保存数据。
	 * 格式：
	 * key:  TimeLetterList   value:List形式的集合，包含该用户所有时光信集合。
	 * key:  LetterPaperOrderList  value:List形式的集合，包含该用户所有纸质信集合。
	 * </p>
	 * @see com.cqust.tpo.service.ITimeLetterService#browseOwnLetter(int)
	 * @param userId 用户ID 
	 * @return  一个map对象，包含了该用户所有的时光信和纸质信集合。
	 */
	@Override
	public Map<String, List> browseOwnLetter(int userId) {
		Map<String, List> map = new HashMap<String, List>();
		
//		获取此用户的时光信集合
		try {
			map.put("TimeLetterList", timeLetterDao.findAllByUserId(userId));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("TimeLetterList", null);
		}
		
//		获取此用户的纸质信集合
		try {
			map.put("LetterPaperOrderList", letterPaperOrderDao.findAllByUserId(userId));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("LetterPaperOrderList", null);
		}
//		组装数据，而后返回
		return map;
	}

	/** 
	 * timeLetterDao : 时光信Dao实例，用来访问时光信信息
	 */
	private ITimeLetterDao timeLetterDao = new TimeLetterDaoImpl();
	
	/** 
	 * LetterPaperOrderDaoImpl : 纸质信Dao实例，用来访问纸质信信息
	 */
	private ILetterPaperOrderDao letterPaperOrderDao = new LetterPaperOrderDaoImpl();

	/**
	 * 排行榜
	 */
	@Override
	public List<TimeLetter> AttentionTopRanklist() {
		// TODO Auto-generated method stub
		List<TimeLetter> AtteentionTopRankList = timeletterDao.TopAttentionfindAllDesc(); 
		return AtteentionTopRankList;
	}

	@Override
	public List<TimeLetter> ClickTopRanklist() {
		// TODO Auto-generated method stub
		List<TimeLetter> ClickTopRankList =timeletterDao.TopClickfindAllDesc();
		return ClickTopRankList;
	}
	
	@Override
	public List<TimeLetter> findByTimeLetterIdList(int timeletterId) {
		// TODO Auto-generated method stub
		List<TimeLetter> timeletter = timeletterDao.findtimeLetterById(timeletterId);
		return timeletter;
	}

	@Override
	public void UpdateAttention(int timeletterId) {
		// TODO Auto-generated method stub
		timeletterDao.UpdateAttentionByTLId(timeletterId);
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.service.ITimeLetterService#findAllTimeLetterAndUser()
	 * @return
	 */
	@Override
	public List<Map> findAllTimeLetterAndUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateTimeLetterisOpen(String one, int timeletterid) {
		// TODO Auto-generated method stub
		timeletterDao.updatetimeletterisOpen(one,timeletterid);
		
	}

	@Override
	public void UpdateTimeLetterisTop(String isTop, int timeletterid) {
		// TODO Auto-generated method stub
		timeletterDao.updatetimeletterisTop(isTop,timeletterid);
	}

	@Override
	public List<TimeLetter> ClickToplist() {
		// TODO Auto-generated method stub
		List<TimeLetter> ClickTopRankList =timeletterDao.ManageTopClickfindAllDesc();
		return ClickTopRankList;
	}

	@Override
	public List<TimeLetter> AttentionToplist() {
		// TODO Auto-generated method stub
		List<TimeLetter> AttentionTopRankList =timeletterDao.ManageTopAttentionfindAllDesc();
		return AttentionTopRankList;
	}



}
