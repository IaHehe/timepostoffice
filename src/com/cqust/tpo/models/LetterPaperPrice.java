package com.cqust.tpo.models;

/**
 * 纸信价目实体对象
 * @author 向航延
 * @date 2016/9/26
 */
public class LetterPaperPrice {
	
	private int LetterPaperPriceId;//纸信价目表id
	private double LetterPaperBasePrice;//纸信价目表基本费用
	private double LetterPaperDayPrice;//纸信日保管费
	private int LetterPageFreeDay;//纸信免费天数
	
	public LetterPaperPrice() {
		super();
	}
	public int getLetterPaperPriceId() {
		return LetterPaperPriceId;
	}
	public void setLetterPaperPriceId(int letterPaperPriceId) {
		LetterPaperPriceId = letterPaperPriceId;
	}
	public double getLetterPaperBasePrice() {
		return LetterPaperBasePrice;
	}
	public void setLetterPaperBasePrice(double letterPaperBasePrice) {
		LetterPaperBasePrice = letterPaperBasePrice;
	}
	public double getLetterPaperDayPrice() {
		return LetterPaperDayPrice;
	}
	public void setLetterPaperDayPrice(double letterPaperDayPrice) {
		LetterPaperDayPrice = letterPaperDayPrice;
	}
	public int getLetterPageFreeDay() {
		return LetterPageFreeDay;
	}
	public void setLetterPageFreeDay(int letterPageFreeDay) {
		LetterPageFreeDay = letterPageFreeDay;
	}
	@Override
	public String toString() {
		return "LetterPaperPrice [LetterPaperPriceId=" + LetterPaperPriceId + ", LetterPaperBasePrice="
				+ LetterPaperBasePrice + ", LetterPaperDayPrice=" + LetterPaperDayPrice + ", LetterPageFreeDay="
				+ LetterPageFreeDay + "]";
	}
	public LetterPaperPrice(int letterPaperPriceId, double letterPaperBasePrice, double letterPaperDayPrice,
			int letterPageFreeDay) {
		super();
		LetterPaperPriceId = letterPaperPriceId;
		LetterPaperBasePrice = letterPaperBasePrice;
		LetterPaperDayPrice = letterPaperDayPrice;
		LetterPageFreeDay = letterPageFreeDay;
	}
	
	
	
	
	
}
