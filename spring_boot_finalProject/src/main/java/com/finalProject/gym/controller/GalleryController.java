package com.finalProject.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.finalProject.gym.service.GalleryService;

@Controller
public class GalleryController {

	private GalleryService gellService;

	@Autowired
	public GalleryController(GalleryService gellService) {
		this.gellService = gellService;
	}

	// 갤러리
	@RequestMapping("/gallery/gallery")
	public String gallery() {
		return "gallery/gallery";
	}

	// 갤러리 등록
	@RequestMapping("/gallery/insertGalleryForm")
	public String insertGalleryForm() {
		return "gallery/insertGalleryForm";
	}

	// 갤러리 상세 페이지
	@RequestMapping("/gallery/galleryContentPage")
	public String galleryContentPage() {
		return "gallery/galleryContentPage";
	}

	// 특수활동 페이지
	@RequestMapping("/gallery/eventBoardForm")
	public String eventBoardForm() {
		return "gallery/eventBoardForm";
	}

	// 특수활동 공고 등록
	@RequestMapping("/gallery/insertEventFrom")
	public String insertEventFrom() {
		return "gallery/insertEventForm";
	}
	
	//특수활동 상세 페이지
	@RequestMapping("/gallery/evnetBoardContentPage")
	public String evnetBoardContentPage() {
		return "gallery/evnetBoardContentPage";
	}
}
