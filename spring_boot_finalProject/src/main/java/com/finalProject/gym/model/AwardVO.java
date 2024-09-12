package com.finalProject.gym.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AwardVO {
	private int awardNo;
	private String childNo;
	private String awardTitle;
	private String ranking;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date getAwardDay;
	
	private int totalPage;
	private String dateFmt; 
	
	
	public int getAwardNo() {
		return awardNo;
	}
	public void setAwardNo(int awardNo) {
		this.awardNo = awardNo;
	}
	public String getChildNo() {
		return childNo;
	}
	public void setChildNo(String childNo) {
		this.childNo = childNo;
	}
	public String getAwardTitle() {
		return awardTitle;
	}
	public void setAwardTitle(String awardTitle) {
		this.awardTitle = awardTitle;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public Date getGetAwardDay() {
		return getAwardDay;
	}
	public void setGetAwardDay(Date getAwardDay) {
		this.getAwardDay = getAwardDay;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getDateFmt() {
		return dateFmt;
	}
	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}
	
	
}
