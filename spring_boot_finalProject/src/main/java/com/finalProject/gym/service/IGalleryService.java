package com.finalProject.gym.service;

import java.util.ArrayList;

import com.finalProject.gym.model.EventVO;
import com.finalProject.gym.model.GalleryVO;

public interface IGalleryService {
	public void insertEvent(EventVO eventvo); // 특수활동 공고 등록
	public ArrayList<EventVO> getEventContentsWhenMem(ArrayList<String> gymNameList, int page, int pageSize); // 특수활동 공고 가져오기
	public int getEventTotalPage(ArrayList<String> gymNameList); //총 게시글 개수 가져오기
	public ArrayList<EventVO> getEventContentsWhenAdmin(String memId, int page, int pageSize); // 특수활동 공고 가져오기
	public int getEventTotalPageWhenAdmin(String memId); //총 게시글 개수 가져오기
	public EventVO getEventDetailContent(int eventNo); //특수활동 공고 상세보기
	public void deleteEventContent(int eventNo); // 특수활동 공고 삭제
	public void updateEventContent(EventVO eventvo); //특수활동 공고 수정
	public EventVO getEventPayInfo(int eventNo); //특수활동 납부 내역 가져오기
	public void insertGalleryContents(GalleryVO gallVO); //갤러리 글작성
	public ArrayList<GalleryVO> getGallContentsWhenMem(ArrayList<String> gymNameList, int page, int pageSize); // 갤러리 가져오기 - 회원
	public ArrayList<GalleryVO> getGallContentsWhenAdmin(String memId, int page, int pageSize); // 갤러리 가져오기 - 관장
	public int getGallTotalPage(ArrayList<String> gymNameList); //총 게시글 개수 가져오기 - 갤러리
	public int getGallTotalPageWhenAdmin(String memId); //총 게시글 개수 가져오기 - 갤러리
	public GalleryVO getGallDetailContent(int galleryNo); //갤러리 상세보기
	public void deleteGallContent(int galleryNo); // 갤러리 글 삭제
	public void updateGallContent(GalleryVO gallVO); //갤러리 글 수정
	public ArrayList<EventVO> getEventDataToManageForm(String memId); //이벤트 정보 회원관리로 가져오기
}
