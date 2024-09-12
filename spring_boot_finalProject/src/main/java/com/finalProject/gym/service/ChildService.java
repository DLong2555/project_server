package com.finalProject.gym.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.gym.dao.IChildDAO;
import com.finalProject.gym.model.ChildVO;
import com.finalProject.gym.model.PayVO;

@Service
public class ChildService implements IChildService {
	@Autowired
	IChildDAO dao;
	
	@Override
	public ArrayList<ChildVO> getMyChildren(String memId) {
		return dao.getMyChildren(memId);
	}

	@Override
	public void signUpGym(ChildVO vo) {
		dao.signUpGym(vo);
	}

	@Override
	public String newMemberChk(String memId, String childName) {
		return dao.newMemberChk(memId, childName);
	}

	@Override
	public void memDeleteAccount(String memId) {
		dao.memDeleteAccount(memId);
	}

	@Override
	public String childNoChk() {
		Random random = new Random();
		String childNo = "";
		
		while(true) {
			String no = random.nextInt(10000) + "";
			childNo = dao.childNoChk(no);
			
			if(childNo == null) {
				childNo = no;
				break;
			}
		}
		
		return childNo;
	}

	@Override
	public void signUpChildInMyPage(HashMap<String, Object> map) {
		dao.signUpChildInMyPage(map);
	}
	
	@Override
	public String newChildChk(String childNo) {
		String name = dao.newChildChk(childNo);
		
		return name;
	}

	@Override
	public void myPageChildDel(String childNo) {
		dao.myPageChildDel(childNo);		
	}

	@Override
	public void myPageChildUpdate(HashMap<String, Object> map) {
		dao.myPageChildUpdate(map);
	}

	@Override
	public ChildVO getAlreadyRegistGymName(String childNo) {
		return dao.getAlreadyRegistGymName(childNo);
	}

	@Override
	public void registGymNewChild(ChildVO chvo) {
		dao.registGymNewChild(chvo);
	}

	@Override
	public ArrayList<String> getMyGymName(String memId) {
		return dao.getMyGymName(memId);
	}

	@Override
	public int payNoChk() {
		Random random = new Random();
		boolean chk = true; 
		int payNo = -1;
		
		while(chk) {
			payNo = random.nextInt(10000);
			chk = dao.payNoChk(payNo);
		}
		
		return payNo;
	}

	@Override
	public void insertPayInfo(PayVO payvo) {
		dao.insertPayInfo(payvo);
	}

	@Override
	public ArrayList<PayVO> getPayInfo(String memId) {
		return dao.getPayInfo(memId);
	}
	

}
