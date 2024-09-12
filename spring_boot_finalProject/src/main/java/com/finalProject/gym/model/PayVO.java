package com.finalProject.gym.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PayVO {
	private int payNo;
	private String childNo;
	private int payMonth;
	private String payCtg;
	private String gymName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	private String payOption;
	
	private String childName;
	private int gymPrice;
	private String payDateFmt;
	private ArrayList<PayVO> payList;
	
	public int getPayNo() {
		return payNo;
	}
	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}
	public String getChildNo() {
		return childNo;
	}
	public void setChildNo(String childNo) {
		this.childNo = childNo;
	}
	public int getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(int payMonth) {
		this.payMonth = payMonth;
	}
	public String getPayCtg() {
		return payCtg;
	}
	public void setPayCtg(String payCtg) {
		this.payCtg = payCtg;
	}
	public String getGymName() {
		return gymName;
	}
	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public int getGymPrice() {
		return gymPrice;
	}
	public void setGymPrice(int gymPrice) {
		this.gymPrice = gymPrice;
	}
	public String getPayDateFmt() {
		return payDateFmt;
	}
	public void setPayDateFmt(String payDateFmt) {
		this.payDateFmt = payDateFmt;
	}
	public ArrayList<PayVO> getPayList() {
		return payList;
	}
	public void setPayList(ArrayList<PayVO> payList) {
		this.payList = payList;
	}
	public String getPayOption() {
		return payOption;
	}
	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}
	
	
	
}
