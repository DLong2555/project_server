package com.finalProject.gym.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalProject.gym.model.ChildVO;
import com.finalProject.gym.model.MemberVO;
import com.finalProject.gym.model.OrderVO;
import com.finalProject.gym.model.PayVO;
import com.finalProject.gym.service.ChildService;
import com.finalProject.gym.service.MemberService;
import com.finalProject.gym.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChildController {

	private ChildService childService;
	private MemberService memService;
	private ProductService prdService;

	@Autowired
	public ChildController(ChildService childService, MemberService memService, ProductService prdService) {
		this.childService = childService;
		this.memService = memService;
		this.prdService = prdService;
	}

	/*
	 * @RequestMapping("/child/signUpGym") public String signUpGym(ChildVO vo,
	 * HttpSession session,
	 * 
	 * @RequestParam("childBelt") String childBelt,
	 * 
	 * @RequestParam("childHp") String childHp,
	 * 
	 * @RequestParam("gymName") String gymName) {
	 * 
	 * String memId = (String)session.getAttribute("sid"); String childName =
	 * vo.getChildName();
	 * 
	 * String childNo = childService.childNoChk(); vo.setChildNo(childNo);
	 * 
	 * String name = childService.newMemberChk(memId, childName); String hp =
	 * childHp.substring(0, 3) + "-" + childHp.substring(3, 7) + "-" +
	 * childHp.substring(7, 11); vo.setChildHp(hp);
	 * 
	 * if(childBelt == null) vo.setChildBelt("흰"); else vo.setChildBelt(childBelt);
	 * 
	 * System.out.println(name);
	 * 
	 * if(name != gymName) { vo.setGymName(gymName); vo.setMemId(memId);
	 * childService.signUpGym(vo); }
	 * 
	 * return "redirect:/"; }
	 */

	@ResponseBody
	@RequestMapping("/child/signUpChildInMyPage")
	public String signUpChildInMyPage(@RequestParam HashMap<String, Object> map, HttpSession session) {
		String memId = (String) session.getAttribute("sid");
		String hp = (String) map.get("childHp");
		hp = hp.substring(0, 3) + "-" + hp.substring(3, 7) + "-" + hp.substring(7, 11);

		map.put("memId", memId);
		map.put("childHp", hp);

		String chNo = (String) map.get("childNo");
		String no = childService.newChildChk(chNo);

		if (no == null) {
			String childNo = childService.childNoChk();
			map.put("childNo", childNo);
			map.put("gymName", "미등록");
			map.put("childBelt", "미등록");
			map.put("deadLine", "미등록");

			childService.signUpChildInMyPage(map);
		} else {
			childService.myPageChildUpdate(map);
		}

		return "success";
	}

	@ResponseBody
	@RequestMapping("/child/loadMyChildInfo")
	public ArrayList<ChildVO> loadMyChildInfo(HttpSession session) {
		String memId = (String) session.getAttribute("sid");
		ArrayList<ChildVO> childList = childService.getMyChildren(memId);

		return childList;
	}

	@ResponseBody
	@RequestMapping("/child/myPageChildDel")
	public void myPageChildDel(@RequestParam("childNo") String childNo) {

		childService.myPageChildDel(childNo);
	}

	// 회비 납부
	@ResponseBody
	@RequestMapping("/child/childRegistGym")
	public void childRegistGym(@RequestParam("childNoArr") List<String> childNoArr,
			@RequestParam("gymNameArr") List<String> gymNameArr, @RequestParam("monArr") List<Integer> monArr,
			@RequestParam("dateArr") List<String> dateArr, @RequestParam("payOption") String payOption,
			@RequestParam("ctg") String payCtg, HttpSession session) {

		// 납부 번호 생성
		int payNo = childService.payNoChk();

		// 마감일 계산
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter calFm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (int i = 0; i < childNoArr.size(); i++) {
			ChildVO chvo = childService.getAlreadyRegistGymName(childNoArr.get(i));
			chvo.setChildNo(childNoArr.get(i));

			// 시작 날짜
			String day = dateArr.get(i);
			LocalDate registDay = LocalDate.parse(day, calFm);

			// 납부 정보
			PayVO payvo = new PayVO();
			payvo.setPayNo(payNo);
			payvo.setChildNo(childNoArr.get(i));
			payvo.setPayMonth(monArr.get(i));
			payvo.setPayCtg("회비");
			payvo.setGymName(gymNameArr.get(i));
			payvo.setPayOption(payOption);

			if (chvo.getGymName().equals("미등록")) {
				String D_day = registDay.plusMonths(monArr.get(i)).format(fm) + "까지";
				chvo.setChildBelt("흰띠");
				chvo.setDeadLine(D_day);
				chvo.setGymName(gymNameArr.get(i));

				childService.registGymNewChild(chvo);
			} else if (!chvo.getGymName().equals(gymNameArr.get(i))) {
				String D_day = registDay.plusMonths(monArr.get(i)).format(fm) + "까지";
				chvo.setChildBelt(chvo.getChildBelt());
				chvo.setDeadLine(D_day);
				chvo.setGymName(gymNameArr.get(i));

				childService.registGymNewChild(chvo);

			} else {
				String deadLine = chvo.getDeadLine().substring(0, 4) + "-" + chvo.getDeadLine().substring(6, 8) + "-"
						+ chvo.getDeadLine().substring(10, 12);
				LocalDate line = LocalDate.parse(deadLine, calFm);

				// 현재가 마감일 보다 후면
				if (line.isBefore(registDay)) {
					String D_day = registDay.plusMonths(monArr.get(i)).format(fm) + "까지";
					chvo.setChildBelt(chvo.getChildBelt());
					chvo.setDeadLine(D_day);

					childService.registGymNewChild(chvo);
				} else {
					String D_day = line.plusMonths(monArr.get(i)).format(fm) + "까지";
					chvo.setChildBelt(chvo.getChildBelt());
					chvo.setDeadLine(D_day);

					childService.registGymNewChild(chvo);
				}
			}

			childService.insertPayInfo(payvo);
		}
	}

	//특수활동 납부
	@ResponseBody
	@RequestMapping("/child/childRegistGymEvent")
	public void childRegistGymEvent(@RequestParam("childNoArr") List<String> childNoArr,
			@RequestParam("gymNameArr") List<String> gymNameArr, @RequestParam("eventNoArr") List<Integer> eventNoArr,
			@RequestParam("titleArr") List<String> titleArr, @RequestParam("payOption") String payOption,
			@RequestParam("ctg") String payCtg, HttpSession session) {

		// 납부 번호 생성
		int payNo = childService.payNoChk();

		for (int i = 0; i < childNoArr.size(); i++) {			
			
			// 납부 정보
			PayVO payvo = new PayVO();
			payvo.setPayNo(payNo);
			payvo.setChildNo(childNoArr.get(i));
			payvo.setPayCtg(payCtg);
			payvo.setGymName(gymNameArr.get(i));
			payvo.setPayOption(payOption);
			payvo.setEventNo(eventNoArr.get(i));

			

			childService.insertPayInfo(payvo);
		}
	}
}
