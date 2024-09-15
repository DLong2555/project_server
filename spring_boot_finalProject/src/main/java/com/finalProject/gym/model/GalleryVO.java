package com.finalProject.gym.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GalleryVO {
	private int galleryNo;
	private String galleryTitle;
	private String galleryImg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date galleryDate;
	private String galleryContents;
	private String memId;
	
	private String registDate;
	private String memName;
	private String gymName;

	public int getGalleryNo() {
		return galleryNo;
	}

	public void setGalleryNo(int galleryNo) {
		this.galleryNo = galleryNo;
	}

	public String getGalleryTitle() {
		return galleryTitle;
	}

	public void setGalleryTitle(String galleryTitle) {
		this.galleryTitle = galleryTitle;
	}

	public String getGalleryImg() {
		return galleryImg;
	}

	public void setGalleryImg(String galleryImg) {
		this.galleryImg = galleryImg;
	}

	public Date getGalleryDate() {
		return galleryDate;
	}

	public void setGalleryDate(Date galleryDate) {
		this.galleryDate = galleryDate;
	}

	public String getGalleryContents() {
		return galleryContents;
	}

	public void setGalleryContents(String galleryContents) {
		this.galleryContents = galleryContents;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}
	
	
}
