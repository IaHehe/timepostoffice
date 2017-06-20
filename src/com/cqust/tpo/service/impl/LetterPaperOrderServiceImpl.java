package com.cqust.tpo.service.impl;

import java.util.Date;
import java.util.List;

import com.cqust.tpo.dao.ILetterPaperOrderDao;
import com.cqust.tpo.dao.impl.LetterPaperOrderDaoImpl;
import com.cqust.tpo.dao.impl.LetterPaperPriceDaoImpl;
import com.cqust.tpo.dao.impl.UserDaoImpl;
import com.cqust.tpo.models.LetterPaperOrder;
import com.cqust.tpo.models.LetterPaperPrice;
import com.cqust.tpo.models.User;
import com.cqust.tpo.service.ILetterPaperOrderService;
import com.cqust.tpo.utils.DateUtil;
import com.cqust.tpo.utils.mail.MailUtil;

public class LetterPaperOrderServiceImpl implements ILetterPaperOrderService {
	
	private ILetterPaperOrderDao letterPaperOrderDao = new LetterPaperOrderDaoImpl();

	
//	@Override
//	public LetterPaperOrder SelectById(int id) {
//		letterPaperOrderDao = new LetterPaperOrderDaoImpl();
//		LetterPaperOrder result = null;
//		try{
//			result  = letterPaperOrderDao.findById(id);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//		
//	}

	
	@Override
	public void delete(Integer id) {
		try {
			letterPaperOrderDao.doDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(LetterPaperOrder vo) {
		// TODO Auto-generated method stub
		letterPaperOrderDao = new LetterPaperOrderDaoImpl();
		try {
			boolean letterpaperorder = letterPaperOrderDao.updateStateto1(vo.getLetterPaperNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void insert(LetterPaperOrder vo) {
		// TODO Auto-generated method stub
		letterPaperOrderDao = new LetterPaperOrderDaoImpl();

		try {
//			计算邮件价格
			LetterPaperPrice lpp = new LetterPaperPriceDaoImpl().findById(1);
			if(lpp==null){
				System.out.println("为空!");
			}
			int letterpagefreeday = lpp.getLetterPageFreeDay();//获取免费的天数
			double letterpaperbaseprice = lpp.getLetterPaperBasePrice();//获取基础的价格
			double letterpaperdaypeice = lpp.getLetterPaperDayPrice();//获取每天的扣费
			System.out.println(lpp);
			Date dateofmailing = vo.getDateOfMailing(); 
			Date letterpapercreatetime = vo.getLetterPaperCreateTime();
			int betweendays = DateUtil.daysBetween(letterpapercreatetime,dateofmailing);
			System.out.println("betweendays:" + betweendays);
			double allprice =letterpaperbaseprice+(letterpaperdaypeice*(betweendays - letterpagefreeday)); 
			allprice = allprice >= 10 ? allprice : 10;//三段运算符，判断是否大于10，如果是 计算，如果不是 获取10
			
			vo.setLetterPaperPrice(allprice);
//			保存订单
			boolean letterpaperorder = letterPaperOrderDao.doInsert(vo);			
			LetterPaperOrder result  = letterPaperOrderDao.findById(vo.getLetterPaperNumber());
//			LetterPaperOrder result  = letterPaperOrderDao.findById("ae97cdc7-66d8-4d31-8e36-f4c098a71e56");
			if(result == null){
				return;
			}
			int userid = result.getUserid();
			User user = new UserDaoImpl().findById(userid);
			if(user == null){
				return ;
			}
		
			//发支付邮件
			String address = "http://localhost:8080/timepostoffice/letterpaperorder/pay?uuid=" + vo.getLetterPaperNumber() ;
			String html = "<h2>点击下面的链接完成订单支付</h2><h1><a href=\"" + address + "\" >" + address + "</a></h1>"; 
//			String html = "<a href=\"http://me.cn:8080/timepostoffice/letterpaperorder/pay?uuid=ae97cdc7-66d8-4d31-8e36-f4c098a71e56\" target=\"_blank\">点击支付</a>";
			System.out.println(result);
			MailUtil.createTimeLetter("订单支付", user.getUserEmail(), null, 
					"账单", html);
			//System.out.println("uuid:" + uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LetterPaperOrderServiceImpl ls = new LetterPaperOrderServiceImpl();
		ls.insert(null);
	}


	@Override
	public LetterPaperOrder get(Integer id) {
		LetterPaperOrder result = null;
		try{
			result  = letterPaperOrderDao.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LetterPaperOrder> list() {
		try {
			List<LetterPaperOrder> LetterPaperOrderList = letterPaperOrderDao.findAll();
			return LetterPaperOrderList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LetterPaperOrder> list(int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}






	


	


	
}
