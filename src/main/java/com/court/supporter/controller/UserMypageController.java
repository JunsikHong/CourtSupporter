package com.court.supporter.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.usermypage.service.UserMypageService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/usermypage")
public class UserMypageController {

	@Autowired
	private UserMypageService userMypageService;
	
	// 사용자 정보 화면
	@GetMapping("/usermypage")
	public String usermypage(Model model) {
		String user_id = "sampleuser";
		model.addAttribute("vo", userMypageService.usermypage_getInfo(user_id));
		return "/usermypage/usermypage";
	}
	
	// 사용자 정보 비밀번호 확인 창
	@GetMapping("/usermypage_modify_pw_check_view")
	public String usermypage_modify_pw_check_view() {

		return "/usermypage/usermypage-modify-pw-check";
	}
	
	// 사용자 정보 수정 비밀번호 확인
	@PostMapping("/usermypage_modify_pw_check")
	public String usermypage_modify_pw_check (TB_001VO vo, Model model, HttpSession session) {
		String user_id = "sampleuser";
		vo.setUser_id(user_id);
		int result = userMypageService.usermypage_modify_pw_check(vo);
		if(result == 1) {
			session.setAttribute("pw_pass", "pw_pass");
			return "redirect:/usermypage/usermypage_modify";
		} else {
			return "redirect:/usermypage/usermypage_modify_pw_check_view";
		}
	}

	// 사용자 정보 수정 창
	@GetMapping("/usermypage_modify")
	public String usermypage_modify(Model model) {
		String user_id = "sampleuser";
		model.addAttribute("vo", userMypageService.usermypage_getInfo(user_id));
		return "/usermypage/usermypage-modify";
	}
	
	// 사용자 정보 수정
	@PostMapping("/usermypage_modify_confirm")
	public String usermypage_modify(TB_001VO vo, RedirectAttributes ra) {
		int result = userMypageService.usermypage_modifyInfo(vo);
		if(result == 1) {
			ra.addFlashAttribute("msg", "정보를 변경하였습니다.");
		} else {
			ra.addFlashAttribute("msg", "정보 변경에 실패하였습니다.");			
		}
		return "redirect:/usermypage/usermypage";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	// 사용자 탈퇴 화면
	@GetMapping("/usermypage_withdrawl")
	public String usermypage_withdrawl() {

		return "/usermypage/usermypage-withdrawl";
	}
	
	// 사용자 탈퇴 체크 화면
	@GetMapping("/usermypage_withdrawl_popup")
	public String usermypage_withdrawl_popup() {
		String user_id = "sampleuser";
		return "/usermypage/usermypage-withdrawl-popup";
	}
	
	// 사용자 탈퇴
	@PostMapping("/usermypage_withdrawl_check")
	public String usermypage_withdrawl_check(TB_001VO vo, Model model) {
		String user_id = "sampleuser";
		vo.setUser_id(user_id);
		int result = userMypageService.usermypage_withdrawl(vo);
		model.addAttribute("result", result);
		return "/usermypage/usermypage-withdrawl-popup";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// 사용자 신청 현황 리스트
	@GetMapping("/usermypage_applicationlist")
	public String usermypage_applicationlist(Model model, Criteria cri) {
		String user_id = "sampleuser";
		ArrayList<TB_005VO> list = userMypageService.usermypage_application_getlist(user_id, cri);
		int total = userMypageService.usermypage_application_gettotal(user_id, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		return "/usermypage/usermypage-applicationlist";
	}

	@GetMapping("/usermypage_applicationdetail")
	public String usermypage_applicationdetail(Model model, TB_005VO vo) {
		String user_id = "sampleuser";
		vo.setUser_id(user_id);
		TB_005VO result = userMypageService.usermypage_getapplicationdetail(vo);
		model.addAttribute("result", result);
		return "/usermypage/usermypage-applicationdetail";
	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	// 사용자 활동 내역 리스트
	@GetMapping("/usermypage_activitylist")
	public String usermypage_activitylist() {

		return "/usermypage/usermypage-activitylist";
	}

	// 사용자 활동 내역 상세
	@GetMapping("/usermypage_activitydetail")
	public String usermypage_activitydetail() {

		return "/usermypage/usermypage-activitydetail";
	}

	// 사용자 활동 내역 수정
	@GetMapping("/usermypage_activitymodify")
	public String usermypage_activitymodify() {

		return "/usermypage/usermypage-activitymodify";
	}

	// 사용자 중지
	@GetMapping("/usermypage_pause")
	public String usermypage_pause() {

		return "/usermypage/usermypage-pause";
	}

	// 사용자 중지 상세
	@GetMapping("/usermypage_pausedetail")
	public String usermypage_pausedetail() {

		return "/usermypage/usermypage-pausedetail";
	}

	// 사용자 해제
	@GetMapping("/usermypage_cancel")
	public String usermypage_cancel() {

		return "/usermypage/usermypage-cancel";
	}
}
