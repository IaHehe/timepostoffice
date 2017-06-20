package com.cqust.tpo.models;
/**
 * 管理员实体对象
 * @author 向航延
 * @date 2016/9/26
 */
public class Manager {
	
	private	int ManagerId;//管理员id
	private String ManagerAccount;//管理员账号
	private String ManagerPassword;//管理员密码
	
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public String getManagerAccount() {
		return ManagerAccount;
	}
	public void setManagerAccount(String managerAccount) {
		ManagerAccount = managerAccount;
	}
	public String getManagerPassword() {
		return ManagerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		ManagerPassword = managerPassword;
	}
	@Override
	public String toString() {
		return "Manager [ManagerId=" + ManagerId + ", ManagerAccount=" + ManagerAccount + ", ManagerPassword="
				+ ManagerPassword + "]";
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(int managerId, String managerAccount, String managerPassword) {
		super();
		ManagerId = managerId;
		ManagerAccount = managerAccount;
		ManagerPassword = managerPassword;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
