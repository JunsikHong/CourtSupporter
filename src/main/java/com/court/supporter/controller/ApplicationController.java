package com.court.supporter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
//	@Autowired
//	@Qualifier("applicationService")
//	private ApplicationService applicationService;

	//동의 화면
	@GetMapping("/agree")
	public String agreeForm() {
		return "application/agree";
	}
	
	//기본 정보 입력 화면으로 이동
	@PostMapping("/agreeForm")
	public String basic() {
		return "redirect:/application/basic";
	}
	
	//기본 정보 화면
	@GetMapping("/basic")
	public String basicForm() {
		return "application/basic";
	}
	
	//학력사항 입력 화면으로 이동
	@PostMapping("/basicForm")
	public String education() {
		return "redirect:/application/education";
	}
	
	//학력사항 입력 화면
	@GetMapping("/education")
	public String educationForm() {
		return "application/education";
	}
	
	//학력사항 입력 팝업창
	@GetMapping("/educationPopup")
	public String educationPopupForm() {
		return "application/educationPopup";
	}
	
	//경력사항 입력 화면으로 이동
	@PostMapping("/educationForm")
	public String work() {
		return "redirect:/application/work";
	}
	
	//경력사항 입력 화면
	@GetMapping("/work")
	public String workForm() {
		return "application/work";
	}
	
	//경력사항 입력 팝업창
	@GetMapping("/workPopup")
	public String workPopupForm() {
		return "application/workPopup";
	}
	
	//자격증 정보 화면으로 이동
	@PostMapping("/workForm")
	public String certificate() {
		return "redirect:/application/certificate";
	}
	
	//자격증 정보 입력 화면
	@GetMapping("/certificate")
	public String certificateForm() {
		return "application/certificate";
	}
	
	//자격증 정보 입력 팝업창
	@GetMapping("/certificatePopup")
	public String certificatePopupForm() {
		return "application/certificatePopup";
	}
	
	//증빙서류 화면으로 이동
	@PostMapping("/certificateForm")
	public String attachment() {
		return "redirect:/application/attachment";
	}
	
	//증빙서류 입력 화면
	@GetMapping("/attachment")
	public String attachmentForm() {
		return "application/attachment";
	}
}
