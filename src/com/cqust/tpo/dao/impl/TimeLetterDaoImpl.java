/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: TimeLetterDaoImpl.java <br>
 * 包名: com.cqust.tpo.dao.impl <br>
 * 创建时间: 2016年9月28日 上午1:07:33 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */
package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cqust.tpo.dao.ITimeLetterDao;
import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;
import com.cqust.tpo.utils.DateUtil;

/** 
 * 类名: TimeLetterDaoImpl <br>
 * 描述: 时光信Dao的具体实现类。 <br>
 * 创建时间: 2016年9月28日 上午1:07:33 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class TimeLetterDaoImpl implements ITimeLetterDao {

	/** (non-Javadoc)
	 * @see com.cqust.tpo.dao.IDAO#doInsert(java.lang.Object)
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public boolean doInsert(TimeLetter vo) throws Exception {
		System.out.println(vo.getUser().getUserName());
		Date date = new Date();
		vo.setCreateTime(date);
		System.out.println(vo.getCreateTime());
		vo.setAttentionNumber(0);
		vo.setClickNumber(0);
		vo.setIsRecommend("N");
		vo.setIsDisplay("Y");
		vo.setIsTop("Y");
		vo.setIsDelete("N");
		vo.setTimeLetterState("0");
		int a = vo.getUser().getUserId();
		String b = vo.getTimeLetterTitle();
		String c = vo.getTimeLetterContent();
		String d = DateUtil.toDateString(vo.getLetterSendTime());
		String e = vo.getLetterRecipent();
		String f =  DateUtil.toDateString(vo.getCreateTime());
		String g = vo.getIsOpen()==null?"N":"Y";
		int h = vo.getAttentionNumber();
		int i = vo.getClickNumber();
		String j = vo.getTimeLetterImg();
		String k = vo.getIsRecommend();
		String l = vo.getIsDisplay();
		String m = vo.getIsTop();
		String n = vo.getTimeLetterState();
		String o = vo.getIsDelete();
		String sql = "INSERT INTO tp_timeletter "
				+ "(userId,timeLetterTitle,timeLetterContent,"
				+ "letterSendTime,letterRecipent,createTime,isOpen,"
				+ "attentionNumber,clickNumber,timeLetterImg,"
				+ "isRecommend,isDisplay,isTop,timeLetterState,isDelete) "
				+ "VALUES ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"',"
						+ "'"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"',"
								+ "'"+m+"','"+n+"','"+o+"')";
		System.out.println(sql);
		boolean rs = ControlDB.executeUpdate(sql);
		if(rs == true)
			return true;
		else
			return false;
		// TODO Auto-generated method stub
		
	}

	/** (non-Javadoc)
	 * 更新数据库中的字段信息，以Id为标准
	 * 仅仅更新时光信状态一栏
	 * @see com.cqust.tpo.dao.IDAO#doUpdate(java.lang.Object)
	 * @param vo 时光信Bean
	 * @return  成功与否
	 * @throws Exception 
	 */
	@Override
	public boolean doUpdate(TimeLetter vo) throws Exception {
		TimeLetter tl = findById(vo.getTimeLetterId());
		if(tl == null){
			return false;
		}
		String sql = "update tp_timeletter set timeLetterState = '" + vo.getTimeLetterState() + "' where timeLetterId = " + vo.getTimeLetterId();
		return ControlDB.executeUpdate(sql);
	}

	/** (non-Javadoc)
	 * @see com.cqust.tpo.dao.IDAO#doDelete(java.lang.Object)
	 * @param id
	 * @return 
	 * @throws Exception
	 */
	@Override
	public boolean doDelete(Integer id) throws Exception {
		if(id == null){
			System.out.println("删除时光信失败了");
			return false;
		}
		String sql = "update tp_timeletter set isDelete = 'Y' where timeLetterId = " + id;
		return ControlDB.executeUpdate(sql);
	}

	/** (non-Javadoc)
	 * 通过时光信ID获取时光信Bean
	 * 不存在返回null
	 * @see com.cqust.tpo.dao.IDAO#findById(java.lang.Object)
	 * @param id 时光信ID
	 * @return 时光信Bean
	 * @throws Exception SQL异常或者其他异常
	 */
	@Override
	public TimeLetter findById(Integer id) throws Exception {
		String sql = "select * from tp_timeletter where timeLetterId = '" + id + "'";
		ResultSet rs = ControlDB.executeQuery(sql);
		User user = new User();
//		改动过
		TimeLetter timeletter = null;
		try{
			if(rs.next()) {
				timeletter = new TimeLetter();
				timeletter.setTimeLetterId(rs.getInt("timeLetterId"));
				timeletter.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				timeletter.setTimeLetterContent(rs.getString("timeLetterContent"));
				timeletter.setLetterSendTime(rs.getDate("letterSendTime"));
				timeletter.setLetterRecipent(rs.getString("letterRecipent"));
				timeletter.setCreateTime(rs.getDate("createTime"));
				timeletter.setIsOpen(rs.getString("isOpen"));
				timeletter.setAttentionNumber(rs.getInt("attentionNumber"));
				timeletter.setClickNumber(rs.getInt("clickNumber"));
				timeletter.setTimeLetterImg(rs.getString("timeLetterImg"));
				timeletter.setIsRecommend(rs.getString("isRecommend"));
				timeletter.setIsDisplay(rs.getString("isDisplay"));
				timeletter.setTimeLetterState(rs.getString("timeLetterState"));
				timeletter.setIsTop(rs.getString("isTop"));
				timeletter.setIsDelete(rs.getString("isDelete"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return timeletter;
	}
	
	/**
	 * 查询全部的时光信信息
	 * @return 多个对象信息使用 List 返回如果 List 集合的 size() 长度为 0，则表示没有数据返回
	 * @throws Exception 操作如果出现了异常，返回给被调用处执行处理
	 */
	@Override
	public List<TimeLetter> findAll() throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		/*String sql = "select * from tp_timeletter where 1=1";*/
		String sql = "SELECT * FROM tp_timeletter WHERE isOpen='Y' and isDisplay='Y'";
		ResultSet rs = ControlDB.executeQuery(sql);
		IUserDao userDao = new UserDaoImpl();
		try{
			while(rs.next()){
				TimeLetter tl = new TimeLetter();
				int userid = rs.getInt("userId");
				tl.setUser(userDao.findById(userid));
//				System.out.print(userid);
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				String subShowContent = rs.getString("timeLetterContent");
				if(subShowContent.length() >= 150)
					tl.setTimeLetterContent(subShowContent.substring(0, 150));
				else
					tl.setTimeLetterContent(subShowContent);
				tl.setLetterSendTime(rs.getDate("letterSendTime"));
				tl.setLetterRecipent(rs.getString("letterRecipent"));
				tl.setCreateTime(rs.getDate("createTime"));
//				tl.setIsOpen(rs.getString("isOpen"));
				tl.setAttentionNumber(rs.getInt("attentionNumber"));
				tl.setClickNumber(rs.getInt("clickNumber"));
				tl.setTimeLetterImg(rs.getString("timeLetterImg"));
				tl.setIsRecommend(rs.getString("isRecommend"));
				tl.setIsDisplay(rs.getString("isDisplay"));
				char state[] = rs.getString("timeLetterState").toCharArray();
				if(state[0]=='1')
					tl.setTimeLetterState("已送达");
				else
					tl.setTimeLetterState("未送达");
//				System.out.println(state[0]);
//				tl.setTimeLetterState(rs.getString("timeLetterState"));
				tl.setIsTop(rs.getString("isTop"));
				tl.setIsDelete(rs.getString("isDelete"));
				li.add(tl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}
	
	/** (non-Javadoc)
	 * @see com.cqust.tpo.dao.IDAO#findAll(int, int)
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TimeLetter> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据查询数据库得到已发送的时光信数目
	 */
	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "select * FROM tp_timeletter WHERE timeLetterState = 'Y'";
		ResultSet rs = ControlDB.executeQuery(sql);
		int count = 0;
		while(rs.next()){
			count++;
		}
		return count;
	}

	/**
	 * 时光信关注量和点赞量排行榜
	 * @throws SQLException 
	 */
	@Override
	public List<TimeLetter> TopAttentionfindAllDesc() {
		// TODO Auto-generated method stub
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "SELECT * FROM tp_timeletter WHERE isOpen='Y' AND isTop='Y' ORDER BY attentionNumber DESC";
		ResultSet rs = ControlDB.executeQuery(sql);
		int count = 0;
		try {
			while(rs.next() && count<=10){
				count++;
				TimeLetter tl = new TimeLetter();
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				tl.setAttentionNumber(rs.getInt("attentionNumber"));
				li.add(tl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}

	@Override
	public List<TimeLetter> TopClickfindAllDesc() {
		// TODO Auto-generated method stub
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "SELECT * FROM tp_timeletter WHERE isOpen='Y' AND isTop='Y' ORDER BY clickNumber DESC";
		ResultSet rs = ControlDB.executeQuery(sql);
		int count = 0;
		try {
			while(rs.next() && count<=10){
				count++;
				TimeLetter tl = new TimeLetter();
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				tl.setClickNumber(rs.getInt("clickNumber"));
				li.add(tl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}

	@Override
	public List<TimeLetter> findtimeLetterById(int timeletterid) {
		// TODO Auto-generated method stub
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "SELECT * FROM tp_timeletter WHERE timeLetterId = '" + timeletterid + "'";
		ResultSet rs = ControlDB.executeQuery(sql);
		TimeLetter tl = new TimeLetter();
		IUserDao user = new UserDaoImpl();
		try {
			if(rs.next()){
				int userid = rs.getInt("userId");
				System.out.println("<<<<<<<<<<<<<<<<<<");
				System.out.println(userid);
				try {
					tl.setUser(user.findById(userid));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				tl.setTimeLetterContent(rs.getString("timeLetterContent"));
				tl.setLetterSendTime(rs.getDate("letterSendTime"));
				tl.setLetterRecipent(rs.getString("letterRecipent"));
				tl.setCreateTime(rs.getDate("createTime"));
				tl.setIsOpen(rs.getString("isOpen"));
				tl.setAttentionNumber(rs.getInt("attentionNumber"));
				tl.setClickNumber(rs.getInt("clickNumber"));
				tl.setTimeLetterImg(rs.getString("timeLetterImg"));
				tl.setIsRecommend(rs.getString("isRecommend"));
				tl.setIsDisplay(rs.getString("isDisplay"));
				char state[] = rs.getString("timeLetterState").toCharArray();
				if(state[0]=='1')
					tl.setTimeLetterState("已寄送");
				else
					tl.setTimeLetterState("未寄送");
				tl.setIsTop(rs.getString("isTop"));
				tl.setIsDelete(rs.getString("isDelete"));
				li.add(tl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}


	@Override
	public boolean UpdateAttentionByTLId(int timeletterid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tp_timeletter SET attentionNumber = attentionNumber + 1 WHERE timeLetterId = '"+timeletterid+"'";
		boolean rs = ControlDB.executeUpdate(sql);
		return rs;
	}

	
	
	/**
	 * <b>方法作用 </b><br><p>
	 * 通过用户ID，找到所有此用户的list集合
	 * 如果根据用户找不到用户，返回一个Null对象
	 * </p>
	 * @param userid 用户id
	 * @see com.cqust.tpo.dao.ITimeLetterDao#findAllByUserId()
	 * @return 此用户的时光信信息
	 */
	@Override
	public List findAllByUserId(int userid) {
//		步骤
//		1.获得此用户用户bean
		IUserDao userdao = new UserDaoImpl();
		User user = null;
		try {
			user = userdao.findById(userid);
		} catch (Exception e1) {
			System.out.println("用户不存在，或者获取时异常");
			e1.printStackTrace();
		}
		if(user == null){
			System.out.println("此用户不存在");
			return null;
		}
//		2.获得此用户的所有时光信
		/**
		 * SQL语句：
		 * select timeLetterId,userId,timeLetterTitle,timeLetterContent,letterSendTime,letterRecipent,createTime,
		 * isOpen,attentionNumber,clickNumber,timeLetterImg,timeLetterState,isRecommend,isDisplay,isTop,
		 * timeLetterState,isDelete 
		 * from tp_timeletter where userid=1 and isDelete='N'
		 */
		String sql = "select timeLetterId,userId,timeLetterTitle,timeLetterContent,letterSendTime,letterRecipent,createTime,isOpen,attentionNumber,clickNumber,timeLetterImg,timeLetterState,isRecommend,isDisplay,isTop,timeLetterState,isDelete from tp_timeletter where userid=" + userid + " and isDelete='N'";
		ResultSet rs = ControlDB.executeQuery(sql);
		try {
			List list = new ArrayList();
			TimeLetter timeLetter = null;
			while (rs.next()) {
				timeLetter = new TimeLetter();
				timeLetter.setAttentionNumber(rs.getInt("attentionNumber"));
				timeLetter.setClickNumber(rs.getInt("clickNumber"));
				timeLetter.setCreateTime(DateUtil.convertToDate(rs.getString("createTime")));
				timeLetter.setIsDelete(rs.getString("isDelete"));
				timeLetter.setIsDisplay(rs.getString("isDisplay"));
				timeLetter.setIsOpen(rs.getString("isOpen"));
				timeLetter.setIsRecommend(rs.getString("isRecommend"));
				timeLetter.setIsTop(rs.getString("isTop"));
				timeLetter.setLetterRecipent(rs.getString("letterRecipent"));
				timeLetter.setLetterSendTime(DateUtil.convertToDate(rs.getString("letterSendTime")));
				timeLetter.setTimeLetterContent(rs.getString("timeLetterContent"));

				timeLetter.setTimeLetterId(rs.getInt("timeLetterId"));
				timeLetter.setTimeLetterImg(rs.getString("timeLetterImg"));
				timeLetter.setTimeLetterState(rs.getString("timeLetterState"));
				timeLetter.setTimeLetterTitle(rs.getString("timeLetterTitle"));

				list.add(timeLetter);
			}
			return list.size() <= 0 ? null : list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			CloseDB.close(rs);
		}
		return null;
	}
	@Override
	public User getUserByTimeLetterId(Integer id) {

		//1.通过时光信id得到用户id
		//2，调用userdaoimpl得到用户，返回
		return null;
	}
	

	@Override
	public boolean updatetimeletterisOpen(String one, int timeletterid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tp_timeletter SET isOpen = '"+one+"' WHERE timeLetterId = '"+timeletterid+"'";
		boolean rs = ControlDB.executeUpdate(sql);
		if(rs == true)
			return true;
		else
			return false;
	}

	@Override
	public boolean updatetimeletterisTop(String isTop, int timeletterid) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tp_timeletter SET isTop = '"+isTop+"' WHERE timeLetterId = '"+timeletterid+"'";
		boolean rs = ControlDB.executeUpdate(sql);
		if(rs == true)
			return true;
		else
			return false;
	}

	@Override
	public List<TimeLetter> ManageTopClickfindAllDesc() {
		// TODO Auto-generated method stub
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "SELECT * FROM tp_timeletter WHERE isDelete = 'Y' ORDER BY clickNumber DESC";
		ResultSet rs = ControlDB.executeQuery(sql);
		try {
			while(rs.next()){
				TimeLetter tl = new TimeLetter();
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				tl.setTimeLetterContent(rs.getString("timeLetterContent"));
				tl.setClickNumber(rs.getInt("clickNumber"));
				li.add(tl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}

	@Override
	public List<TimeLetter> ManageTopAttentionfindAllDesc() {
		ArrayList<TimeLetter> li = new ArrayList<TimeLetter>();
		String sql = "SELECT * FROM tp_timeletter WHERE isDelete = 'Y' ORDER BY attentionNumber DESC";
		ResultSet rs = ControlDB.executeQuery(sql);
		try {
			while(rs.next()){
				TimeLetter tl = new TimeLetter();
				tl.setTimeLetterId(rs.getInt("timeLetterId"));
				tl.setTimeLetterTitle(rs.getString("timeLetterTitle"));
				tl.setTimeLetterContent(rs.getString("timeLetterContent"));
				tl.setAttentionNumber(rs.getInt("attentionNumber"));
				li.add(tl);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return li;
	}

	/** (non-Javadoc)
	 * 根据时光信创建时间，修改时间，结合id自增长列的降序顺序限制输出一条数据。
	 * 就认为那是新插入的数据。也可以根据内容查询。
	 * 如果没找到此时光信，返回  0
	 * @see com.cqust.tpo.dao.ITimeLetterDao#getTimeLetterIdByCreateAndSendTime(java.util.Date, java.util.Date)
	 * @param createTime 目标时光信创建时间
	 * @param SendTime 目标时光信发送时间
	 * @return 时光信Bean
	 */
	@Override
	public int getTimeLetterIdByCreateAndSendTime(Date createTime, Date SendTime) {
//		仅有一条数据输出
		String sql = "select timeLetterId from tp_timeletter where letterSendTime = '" + DateUtil.toDateString(SendTime) + 
				"' and createTime = '" + DateUtil.toDateString(createTime) + "' order by  timeLetterId desc limit 1";
		ResultSet rs = ControlDB.executeQuery(sql);
		try{
			if(rs.next()) {
				return rs.getInt("timeLetterId");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			CloseDB.close(rs);
		}
		return 0;
	}
}
