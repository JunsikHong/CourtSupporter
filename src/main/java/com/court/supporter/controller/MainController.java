package com.court.supporter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "/main";
	}
	
	//공고 등록 화면으로 이동
	@GetMapping("/announceReg")
	public String announceRegist() {
		return "announce/announceReg";
	}	

	//등록시 목록으로 이동
	@PostMapping("/announceRegForm")
	public String regist() {
		
		return "redirect:/announce/announceList";
	}
	
	//공고 목록으로 이동
	@GetMapping("/announceList")
	public String announceList() { //Model model, Criteria cri
		
//		ArrayList<AnnounceVO> list = announceService.getList();		
		
		return "announce/announceList";
	}
	
	//임시
	@GetMapping("/announceDetail")
	public String announceDetail() {
		return "announce/announceDetail";
	}
	
	//공고 수정
	@GetMapping("/announceModify")
	public String announceModify() {
		return "announce/announceModify";
	}
	
	
	//공고 삭제
	
	
	
}
