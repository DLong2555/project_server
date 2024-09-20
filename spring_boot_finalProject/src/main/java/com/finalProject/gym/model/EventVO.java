package com.finalProject.gym.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EventVO {
	private int eventNo;
	private String eventTitle;
	private String eventImg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventDeadLine;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date eventDate;
	private String eventTitleContents;
	private String eventContents;
	private int eventPrice;
	private String memId;
	
	private String deadLine;
	private String createdAt;
	private String gymName;
	private String memName;
	private String deadLineChk;
	
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventImg() {
		return eventImg;
	}
	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}
	public Date getEventDeadLine() {
		return eventDeadLine;
	}
	public void setEventDeadLine(Date eventDeadLine) {
		this.eventDeadLine = eventDeadLine;
	}
	public String getEventTitleContents() {
		return eventTitleContents;
	}
	public void setEventTitleContents(String eventTitleContents) {
		this.eventTitleContents = eventTitleContents;
	}
	public String getEventContents() {
		return eventContents;
	}
	public void setEventContents(String eventContents) {
		this.eventContents = eventContents;
	}
	public int getEventPrice() {
		return eventPrice;
	}
	public void setEventPrice(int eventPrice) {
		this.eventPrice = eventPrice;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDeadLineChk() {
		return deadLineChk;
	}
	public void setDeadLineChk(String deadLineChk) {
		this.deadLineChk = deadLineChk;
	}
	
	
}
