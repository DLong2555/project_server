package com.finalProject.gym.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.gym.dao.IGalleryDAO;
import com.finalProject.gym.model.EventVO;
import com.finalProject.gym.model.GalleryVO;

@Service
public class GalleryService implements IGalleryService {
	private IGalleryDAO gallDao;
	
	@Autowired
	public GalleryService(IGalleryDAO gallDao) {
		this.gallDao = gallDao;
	}
	
	@Override
	public void insertEvent(EventVO eventvo) {		
		gallDao.insertEvent(eventvo);
	}

	@Override
	public ArrayList<EventVO> getEventContentsWhenMem(ArrayList<String> gymNameList, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		return gallDao.getEventContentsWhenMem(gymNameList, offset, pageSize);
	}

	@Override
	public int getEventTotalPage(ArrayList<String> gymNameList) {	
		return gallDao.getEventTotalPage(gymNameList);
	}

	@Override
	public ArrayList<EventVO> getEventContentsWhenAdmin(String memId, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		return gallDao.getEventContentsWhenAdmin(memId, offset, pageSize);
	}

	@Override
	public int getEventTotalPageWhenAdmin(String memId) {
		return gallDao.getEventTotalPageWhenAdmin(memId);
	}

	@Override
	public EventVO getEventDetailContent(int eventNo) {
		return gallDao.getEventDetailContent(eventNo);
	}

	@Override
	public void deleteEventContent(int eventNo) {
		gallDao.deleteEventContent(eventNo);
	}

	@Override
	public void updateEventContent(EventVO eventvo) {
		gallDao.updateEventContent(eventvo);
	}

	@Override
	public EventVO getEventPayInfo(int eventNo) {
		return gallDao.getEventPayInfo(eventNo);
	}

	@Override
	public void insertGalleryContents(GalleryVO gallVO) {
		gallDao.insertGalleryContents(gallVO);
	}

	@Override
	public ArrayList<GalleryVO> getGallContentsWhenMem(ArrayList<String> gymNameList, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		return gallDao.getGallContentsWhenMem(gymNameList, offset, pageSize);
	}

	@Override
	public ArrayList<GalleryVO> getGallContentsWhenAdmin(String memId, int page, int pageSize) {
		int offset = (page - 1) * pageSize;
		return gallDao.getGallContentsWhenAdmin(memId, offset, pageSize);
	}

	@Override
	public int getGallTotalPage(ArrayList<String> gymNameList) {
		return gallDao.getGallTotalPage(gymNameList);
	}

	@Override
	public int getGallTotalPageWhenAdmin(String memId) {
		return gallDao.getGallTotalPageWhenAdmin(memId);
	}

	@Override
	public GalleryVO getGallDetailContent(int galleryNo) {
		return gallDao.getGallDetailContent(galleryNo);
	}

	@Override
	public void deleteGallContent(int galleryNo) {
		gallDao.deleteGallContent(galleryNo);	
	}

	@Override
	public void updateGallContent(GalleryVO gallVO) {
		gallDao.updateGallContent(gallVO);		
	}

	@Override
	public ArrayList<EventVO> getEventDataToManageForm(String memId) {
		return gallDao.getEventDataToManageForm(memId);
	}
	
	
	

}
