package com.finalProject.gym.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.finalProject.gym.model.AwardVO;
import com.finalProject.gym.model.ChildVO;
import com.finalProject.gym.model.MemberVO;

public interface IMemberService {
	public void joinMember(MemberVO vo); //회원가입
	public String joinMemIdCheck(String id); //아이디 중복확인
	public String joinMemNickChk(String nick); //닉네임 중복확인
	public MemberVO login(HashMap<String, Object> map); //로그인
	public MemberVO getMemData(String memId); // 마이페이지 정보 가져오기
	public void updateMemImgNo(HashMap<String, Object> map); //마이페이지 이미지 업데이트
	public void myPageUpdate(MemberVO vo); // 마이페이지 업데이트
	public void memDeleteAccount(String memId); // 회원탈퇴
	public String getMemNick(String memId); // 닉네임 가져오기
	/*----------------------------------------------------------------*/
	public String gymNoChk(); // 체육관번호 중복확인
	public ArrayList<ChildVO> getMyGymChildName(String gymNo); //체육관 회원 이름 가져오기
	public ChildVO getMemDateByChildNo(String childNo); // 회원정보 가져오기
	public ArrayList<ChildVO> childNameSearch(HashMap<String, String> map); //회원 이름 검색
	public void childBeltUpdate(HashMap<String, Object> map); // 띠 수정
	public AwardVO getAwardContents(String childNo, int page); //수상내역 가져오기
	public int totalAwardPage(String childNo); //수상내역 개수 가져오기
	public void deletAward(int awardNo); // 수상내역 삭제
	public void addAwardContents(AwardVO awardvo); //수상내역 추가
	public ArrayList<Integer> getChildNoJoinEvent(int eventNo); //이벤트 참여 아이 번호 가져오기
}
