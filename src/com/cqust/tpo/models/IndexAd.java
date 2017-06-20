package com.cqust.tpo.models;

/**
 * 首页广告实体对象
 * @author 董继军
 * @date 2016/9/26
 */
import java.util.Date;

public class IndexAd {
	
	private int indexAdId;//广告id
	private String indexAdTitle;//广告标题
	private String indexAdContent;//广告内容
	private Date indexAdTime;//广告创建时间
	public IndexAd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IndexAd(int indexAdId, String indexAdTitle, String indexAdContent, Date indexAdTime) {
		super();
		this.indexAdId = indexAdId;
		this.indexAdTitle = indexAdTitle;
		this.indexAdContent = indexAdContent;
		this.indexAdTime = indexAdTime;
	}
	public int getIndexAdId() {
		return indexAdId;
	}
	public void setIndexAdId(int indexAdId) {
		this.indexAdId = indexAdId;
	}
	public String getIndexAdTitle() {
		return indexAdTitle;
	}
	public void setIndexAdTitle(String indexAdTitle) {
		this.indexAdTitle = indexAdTitle;
	}
	public String getIndexAdContent() {
		return indexAdContent;
	}
	public void setIndexAdContent(String indexAdContent) {
		this.indexAdContent = indexAdContent;
	}
	public Date getIndexAdTime() {
		return indexAdTime;
	}
	public void setIndexAdTime(Date indexAdTime) {
		this.indexAdTime = indexAdTime;
	}
	@Override
	public String toString() {
		return "IndexAd [indexAdId=" + indexAdId + ", indexAdTitle=" + indexAdTitle + ", indexAdContent="
				+ indexAdContent + ", indexAdTime=" + indexAdTime + "]";
	}
	
	
}
