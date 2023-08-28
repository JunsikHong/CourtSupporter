package com.court.supporter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "/main";
	}
	
	@GetMapping("/noticeList")
	public String noticeList() {
		return "notice/noticeList";
	}
	
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		return "notice/noticeDetail";
	}
	
	@GetMapping("/noticeModify")
	public String noticeModify() {
		return "notice/noticeModify";
	}
	
	@GetMapping("/noticeRegist")
	public String noticeRegist() {
		return "notice/noticeRegist";
	}
	
	@GetMapping("/faqList")
	public String faqList() {
		return "notice/faqList";
	}
	
	@GetMapping("/faqDetail")
	public String faqDetail() {
		return "notice/faqDetail";
	}
	
	@GetMapping("/faqModify")
	public String faqModify() {
		return "notice/faqModify";
	}
	
	@GetMapping("/faqRegist")
	public String faqRegist() {
		return "notice/faqRegist";
	}
	
	
}
