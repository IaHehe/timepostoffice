package com.cqust.tpo.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.cqust.tpo.dao.ILetterPaperOrderDao;
import com.cqust.tpo.dao.IUserDao;
import com.cqust.tpo.db.CloseDB;
import com.cqust.tpo.db.ControlDB;
import com.cqust.tpo.models.LetterPaperOrder;
import com.cqust.tpo.models.LetterPaperPrice;
import com.cqust.tpo.models.PostGuide;
import com.cqust.tpo.models.User;
import com.cqust.tpo.utils.DateUtil;

/** 
 * 类名: LetterPaperOrderDaoImpl <br>
 * 描述: 纸质信的Dao的实现类. <br>
 * 创建时间: 2016年9月28日 上午1:38:25 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class LetterPaperOrderDaoImpl implements ILetterPaperOrderDao {
	
	

	@Override
	public boolean doInsert(LetterPaperOrder vo) throws Exception {
				
		String rn = vo.getLetterPaperName();
		String rp = vo.getLetterPaperPhone();
		String ra = vo.getLetterPaperAddress();
		String rpn = vo.getLetterPaperPostNumber();
		String cn = vo.getCustomerName();
		String cp = vo.getCustomerPhone();
		String cqq = vo.getCustomerQQ();
		String ilpu = vo.getIsLetterPaperUpdate();
		//修改
		Double lppallp = ((LetterPaperOrder)vo).getLetterPaperPrice();
		
		String dom =DateUtil.toDateString(vo.getDateOfMailing());
		String lpct = DateUtil.toDateString(vo.getLetterPaperCreateTime());
		String lpn = vo.getLetterPaperNumber();
		String lps = vo.getLetterPaperState();
		String os = vo.getOrderState();
		Integer user = vo.getUserid();
		
		LetterPaperOrder letterPaperOrder = new LetterPaperOrder();
		
		//修改
		String sql="INSERT INTO tp_letterpaperorder "
				+ "(userId,letterPaperNumber,letterPaperName,letterPaperAddress,letterPaperpostNumber,letterPaperPhone,dateOfMailing,customerName,customerPhone,customerQQ,letterPaperPrice,letterPaperState,orderState,isLetterPaperUpdate,letterPaperCreateTime) "
				+ "VALUES('"+user+"','"+lpn+"','"+rn+"','"+ra+"','"+rpn+"','"+rp+"','"+dom+"','"+cn+"','"+cp+"','"+cqq+"','"+lppallp+"','"+lps+"','"+os+"','"+ilpu+"','"+lpct+"')";
						
		System.out.println(sql);
		boolean stmt = ControlDB.executeUpdate(sql);  
		return stmt;		
	}

	@Override
	public boolean doUpdate(LetterPaperOrder lpo) throws Exception {
		
/*		LetterPaperOrder letterPaperOrder = new LetterPaperOrder();
		
		String sql="UPDATE tp_letterpaperorder SET orderState = 1 WHERE letterPaperNumber = '"+lpo.getLetterPaperNumber()+"'";
		System.out.println(sql);
		
		boolean stmt = ControlDB.executeUpdate(sql);	
		return stmt;	*/
		return false;
	}

	@Override
	public boolean doDelete(Integer id) throws Exception {
		if(id == null){
			System.out.println("删除时光信失败了");
			return false;
		}
		String sql = "delete from tp_letterpaperorder where letterPaperId = " +id; //"update tp_letterpaperorder set isDelete = 'Y' where letterPaperId = " + id;
		System.out.println(sql);
		return ControlDB.executeUpdate(sql);
	}

	@Override
	public LetterPaperOrder findById(Integer id) throws Exception {
		if(id == null)
		{
			System.out.println("获取到的ID为空");
		}
		String sql="select * from tp_letterpaperorder where letterPaperId = " +id;
		ResultSet rs = ControlDB.executeQuery(sql);
		LetterPaperOrder lpo = new LetterPaperOrder();
		try{	
			while(rs.next()){
			lpo.setCustomerName(rs.getString("CUSTOMERNAME"));
			lpo.setCustomerPhone(rs.getString("CUSTOMERPHONE"));
			lpo.setCustomerQQ(rs.getString("CUSTOMERQQ"));
			lpo.setLetterPaperAddress(rs.getString("LETTERPAPERADDRESS"));
			lpo.setLetterPaperPostNumber(rs.getString("LETTERPAPERPOSTNUMBER"));
			lpo.setLetterPaperPhone(rs.getString("LETTERPAPERPHONE"));	
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB.close(rs);
		}
		return lpo;
	}

	@Override
	public List<LetterPaperOrder> findAll() throws Exception {
		List<LetterPaperOrder> li = new ArrayList<LetterPaperOrder>();
		String sql = "select * from tp_letterpaperorder";
		ResultSet rs = ControlDB.executeQuery(sql);	
		try {
			while (rs.next()) {
				LetterPaperOrder lpo1 = new LetterPaperOrder();
				
				lpo1.setLetterPaperId(rs.getInt("LETTERPAPERID"));
				lpo1.setLetterPaperNumber(rs.getString("LETTERPAPERNUMBER"));
				lpo1.setLetterPaperName(rs.getString("LETTERPAPERName"));
				lpo1.setDateOfMailing(rs.getDate("DATEOFMAILING"));
				lpo1.setLetterPaperPrice(rs.getDouble("LETTERPAPERPRICE"));
				lpo1.setOrderState(rs.getString("ORDERSTATE"));
				lpo1.setLetterPaperState(rs.getString("LETTERPAPERSTATE"));
				lpo1.setIsLetterPaperUpdate(rs.getString("ISLETTERPAPERUPDATE"));
				lpo1.setLetterPaperAddress(rs.getString("LETTERPAPERADDRESS"));
				lpo1.setLetterPaperPostNumber(rs.getString("LETTERPAPERPOSTNUMBER"));
				lpo1.setLetterPaperPhone(rs.getString("LETTERPAPERPHONE"));
				lpo1.setCustomerName(rs.getString("CUSTOMERNAME"));
				lpo1.setCustomerPhone(rs.getString("CUSTOMERPHONE"));
				lpo1.setCustomerQQ(rs.getString("CUSTOMERQQ"));
				
				li.add(lpo1);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB.close(rs);
		}
		return li;
	}

	@Override
	public List<LetterPaperOrder> findAll(int firstResult, int maxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	/**
	 * <b>方法作用 </b><br><p>
	 * 通过用户ID，找到所有此用户的纸信，组装list，而后返回list集合
	 * 如果用户id不存在或者说用户没有纸质信，返回一个null
	 * </p>
	 * @see com.cqust.tpo.dao.ILetterPaperOrderDao#findAllByUserId()
	 * @param userid 用户id
	 * @return 此用户的纸质信信息
	 */
	@Override
	public List findAllByUserId(int userid) {
//		System.out.println("必须实现此方法....");
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
//		2.获得此用户的所有纸信
//				1.组装sql语句
//		select letterPaperId,letterPaperNumber,letterPaperName,letterPaperAddress,
//		letterPaperPostNumber,letterPaperPhone,dateOfMailing,customerName,
//		customerPhone,customerQQ,letterPaperPrice,letterPaperState,orderState,
//		isLetterPaperUpdate,letterPaperCreateTime
//		from tp_letterpaperorder 
//		where userid=" + userid;
		String sql = "select letterPaperId,letterPaperNumber,letterPaperName,letterPaperAddress,letterPaperPostNumber,letterPaperPhone,dateOfMailing,customerName,customerPhone,customerQQ,letterPaperPrice,letterPaperState,orderState,isLetterPaperUpdate,letterPaperCreateTime from tp_letterpaperorder where letterPaperState != 3 and userid=" + userid;
//				2.调用工具类查询方法，得到结果集
		ResultSet rs = ControlDB.executeQuery(sql);
		List<LetterPaperOrder> list = new ArrayList<LetterPaperOrder>();
		LetterPaperOrder lpo = null;
		//				3.处理结果集，得到List
		try {
			while (rs.next()) {
				//处理每一条数据，组装List集合
				lpo = new LetterPaperOrder();
				lpo.setLetterPaperId(rs.getInt("letterPaperId"));
				lpo.setLetterPaperNumber(rs.getString("letterPaperNumber"));
				lpo.setLetterPaperCreateTime(DateUtil.convertToDate(rs.getString("letterPaperCreateTime")));
				lpo.setCustomerName(rs.getString("customerName"));
				lpo.setLetterPaperName(rs.getString("letterPaperName"));
				lpo.setLetterPaperAddress(rs.getString("letterPaperAddress"));
				lpo.setLetterPaperPostNumber(rs.getString("letterPaperPostNumber"));
				lpo.setLetterPaperPhone(rs.getString("letterPaperPhone"));
				lpo.setLetterPaperPrice(rs.getDouble("letterPaperPrice"));
				lpo.setCustomerPhone(rs.getString("customerPhone"));
				lpo.setCustomerQQ(rs.getString("customerQQ"));
				lpo.setDateOfMailing(DateUtil.convertToDate(rs.getString("dateOfMailing")));
				lpo.setLetterPaperState(rs.getString("letterPaperState"));
				lpo.setOrderState(rs.getString("orderState"));
				lpo.setIsLetterPaperUpdate(rs.getString("isLetterPaperUpdate"));
				list.add(lpo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB.close(rs);
		}
		return list.size() <= 0  ? null : list ;
	}

	@Override
	public LetterPaperOrder findById(String uuid) {
		
		ILetterPaperOrderDao letterpaperorderdao = new LetterPaperOrderDaoImpl();
		LetterPaperOrder letterpaperorder = null;
		
		if(uuid==null){
			System.out.println("不存在！");
		}
		String sql = "SELECT * FROM tp_letterpaperorder WHERE letterPaperNumber = '"+uuid+"'";
		ResultSet rs = ControlDB.executeQuery(sql);
		LetterPaperOrder lpo = new LetterPaperOrder();
		try {
			while (rs.next()) {
				lpo.setUserid(rs.getInt("userId"));
				lpo.setLetterPaperId(rs.getInt("letterPaperId"));
				lpo.setLetterPaperNumber(rs.getString("letterPaperNumber"));
				lpo.setLetterPaperCreateTime(DateUtil.convertToDate(rs.getString("letterPaperCreateTime")));
				lpo.setCustomerName(rs.getString("customerName"));
				lpo.setLetterPaperName(rs.getString("letterPaperName"));
				lpo.setLetterPaperAddress(rs.getString("letterPaperAddress"));
				lpo.setLetterPaperPostNumber(rs.getString("letterPaperPostNumber"));
				lpo.setLetterPaperPhone(rs.getString("letterPaperPhone"));
				lpo.setLetterPaperPrice(rs.getDouble("letterPaperPrice"));
				lpo.setCustomerPhone(rs.getString("customerPhone"));
				lpo.setCustomerQQ(rs.getString("customerQQ"));
				lpo.setDateOfMailing(DateUtil.convertToDate(rs.getString("dateOfMailing")));
				lpo.setLetterPaperState(rs.getString("letterPaperState"));
				lpo.setOrderState(rs.getString("orderState"));
				lpo.setIsLetterPaperUpdate(rs.getString("isLetterPaperUpdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB.close(rs);
		}
		return lpo;
		
	
		
	}

	@Override
	public boolean updateStateto1(String uuid) {
		//LetterPaperOrder letterPaperOrder = new LetterPaperOrder();
		if(uuid==null){
			System.out.println("uuid==null");
		}
		String sql="UPDATE tp_letterpaperorder SET orderState = 1 ,letterPaperState = 1 WHERE letterPaperNumber = '"+uuid+"'";
		System.out.println(sql);
		boolean stmt = ControlDB.executeUpdate(sql);	
		return stmt;
	}


	
/*	public static void main(String [] args) throws Exception {
		
		ILetterPaperOrderDao lpo = new LetterPaperOrderDaoImpl();
		
//		LetterPaperOrder l =lpo.findById("c6934a62-b9ae-4620-9831-01537011f655");
		
		System.out.println(lpo.findAll().size());
		
		
	}*/



}

