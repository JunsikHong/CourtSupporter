package com.court.supporter.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.court.supporter.application.service.ApplicationService;
import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;

@Controller
@RequestMapping("/application")
public class ApplicationController {
	
	@Autowired
	@Qualifier("applicationService")
	private ApplicationService applicationService;

	//동의 화면
	@GetMapping("/applicationAgree")
	public String agreeForm() {
		return "application/applicationAgree";
	}
	
	//기본 정보 입력 화면으로 이동
	@PostMapping("/agreeForm")
	public String basic() {
		return "redirect:/application/applicationBasic";
	}
	
	//기본 정보 화면
	@GetMapping("/applicationBasic")
	public String basicForm(Model model) {
		String user_id = "user1";
		//신청인 정보 가져오기
		TB_001VO vo = applicationService.getUserInfo(user_id);
		
		model.addAttribute("vo", vo);
		
		return "application/applicationBasic";
	}
	
	//기본 정보 페이지 - 주민번호 조회
	@PostMapping("/fetchData")
	public ResponseEntity<String> fetchData(@RequestBody TB_001VO tb_001vo) {
        boolean isDataAvailable = applicationService.getUserRrn(tb_001vo.getUser_rrn());
        
        if (isDataAvailable) {
            return new ResponseEntity<>("{\"success\": true}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"success\": false}", HttpStatus.OK);
        }
    }
	
	//학력사항 입력 화면으로 이동
	@PostMapping("/basicForm")
	public String education(TB_001VO tb_001vo, TB_005VO tb_005vo, TB_010VO tb_010vo) {
		//기본 정보 등록
		applicationService.basicRegist(tb_001vo, tb_005vo, tb_010vo);
		
		return "redirect:/application/applicationEducation";
	}
	
	//학력사항 입력 화면
	@GetMapping("/applicationEducation")
	public String educationForm() {
		return "application/applicationEducation";
	}
	
	//학력사항 입력 팝업창
	@GetMapping("/applicationEducationPopup")
	public String educationPopupForm() {
		return "application/applicationEducationPopup";
	}
	
	//경력사항 입력 화면으로 이동
	@PostMapping("/educationForm")
	public String work() {
		
		return "redirect:/application/applicationWork";
	}
	
	//경력사항 입력 화면
	@GetMapping("/applicationWork")
	public String workForm() {
		return "application/applicationWork";
	}
	
	//경력사항 입력 팝업창
	@GetMapping("/applicationWorkPopup")
	public String workPopupForm() {
		return "application/applicationWorkPopup";
	}
	
	//자격증 정보 화면으로 이동
	@PostMapping("/workForm")
	public String certificate() {
		return "redirect:/application/applicationCertificate";
	}
	
	//자격증 정보 입력 화면
	@GetMapping("/applicationCertificate")
	public String certificateForm() {
		return "application/applicationCertificate";
	}
	
	//자격증 정보 입력 팝업창
	@GetMapping("/applicationCertificatePopup")
	public String certificatePopupForm() {
		return "application/applicationCertificatePopup";
	}
	
	//증빙서류 화면으로 이동
	@PostMapping("/certificateForm")
	public String attachment() {
		return "redirect:/application/applicationAttachment";
	}
	
	//증빙서류 입력 화면
	@GetMapping("/applicationAttachment")
	public String attachmentForm() {
		return "application/applicationAttachment";
	}
}
