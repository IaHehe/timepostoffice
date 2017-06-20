package com.cqust.tpo.models;
/**
 * Copyright © 2015. by Tengyu Ma 
 * 文件名: LetterPaperOrder.java <br/>
 * 包名: com.cqust.tpo.models <br/>
 * 创建时间: 2016年9月26日 上午11:46:38 
 * @author 马腾宇 Tengyu Ma  mty2015@126.com 
 * @version V1.0.0  
 */

import java.util.Date;

/** 
 * 类名: LetterPaperOrder <br>
 * 描述: 纸信订单Bean <br>
 * 创建时间: 2016年9月26日 上午11:46:38 
 * @author 马腾宇  Tengyu Ma   mty2015@126.com 
 * @version 1.0.0
 * @since 1.6 
 */
public class LetterPaperOrder {

	/** 
	 * letterPaperId :纸信订单id,唯一标识
	 */
	private Integer letterPaperId;
	
	/** 
	 * letterPaperNumber : 纸信编号
	 */
	private String letterPaperNumber;
	
	/** 
	 * recipientName : 收信人姓名 
	 */
	private String letterPaperName;
	
	/** 
	 * recipientAddress : 收信人地址 
	 */
	private String letterPaperAddress;
	
	/** 
	 * recipientPostNumber : 收信人邮编
	 */
	private String letterPaperPostNumber;
	
	/** 
	 * recipientPhone : 收信人电话 
	 */
	private String letterPaperPhone;
	
	/** 
	 * dateOfMailing : 寄出日期	
	 */
	private Date dateOfMailing;
	
	/** 
	 * customerName :客户姓名
	 */
	private String customerName;
	
	/** 
	 * customerPhone :客户电话	
	 */
	private String customerPhone;
	
	/** 
	 * customerQQ :客户QQ	
	 */
	private String customerQQ;
	
	/** 
	 * letterPaperPrice : 订单价格
	 */
	private Double letterPaperPrice;
	/** 
	 * letterPaperState : 纸信状态
	 * DBDesc:0表示初始态、1等待接收、2保管中、3作废、4已寄出，默认值0（插入记录时）
	 */
	private String letterPaperState;

	/** 
	 * isLetterPaperUpdate : 纸信信息是否允许修改
	 * DBDesc:纸信收信人信息是否允许修改,Y表示可以，N表示不可以，默认Y
	 */
	private String isLetterPaperUpdate;
	/** 
	 * letterPaperCreateTime :纸信创建时间
	 * DBDesc:纸信创建时间，系统的当前时间
	 */
	private Date letterPaperCreateTime;
	/** 
	 * orderState :订单状态
	 * DBDesc:0表示未付款、1已付款、2取消、3完成，默认0
	 */
	private String orderState;

	
	/**
	 * 用户id
	 */
	private Integer userid;
	/**
	 * LetterPaperOrder无参构造方法
	 */
//	public LetterPaperOrder(Integer letterPaperId, String letterPaperNumber, String recipientName,
//			String recipientAddress, String recipientPostNumber, String recipientPhone, Date dateOfMailing,
//			String customerName, String customerPhone, String customerQQ, Double letterPaperPrice,
//			String letterPaperState, String isLetterPaperUpdate, Date letterPaperCreateTime, String orderState,
//			Integer userid) {
//		super();
//		this.letterPaperId = letterPaperId;
//		this.letterPaperNumber = letterPaperNumber;
////		this.recipientName = recipientName;
////		this.recipientAddress = recipientAddress;
////		this.recipientPostNumber = recipientPostNumber;
////		this.recipientPhone = recipientPhone;
//		this.dateOfMailing = dateOfMailing;
//		this.customerName = customerName;
//		this.customerPhone = customerPhone;
//		this.customerQQ = customerQQ;
//		this.letterPaperPrice = letterPaperPrice;
//		this.letterPaperState = letterPaperState;
//		this.isLetterPaperUpdate = isLetterPaperUpdate;
//		this.letterPaperCreateTime = letterPaperCreateTime;
//		this.orderState = orderState;
//		this.userid = userid;
//	}
//
//	public LetterPaperOrder(){
//	}
//
//	/**
//	 * 带参构造方法.
//	 * 传入一封纸信的基本字段信息，构造一个LetterPaperOrder对象。
//	 * 没有的字段：
//	 * letterPaperId,letterPaperNumber,letterPaperState,isLetterPaperUpdate,letterPaperCreateTime,orderState.
//	 * 
//	 * <b>工作原理</b>
//	 * 调用完全构造方法，没有的字段使用 null.
//	 * 
//	 * @param recipientName  收信人姓名
//	 * @param recipientAddress  收信人地址
//	 * @param recipientPostNumber  收信人邮编
//	 * @param recipientPhone  收信人电话
//	 * @param dateOfMailing  寄出日期
//	 * @param customerName  客户姓名
//	 * @param customerPhone  客户电话
//	 * @param customerQQ  客户QQ
//	 * @param letterPaperPrice  订单价格
//	 * 
//	 * @see LetterPaperOrder#LetterPaperOrder(Integer, String, String, String, String, String, Date, String, String, String, Double, String, String, Date, String)
//	 */
//	public LetterPaperOrder(String recipientName,
//			String recipientAddress, String recipientPostNumber, String recipientPhone, Date dateOfMailing,
//			String customerName, String customerPhone, String customerQQ, Double letterPaperPrice) {
//		this(null, null, recipientName, recipientAddress, recipientPostNumber, recipientPhone, dateOfMailing, customerName, customerPhone, customerQQ, letterPaperPrice, null, null, null, null);
//	}
//
//	/**
//	 * 纸信订单构造方法.
//	 * 包含所有字段.
//	 * @param letterPaperId  纸信订单ID
//	 * @param letterPaperNumber  纸信编号
//	 * @param recipientName  收信人姓名
//	 * @param recipientAddress  收信人地址
//	 * @param recipientPostNumber  收信人邮编
//	 * @param recipientPhone  收信人电话
//	 * @param dateOfMailing  寄出日期
//	 * @param customerName  客户姓名
//	 * @param customerPhone  客户电话
//	 * @param customerQQ  客户QQ
//	 * @param letterPaperPrice  订单价格
//	 * @param letterPaperState  纸信状态
//	 * @param isLetterPaperUpdate  纸信信息是否允许修改
//	 * @param letterPaperCreateTime  纸信创建时间
//	 * @param orderState   订单状态
//	 */
//	public LetterPaperOrder(Integer letterPaperId, String letterPaperNumber, String recipientName,
//			String recipientAddress, String recipientPostNumber, String recipientPhone, Date dateOfMailing,
//			String customerName, String customerPhone, String customerQQ, Double letterPaperPrice,
//			String letterPaperState, String isLetterPaperUpdate, Date letterPaperCreateTime, String orderState) {
//		super();
//		this.letterPaperId = letterPaperId;
//		this.letterPaperNumber = letterPaperNumber;
//		this.recipientName = recipientName;
//		this.recipientAddress = recipientAddress;
//		this.recipientPostNumber = recipientPostNumber;
//		this.recipientPhone = recipientPhone;
//		this.dateOfMailing = dateOfMailing;
//		this.customerName = customerName;
//		this.customerPhone = customerPhone;
//		this.customerQQ = customerQQ;
//		this.letterPaperPrice = letterPaperPrice;
//		this.letterPaperState = letterPaperState;
//		this.isLetterPaperUpdate = isLetterPaperUpdate;
//		this.letterPaperCreateTime = letterPaperCreateTime;
//		this.orderState = orderState;
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "LetterPaperOrder [letterPaperId=" + letterPaperId + ", letterPaperNumber=" + letterPaperNumber
//				+ ", recipientName=" + recipientName + ", recipientAddress=" + recipientAddress
//				+ ", recipientPostNumber=" + recipientPostNumber + ", recipientPhone=" + recipientPhone
//				+ ", dateOfMailing=" + dateOfMailing + ", customerName=" + customerName + ", customerPhone="
//				+ customerPhone + ", customerQQ=" + customerQQ + ", letterPaperPrice=" + letterPaperPrice
//				+ ", letterPaperState=" + letterPaperState + ", isLetterPaperUpdate=" + isLetterPaperUpdate
//				+ ", letterPaperCreateTime=" + letterPaperCreateTime + ", orderState=" + orderState + ", userid="
//				+ userid + "]";
//	}

	/**
	 * @return 获取用户id
	 */
	public Integer getUserid() {
		return userid;
	}

	/**
	 * @return 获取  letterPaperId 值/对象
	 */
	public Integer getLetterPaperId() {
		return letterPaperId;
	}

	/**
	 * @return 获取  letterPaperNumber 值/对象
	 */
	public String getLetterPaperNumber() {
		return letterPaperNumber;
	}

	/**
	 * @return 获取  recipientName 值/对象
	 */


	/**
	 * @return 获取  dateOfMailing 值/对象
	 */
	public Date getDateOfMailing() {
		return dateOfMailing;
	}

	/**
	 * @return 获取  customerName 值/对象
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return 获取  customerPhone 值/对象
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}

	/**
	 * @return 获取  customerQQ 值/对象
	 */
	public String getCustomerQQ() {
		return customerQQ;
	}

	/**
	 * @return 获取  letterPaperPrice 值/对象
	 */
	public Double getLetterPaperPrice() {
		return letterPaperPrice;
	}

	/**
	 * @return 获取  letterPaperState 值/对象
	 */
	public String getLetterPaperState() {
		return letterPaperState;
	}

	/**
	 * @return 获取  isLetterPaperUpdate 值/对象
	 */
	public String getIsLetterPaperUpdate() {
		return isLetterPaperUpdate;
	}

	/**
	 * @return 获取  letterPaperCreateTime 值/对象
	 */
	public Date getLetterPaperCreateTime() {
		return letterPaperCreateTime;
	}

	
	public LetterPaperOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LetterPaperOrder [letterPaperId=" + letterPaperId + ", letterPaperNumber=" + letterPaperNumber
				+ ", letterPaperName=" + letterPaperName + ", letterPaperAddress=" + letterPaperAddress
				+ ", letterPaperPostNumber=" + letterPaperPostNumber + ", letterPaperPhone=" + letterPaperPhone
				+ ", dateOfMailing=" + dateOfMailing + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerQQ=" + customerQQ + ", letterPaperPrice=" + letterPaperPrice
				+ ", letterPaperState=" + letterPaperState + ", isLetterPaperUpdate=" + isLetterPaperUpdate
				+ ", letterPaperCreateTime=" + letterPaperCreateTime + ", orderState=" + orderState + ", userid="
				+ userid + "]";
	}

	public String getLetterPaperName() {
		return letterPaperName;
	}

	public void setLetterPaperName(String letterPaperName) {
		this.letterPaperName = letterPaperName;
	}

	public String getLetterPaperAddress() {
		return letterPaperAddress;
	}

	public void setLetterPaperAddress(String letterPaperAddress) {
		this.letterPaperAddress = letterPaperAddress;
	}

	public String getLetterPaperPostNumber() {
		return letterPaperPostNumber;
	}

	public void setLetterPaperPostNumber(String letterPaperPostNumber) {
		this.letterPaperPostNumber = letterPaperPostNumber;
	}

	public String getLetterPaperPhone() {
		return letterPaperPhone;
	}

	public void setLetterPaperPhone(String letterPaperPhone) {
		this.letterPaperPhone = letterPaperPhone;
	}

	/**
	 * @return 获取  orderState 值/对象
	 */
	public String getOrderState() {
		return orderState;
	}

	/**
	 * @param userid 用户id
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	/**
	 * @param letterPaperId 设置 letterPaperId 值/对象
	 */
	public void setLetterPaperId(Integer letterPaperId) {
		this.letterPaperId = letterPaperId;
	}

	/**
	 * @param letterPaperNumber 设置 letterPaperNumber 值/对象
	 */
	public void setLetterPaperNumber(String letterPaperNumber) {
		this.letterPaperNumber = letterPaperNumber;
	}

	/**
	 * @param recipientName 设置 recipientName 值/对象
	 */
	
	/**
	 * @param dateOfMailing 设置 dateOfMailing 值/对象
	 */
	public void setDateOfMailing(Date dateOfMailing) {
		this.dateOfMailing = dateOfMailing;
	}

	/**
	 * @param customerName 设置 customerName 值/对象
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @param customerPhone 设置 customerPhone 值/对象
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * @param customerQQ 设置 customerQQ 值/对象
	 */
	public void setCustomerQQ(String customerQQ) {
		this.customerQQ = customerQQ;
	}

	/**
	 * @param letterPaperPrice 设置 letterPaperPrice 值/对象
	 */
	public void setLetterPaperPrice(Double letterPaperPrice) {
		this.letterPaperPrice = letterPaperPrice;
	}

	/**
	 * @param letterPaperState 设置 letterPaperState 值/对象
	 */
	public void setLetterPaperState(String letterPaperState) {
		this.letterPaperState = letterPaperState;
	}

	/**
	 * @param isLetterPaperUpdate 设置 isLetterPaperUpdate 值/对象
	 */
	public void setIsLetterPaperUpdate(String isLetterPaperUpdate) {
		this.isLetterPaperUpdate = isLetterPaperUpdate;
	}

	/**
	 * @param letterPaperCreateTime 设置 letterPaperCreateTime 值/对象
	 */
	public void setLetterPaperCreateTime(Date letterPaperCreateTime) {
		this.letterPaperCreateTime = letterPaperCreateTime;
	}

	/**
	 * @param orderState 设置 orderState 值/对象
	 */
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
}
