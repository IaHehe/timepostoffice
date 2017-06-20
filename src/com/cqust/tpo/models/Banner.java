package com.cqust.tpo.models;

/**
 * Banner实体对象
 * @author 杨秀仕
 * @date 2016/09/26
 */

public class Banner {
	private int bannerId; //bannerId
	private String isBannerDisplay; //banner是否显示
	
	public Banner()	{
		
	}
	
	public Banner(int bannerId,String isBannerDisplay)	{
		this.bannerId = bannerId;
		this.isBannerDisplay = isBannerDisplay;
	}
	
	public int getBannerId() {
		return bannerId;
	}
	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}
	public String getIsBannerDisplay() {
		return isBannerDisplay;
	}
	public void setIsBannerDisplay(String isBannerDisplay) {
		this.isBannerDisplay = isBannerDisplay;
	}

	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", isBannerDisplay="
				+ isBannerDisplay + "]";
	}
	
}
