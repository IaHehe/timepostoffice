package com.cqust.tpo.models;

/**
 * 联系我们实体对象
 * @author 董继军
 * @date 2016/9/26
 */
public class ContactUs {
	
	private int contactUsId;//联系我们id
	private String contactUsEmail;//电子信箱
	private String contactUsPhone;//联系电话
	private String contactUsAddress;//联系地址
	
	public ContactUs() {
	}
	
	public ContactUs(int contactUsId, String contactUsEmail, String contactUsPhone, String contactUsAddress) {
		super();
		this.contactUsId = contactUsId;
		this.contactUsEmail = contactUsEmail;
		this.contactUsPhone = contactUsPhone;
		this.contactUsAddress = contactUsAddress;
	}
	
	public int getContactUsId() {
		return contactUsId;
	}
	public void setContactUsId(int contactUsId) {
		this.contactUsId = contactUsId;
	}
	public String getContactUsEmail() {
		return contactUsEmail;
	}
	public void setContactUsEmail(String contactUsEmail) {
		this.contactUsEmail = contactUsEmail;
	}
	public String getContactUsPhone() {
		return contactUsPhone;
	}
	public void setContactUsPhone(String contactUsPhone) {
		this.contactUsPhone = contactUsPhone;
	}
	public String getContactUsAddress() {
		return contactUsAddress;
	}
	public void setContactUsAddress(String contactUsAddress) {
		this.contactUsAddress = contactUsAddress;
	}

	@Override
	public String toString() {
		return "ContactUs [contactUsId=" + contactUsId + ", contactUsEmail=" + contactUsEmail + ", contactUsPhone="
				+ contactUsPhone + ", contactUsAddress=" + contactUsAddress + "]";
	}

	
	
	
	
}
