package com.finalProject.gym.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.finalProject.gym.model.ChildVO;
import com.finalProject.gym.model.PayVO;

public interface IChildDAO {
	public ArrayList<ChildVO> getMyChildren(String memId); // 아이디에 등록된 관원 가져오기
	public void signUpGym(ChildVO vo); // 관원 등록
	public String newMemberChk(String memId, String childName); // 신규회원인지 확인
	public String childNoChk(String no); // chlidNo 중복체크
	public void memDeleteAccount(String memId); // 회원탈퇴
	public void signUpChildInMyPage(HashMap<String, Object> map); // 마이페이지에서 자식 등록
	public String newChildChk(String childNo); // 신규자식인지 확인
	public void myPageChildDel(String childNo); // 마이페이지 자식 삭제
	public void myPageChildUpdate(HashMap<String, Object> map); // 기존 자식 정보 업데이트
	public ChildVO getAlreadyRegistGymName(String childNo); //이미 도장이 등록 되어 있는지 확인
	public void registGymNewChild(ChildVO chvo); // 도장 신규 등록
	public ArrayList<String> getMyGymName(String memId); // 다니고 있는 도장가져오기
	public boolean payNoChk(int payNo); //납부번호 중복확인
	public void insertPayInfo(PayVO payvo); //납부 정보 등록
	public ArrayList<PayVO> getPayInfo(String memId); //납부 정보 가져오기
}
