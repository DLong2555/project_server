package com.finalProject.gym.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finalProject.gym.model.EventVO;
import com.finalProject.gym.model.GalleryVO;
import com.finalProject.gym.service.ChildService;
import com.finalProject.gym.service.GalleryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {

	private GalleryService gallService;
	private ChildService childService;

	@Autowired
	public GalleryController(GalleryService gallService, ChildService childService) {
		this.gallService = gallService;
		this.childService = childService;
	}

	// 갤러리
	@RequestMapping("/gallery/gallery")
	public String gallery(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize, HttpSession session, Model model) {

		String memId = (String) session.getAttribute("sid");
		String gymNo = (String) session.getAttribute("sidGymNo");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);

		if (gymNo == null) {
			ArrayList<String> gymNameList = childService.getMyGymName(memId);
			ArrayList<GalleryVO> gallList = gallService.getGallContentsWhenMem(gymNameList, page, pageSize);
			int pages = gallService.getGallTotalPage(gymNameList);
			int totalPages = (int) Math.ceil((double) pages / pageSize);

			for (GalleryVO gall : gallList) {
				String deadLine = formatter.format(gall.getGalleryDate());

				gall.setRegistDate(deadLine);
			}

			model.addAttribute("gallList", gallList);
			model.addAttribute("totalPage", totalPages);
			model.addAttribute("pageSize", pageSize);
		} else {
			ArrayList<GalleryVO> gallList = gallService.getGallContentsWhenAdmin(memId, page, pageSize);
			int pages = gallService.getEventTotalPageWhenAdmin(memId);
			int totalPages = (int) Math.ceil((double) pages / pageSize);

			for (GalleryVO gall : gallList) {
				String deadLine = formatter.format(gall.getGalleryDate());

				gall.setRegistDate(deadLine);
			}

			model.addAttribute("gallList", gallList);
			model.addAttribute("totalPage", totalPages);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("gymNo", gymNo);
		}

		return "gallery/gallery";
	}

	// 갤러리 등록 페이지
	@RequestMapping("/gallery/insertGalleryForm")
	public String insertGalleryForm() {
		return "gallery/insertGalleryForm";
	}

	// 갤러리 등록
	@ResponseBody
	@RequestMapping("/gallery/insertGallery")
	public void insertGallery(@ModelAttribute GalleryVO gallVO, HttpSession session) throws ParseException {
		String memId = (String) session.getAttribute("sid");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date date = formatter.parse(gallVO.getRegistDate());

		gallVO.setMemId(memId);
		gallVO.setGalleryDate(date);

		gallService.insertGalleryContents(gallVO);
	}

	// 갤러리 상세 페이지
	@RequestMapping("/gallery/galleryContentPage")
	public String galleryContentPage(@RequestParam(value = "galleryNo", required = false) String gallNo, Model model) {

		if (gallNo != null) {
			int galleryNo = Integer.parseInt(gallNo);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일(E) hh시 mm분 ss초", Locale.KOREAN);

			GalleryVO gall = gallService.getGallDetailContent(galleryNo);
			String createdAt = formatter.format(gall.getGalleryDate());

			gall.setRegistDate(createdAt);

			model.addAttribute("gall", gall);
		}

		return "gallery/galleryContentPage";
	}

	// 갤러리 글 삭제
	@ResponseBody
	@RequestMapping("/gallery/deleteContent")
	public void deleteContent(@RequestParam(value = "galleryNo") String no) {
		if (no != null) {
			int galleryNo = Integer.parseInt(no);
			gallService.deleteGallContent(galleryNo);
		}
	}

	// 갤러리 수정하기
	@RequestMapping("/gallery/updateGallContentForm")
	public String updateGallContentForm(@RequestParam("galleryNo") int galleryNo, Model model) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);

		GalleryVO gall = gallService.getGallDetailContent(galleryNo);
		String date = formatter.format(gall.getGalleryDate());

		gall.setRegistDate(date);

		model.addAttribute("gall", gall);

		return "gallery/modifyGalleryForm";
	}

	// 갤러리 수정
	@ResponseBody
	@RequestMapping("/gallery/updateGallery")
	public void updateGallContent(@ModelAttribute GalleryVO gall)
			throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date date = formatter.parse(gall.getRegistDate());

		gall.setGalleryDate(date);

		gallService.updateGallContent(gall);
	}

	// 특수활동 페이지
	@GetMapping("/gallery/eventBoardForm")
	public String eventBoardForm(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pageSize", defaultValue = "9") int pageSize, HttpSession session, Model model) {

		String memId = (String) session.getAttribute("sid");
		String gymNo = (String) session.getAttribute("sidGymNo");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);

		if (gymNo == null) {
			ArrayList<String> gymNameList = childService.getMyGymName(memId);
			ArrayList<EventVO> eventList = gallService.getEventContentsWhenMem(gymNameList, page, pageSize);
			int pages = gallService.getEventTotalPage(gymNameList);
			int totalPages = (int) Math.ceil((double) pages / pageSize);

			for (EventVO event : eventList) {
				String deadLine = formatter.format(event.getEventDeadLine());

				event.setDeadLine(deadLine);
			}

			model.addAttribute("eventList", eventList);
			model.addAttribute("totalPage", totalPages);
			model.addAttribute("pageSize", pageSize);
		} else {
			ArrayList<EventVO> eventList = gallService.getEventContentsWhenAdmin(memId, page, pageSize);
			int pages = gallService.getEventTotalPageWhenAdmin(memId);
			int totalPages = (int) Math.ceil((double) pages / pageSize);

			for (EventVO event : eventList) {
				String deadLine = formatter.format(event.getEventDeadLine());

				event.setDeadLine(deadLine);
			}

			model.addAttribute("eventList", eventList);
			model.addAttribute("totalPage", totalPages);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("gymNo", gymNo);
		}

		return "gallery/eventBoardForm";
	}

	// 특수활동 공고 등록 페이지
	@RequestMapping("/gallery/insertEventFrom")
	public String insertEventFrom() {
		return "gallery/insertEventForm";
	}

	// 특수활동 공고 등록
	@ResponseBody
	@RequestMapping("/gallery/insertEvent")
	public void insertEvent(@ModelAttribute EventVO eventvo, @RequestParam("deadLine") String deadLine,
			HttpSession session) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date eventDeadLine = formatter.parse(deadLine);

		eventvo.setMemId((String) session.getAttribute("sid"));
		eventvo.setEventDeadLine(eventDeadLine);

		gallService.insertEvent(eventvo);
	}

	// 특수활동 상세 페이지
	@RequestMapping("/gallery/eventBoardContentPage")
	public String evnetBoardContentPage(@RequestParam(value = "eventNo", required = false) String no, Model model) {

		if (no != null) {
			int eventNo = Integer.parseInt(no);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일(E) hh시 mm분 ss초", Locale.KOREAN);
			SimpleDateFormat DeadLineformatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);

			EventVO event = gallService.getEventDetailContent(eventNo);
			String createdAt = formatter.format(event.getEventDate());
			String deadLine = DeadLineformatter.format(event.getEventDeadLine());

			event.setCreatedAt(createdAt);
			event.setDeadLine(deadLine);

			model.addAttribute("event", event);
		}

		return "gallery/eventBoardContentPage";
	}

	// 특수활동 공고 삭제
	@ResponseBody
	@RequestMapping("/gallery/deleteGallContent")
	public void deleteGallContent(@RequestParam(value = "eventNo") String no) {
		if (no != null) {
			int eventNo = Integer.parseInt(no);
			gallService.deleteEventContent(eventNo);
		}

	}

	// 특수활동 수정하기
	@RequestMapping("/gallery/updateEventContentForm")
	public String updateEventContentForm(@RequestParam("eventNo") int eventNo, Model model) {

		SimpleDateFormat DeadLineformatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);

		EventVO event = gallService.getEventDetailContent(eventNo);
		String deadLine = DeadLineformatter.format(event.getEventDeadLine());

		event.setDeadLine(deadLine);

		model.addAttribute("event", event);

		return "gallery/updateEventContentForm";
	}

	// 특수활동 공고 수정
	@ResponseBody
	@RequestMapping("/gallery/updateEventContent")
	public void updateEventContent(@ModelAttribute EventVO event, @RequestParam("deadLine") String deadLine)
			throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
		Date eventDeadLine = formatter.parse(deadLine);

		event.setEventDeadLine(eventDeadLine);

		gallService.updateEventContent(event);
	}
}
