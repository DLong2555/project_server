package com.finalProject.gym.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.finalProject.gym.model.AwardVO;
import com.finalProject.gym.model.ChildVO;
import com.finalProject.gym.model.EventVO;
import com.finalProject.gym.model.GreenEyeVO;
import com.finalProject.gym.model.MemberVO;
import com.finalProject.gym.service.ChildService;
import com.finalProject.gym.service.GalleryService;
import com.finalProject.gym.service.GreenEyeService;
import com.finalProject.gym.service.GymService;
import com.finalProject.gym.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private MemberService memService;
	//private GymService gymService;
	private ChildService childService;
	private GalleryService gallService;
	private GreenEyeService greenService;
	
	@Autowired
	public MainController(MemberService memService, ChildService childService,
						  GalleryService gallService, GreenEyeService greenService) {
		this.memService = memService;
		this.childService = childService;
		//this.gymService = gymService;
		this.gallService = gallService;
		this.greenService = greenService;
	}
	

	// 메인페이지 이동
	@RequestMapping("/")
	public String main() {
		return "main";
	}

	// top에 유저 정보
	@ResponseBody
	@RequestMapping("/top")
	public HashMap<String, String> top(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");
		MemberVO vo = memService.getMemData(memId);
		HashMap<String, String> map = new HashMap<String, String>();

		if (vo != null) {
			map.put("loginChk", "login");
			map.put("memNick", vo.getMemNick());
			map.put("memImgNo", vo.getMemImgNo());
			map.put("memEmail", vo.getMemEmail());
		}

		return map;
	}

	// 회원가입 페이지 이동
	@RequestMapping("/member/joinForm")
	public String joinForm() {
		return "member/joinForm";
	}

	// 일반 회원가입
	@RequestMapping("/member/join")
	public String join(MemberVO vo, @RequestParam("memHp") String memHp) {

		String memImgNo = "basic.png";
		String hp = memHp.substring(0, 3) + "-" + memHp.substring(3, 7) + "-" + memHp.substring(7, 11);
		vo.setMemHp(hp);
		vo.setMemImgNo(memImgNo);

		memService.joinMember(vo);
		return "redirect:/member/loginForm";
	}

	// 업주 회원가입
	@RequestMapping("/gym/insertGymOwner")
	public String insertGymOwner(MemberVO vo, @RequestParam("memHp") String memHp,
			@RequestParam("gymHp") String gymHp) {

		String memImgNo = "basic.png";
		String hp = memHp.substring(0, 3) + "-" + memHp.substring(3, 7) + "-" + memHp.substring(7, 11);
		String ghp = gymHp.substring(0, 3) + "-" + gymHp.substring(3, 6) + "-" + gymHp.substring(6, 10);

		vo.setMemImgNo(memImgNo);
		vo.setMemHp(hp);
		vo.setGymHp(ghp);

		String gymNo = memService.gymNoChk();
		vo.setGymNo(gymNo);

		memService.joinMember(vo);

		return "redirect:/member/loginForm";
	}

	// 회원가입 아이디 중복확인
	@RequestMapping("/member/joinMemIdChk")
	@ResponseBody
	public boolean joinMemIdChk(@RequestParam("memId") String memId) {
		boolean result = false;
		String id_mem = memService.joinMemIdCheck(memId);

		if (id_mem != null)
			result = true;

		return result;
	}

	// 회원가입 닉네임 중복확인
	@RequestMapping("/member/nickChk")
	@ResponseBody
	public boolean joinMemNickChk(@RequestParam("nick") String nick) {
		boolean result = false;
		String pattern1 = "^(?=.*[ㄱ-ㅎ가-힣])[가-힣a-zA-Z*\\d*]{3,8}$";
		String pattern2 = "^(?=.*[a-zA-Z])[a-zA-Zㄱ-ㅎ가-힣*\\d*]{3,8}$";

		String memNick = memService.joinMemNickChk(nick);

		if (memNick != null)
			result = true;
		else {
			if (Pattern.matches(pattern1, nick) || Pattern.matches(pattern2, nick))
				result = false;
			else
				result = true;
		}

		return result;
	}

	// 회원가입 선택
	@RequestMapping("/member/joinChoose")
	public String joinChoose() {
		return "member/joinChoose";
	}

	// 로그인 페이지
	@RequestMapping("/member/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	// 로그인
	@ResponseBody
	@RequestMapping("/member/login")
	public boolean login(@RequestParam HashMap<String, Object> map, HttpSession session) {
		MemberVO vo = memService.login(map);
		boolean result = false;

		if (vo != null) {
			session.setAttribute("sid", map.get("memId"));
			session.setAttribute("sidNick", vo.getMemNick());
			if (vo.getGymNo() != null) {
				session.setAttribute("sidGymNo", vo.getGymNo());
			}

			result = true;
		}

		return result;
	}

	// 로그아웃
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 마이페이지
	@RequestMapping("/member/myPageForm")
	public String myPageForm(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");
		MemberVO vo = memService.getMemData(memId);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 M월 d일(E) HH시 mm분 ss초", Locale.KOREAN);

		if (vo != null) {
			Date joinDate = vo.getMemJoinDate(); // Date 객체로 가정
			String formattedJoinDate = formatter.format(joinDate);

			model.addAttribute("vo", vo);
			model.addAttribute("date", formattedJoinDate);
		}

		return "member/myPageForm";
	}

	// 마이페이지 등록정보
	@RequestMapping("/member/myPageChildInfo")
	public String myPageChildInfo(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");
		MemberVO vo = memService.getMemData(memId);

		if (vo != null) {
			model.addAttribute("vo", vo);
		}

		return "member/myPageChildInfo";
	}

	// 마이 페이지 회원관리
	@RequestMapping("/member/memberManageForm")
	public String memberMangeForm(@RequestParam(value = "ctg", required = false, defaultValue = "회원정보") String ctg,
			                      @RequestParam(value = "eventNo", required = false) String no, 
								  HttpSession session, Model model) {

		String memId = (String) session.getAttribute("sid");
		String gymNo = (String) session.getAttribute("sidGymNo");
		
		DateTimeFormatter calFm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();

		ArrayList<ChildVO> nameList = memService.getMyGymChildName(gymNo);

		for (ChildVO cvo : nameList) {
			String deadLine = cvo.getDeadLine();
			deadLine = deadLine.substring(0, 4) + "-" + deadLine.substring(6, 8) + "-" + deadLine.substring(10, 12);
			LocalDate line = LocalDate.parse(deadLine, calFm);
			long left = ChronoUnit.DAYS.between(now, line);

			if (left <= 7 && left >= 0)
				cvo.setLeftChk("soon");
			else if (left < 0)
				cvo.setLeftChk("ended");
			else
				cvo.setLeftChk("plenty");

		}
		
		ArrayList<EventVO> eventList = gallService.getEventDataToManageForm(memId);
				
		model.addAttribute("nameList", nameList);
		model.addAttribute("ctg", ctg);
		model.addAttribute("eventList", eventList);

		return "member/memberManageForm";
	}

	@ResponseBody
	@RequestMapping("/member/getMemDateByChildNo")
	public ChildVO getMemDateByChildNo(@RequestParam("childNo") String childNo) {
		ChildVO cvo = memService.getMemDateByChildNo(childNo);

		return cvo;
	}
	
	//납부관리
	@ResponseBody
	@RequestMapping("/member/getChildNoJoinEvent")
	public ArrayList<Integer> getChildNoJoinEvent(@RequestParam("eventNo") String no) {
		if(no != null) {
			int eventNo = Integer.parseInt(no);
			ArrayList<Integer> eventNoList = memService.getChildNoJoinEvent(eventNo);
			
			return eventNoList;		
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/member/getAwardContents")
	public AwardVO getAwardContents(@RequestParam("childNo") String childNo,
			@RequestParam(defaultValue = "1") int page) {

		int totalPageNum = memService.totalAwardPage(childNo);
		if (totalPageNum > 0) {
			AwardVO avo = memService.getAwardContents(childNo, page);

			avo.setTotalPage(totalPageNum);

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 M월 d일", Locale.KOREAN);
			String dateFmt = fmt.format(avo.getGetAwardDay());

			avo.setDateFmt(dateFmt);

			return avo;
		}

		return null;
	}

	// 회원 아이 수상내역
	@RequestMapping("/member/memAwardForm")
	public String memAwardForm(HttpSession session, Model model) {
		String memId = (String) session.getAttribute("sid");
		MemberVO vo = memService.getMemData(memId);
		
		ArrayList<ChildVO> childList = childService.getMyChildren(memId);
		
		model.addAttribute("childList",childList);
		model.addAttribute("vo", vo);

		return "member/memberAwardForm";
	}

	// 회원관리 이름 검색
	@ResponseBody
	@RequestMapping("/member/childNameSearch")
	public ArrayList<ChildVO> childNameSearch(@RequestParam("word") String word, HttpSession session) {
		String memId = (String) session.getAttribute("sid");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("memId", memId);
		map.put("word", word);

		DateTimeFormatter calFm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();

		ArrayList<ChildVO> childList = memService.childNameSearch(map);

		for (ChildVO cvo : childList) {
			String deadLine = cvo.getDeadLine();
			deadLine = deadLine.substring(0, 4) + "-" + deadLine.substring(6, 8) + "-" + deadLine.substring(10, 12);
			LocalDate line = LocalDate.parse(deadLine, calFm);
			long left = ChronoUnit.DAYS.between(now, line);

			if (left <= 7 && left >= 0)
				cvo.setLeftChk("soon");
			else if (left < 0)
				cvo.setLeftChk("ended");
			else
				cvo.setLeftChk("plenty");

		}

		return childList;
	}

	// 마이페이지 띠 수정
	@ResponseBody
	@RequestMapping("/member/childBeltUpdate")
	public void childBeltUpdate(@RequestParam HashMap<String, Object> map) {
		memService.childBeltUpdate(map);
	}

	// 마이페이지 닉네임 변경 중복 체크
	@RequestMapping("/member/myPageNickChk")
	@ResponseBody
	public String myPageNickChk(@RequestParam("nick") String nick, @RequestParam("OriginNick") String OriginNick) {
		String result = "false";
		String pattern1 = "^(?=.*[ㄱ-ㅎ가-힣])[가-힣a-zA-Z*\\d*]{3,8}$";
		String pattern2 = "^(?=.*[a-zA-Z])[a-zA-Zㄱ-ㅎ가-힣*\\d*]{3,8}$";

		String memNick = memService.joinMemNickChk(nick);

		if (memNick != null) {
			if (memNick.equals(OriginNick))
				return "equal";
			else
				result = "true";
		} else {
			if (Pattern.matches(pattern1, nick) || Pattern.matches(pattern2, nick))
				result = "false";
			else
				result = "true";
		}

		return result;
	}

	// 회원 수상 경력 추가
	@ResponseBody
	@RequestMapping("/member/addAwardContents")
	public void addAwardContents(@ModelAttribute AwardVO awardvo) throws ParseException {
		String date = awardvo.getDateFmt();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date awardDate = formatter.parse(date);

		awardvo.setGetAwardDay(awardDate);

		memService.addAwardContents(awardvo);
	}

	// 회원 수상 경력 삭제
	@RequestMapping("/member/awardDelete")
	@ResponseBody
	public void awardDelete(@RequestParam("awardNo") int awardNo) {
		memService.deletAward(awardNo);
	}

	// 이미지 업로드 및 저장
	@ResponseBody
	@RequestMapping("/member/imageFileUpload")
	public String imageFileUpload(@RequestParam("uploadFile") MultipartFile file, HttpSession session)
			throws IOException {
		//String uploadPath = "C:/springWorkspace/upload/";
		// 이미지 업로드 서버 경로
		String uploadPath = "/usr/local/project/upload/";

		String originalFileName = file.getOriginalFilename();				
		
		String filePath = uploadPath + originalFileName;
		GreenEyeVO green = greenService.greenEye(filePath);
		
		if(green.getPorn() < 0.5) {
			File sendFile = new File(uploadPath + originalFileName);
	
			file.transferTo(sendFile);
			
			// db에 저장
			String memId = (String) session.getAttribute("sid");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memId", memId);
			map.put("fileName", originalFileName);

			memService.updateMemImgNo(map);
			
			return "success";
		}
		
		return "fail";
	}

	// 업로드
	@ResponseBody
	@RequestMapping("/member/imageFileUploadOnly")
	public void imageFileUploadOnly(@RequestParam("uploadFile") MultipartFile file, HttpSession session)
			throws IOException {
		//String uploadPath = "C:/springWorkspace/upload/";
		String uploadPath = "/usr/local/project/upload/";

		String originalFileName = file.getOriginalFilename();

		File sendFile = new File(uploadPath + originalFileName);

		file.transferTo(sendFile);
	}

	// 여러장 업로드
	@ResponseBody
	@RequestMapping("/member/imageFileUploadMulti")
	public ArrayList<String> imageFileUploadMulti(@RequestParam("uploadFile") MultipartFile[] files, HttpSession session)
			throws IOException {
		//String uploadPath = "C:/springWorkspace/upload/";
		String uploadPath = "/usr/local/project/upload/";
		
		ArrayList<String> fileNameList = new ArrayList<String>();
		
		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				String originalFileName = file.getOriginalFilename();
				
				fileNameList.add(originalFileName);
				
				File sendFile = new File(uploadPath + originalFileName);
				file.transferTo(sendFile);
			}
		}
		System.out.println(fileNameList);
		return fileNameList;
	}

	// 마이페이지 정보 업데이트
	@RequestMapping("/member/myPageUpdate")
	public String myPageUpdate(MemberVO vo) {
		memService.myPageUpdate(vo);

		return "redirect:/member/myPageForm";
	}

	// 회원탈퇴
	@ResponseBody
	@RequestMapping("/member/memDeleteAccount")
	public void memDeleteAccount(HttpSession session) {
		String memId = (String) session.getAttribute("sid");

		memService.memDeleteAccount(memId);
		childService.memDeleteAccount(memId);
		session.invalidate();
	}
}
