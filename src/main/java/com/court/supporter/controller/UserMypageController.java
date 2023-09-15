package com.court.supporter.controller;

import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;
import com.court.supporter.command.TB_012VO;
import com.court.supporter.command.TB_014VO;
import com.court.supporter.usermypage.service.UserMypageService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/usermypage")
public class UserMypageController {

	@Autowired
	@Qualifier("userMypageService")
	private UserMypageService userMypageService;
	
	// 사용자 정보 화면
	@GetMapping("/usermypage")
	public String usermypage(Model model) {
		String user_proper_num = "23091400001";
		model.addAttribute("vo", userMypageService.usermypage_getInfo(user_proper_num));

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
		String user_proper_num = "23091400001";
		vo.setUser_id(user_proper_num);
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
		String user_proper_num = "23091400001";
		model.addAttribute("vo", userMypageService.usermypage_getInfo(user_proper_num));
		return "/usermypage/usermypage-modify";
	}
	
	// 사용자 정보 수정
	@PostMapping("/usermypage_modify_confirm")
	public String usermypage_modify(TB_001VO vo, RedirectAttributes ra) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
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
		return "/usermypage/usermypage-withdrawl-popup";
	}
	
	// 사용자 탈퇴
	@PostMapping("/usermypage_withdrawl_check")
	public String usermypage_withdrawl_check(TB_001VO vo, Model model) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
		int result = userMypageService.usermypage_withdrawl(vo);
		model.addAttribute("result", result);
		return "/usermypage/usermypage-withdrawl-popup";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// 사용자 신청 현황 리스트
	@GetMapping("/usermypage_applicationlist")
	public String usermypage_applicationlist(Model model, Criteria cri) {
		String user_proper_num = "23091400001";
		ArrayList<TB_005VO> list = userMypageService.usermypage_application_getlist(user_proper_num, cri);
		int total = userMypageService.usermypage_application_gettotal(user_proper_num, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		return "/usermypage/usermypage-applicationlist";
	}

	// 사용자 신청 현황 상세
	@GetMapping("/usermypage_applicationdetail")
	public String usermypage_applicationdetail(Model model, TB_005VO vo) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
		TB_001VO tb_001VO = userMypageService.usermypage_getInfo(user_proper_num);
		TB_005VO tb_005VO = userMypageService.usermypage_getapplicationdetail1(vo);
		TB_006VO tb_006VO = userMypageService.usermypage_getapplicationdetail2(vo);
		ArrayList<TB_007VO> tb_007VOlist = userMypageService.usermypage_getapplicationdetail3(vo);
		ArrayList<TB_008VO> tb_008VOlist = userMypageService.usermypage_getapplicationdetail4(vo);
		ArrayList<TB_009VO> tb_009VOlist = userMypageService.usermypage_getapplicationdetail5(vo);
		TB_010VO tb_010VO = userMypageService.usermypage_getapplicationdetail6(vo);
		ArrayList<TB_011VO> tb_011VOlist = userMypageService.usermypage_getapplicationdetail7(vo);
		
		tb_007VOlist.removeIf(Objects::isNull);
		tb_008VOlist.removeIf(Objects::isNull);
		tb_009VOlist.removeIf(Objects::isNull);
		tb_011VOlist.removeIf(Objects::isNull);
		
		model.addAttribute("tb_001VO", tb_001VO);
		model.addAttribute("tb_005VO", tb_005VO);
		model.addAttribute("tb_006VO", tb_006VO);
		model.addAttribute("tb_007VOlist", tb_007VOlist);
		model.addAttribute("tb_008VOlist", tb_008VOlist);
		model.addAttribute("tb_009VOlist", tb_009VOlist);
		model.addAttribute("tb_010VO", tb_010VO);
		model.addAttribute("tb_011VOlist", tb_011VOlist);
		return "/usermypage/usermypage-applicationdetail";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// 사용자 활동 내역 리스트
	@GetMapping("/usermypage_activitylist")
	public String usermypage_activitylist(Model model, Criteria cri) {
		String user_proper_num = "23091400001";
		ArrayList<TB_012VO> list = userMypageService.usermypage_activity_getlist(user_proper_num, cri);
		int total = userMypageService.usermypage_activity_gettotal(user_proper_num, cri);		
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		return "/usermypage/usermypage-activitylist";
	}

	// 사용자 활동 내역 상세
	@GetMapping("/usermypage_activitydetail")
	public String usermypage_activitydetail(Model model, TB_012VO vo) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
		TB_012VO result = userMypageService.usermypage_getactivitydetail(vo);
		model.addAttribute("result", result);
		return "/usermypage/usermypage-activitydetail";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////

	// 사용자 중지 리스트
	@GetMapping("/usermypage_pauselist")
	public String usermypage_pause(Model model, Criteria cri) {
		String user_proper_num = "23091400001";
		ArrayList<TB_014VO> list = userMypageService.usermypage_pauselist(user_proper_num, cri);
		int total = userMypageService.usermypage_pausetotal(user_proper_num, cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		return "/usermypage/usermypage-pause";
	}

	// 사용자 중지 상세
	@GetMapping("/usermypage_pausedetail")
	public String usermypage_pausedetail(Model model, TB_014VO vo) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
		TB_014VO result = userMypageService.usermypage_getpausedetail(vo);
		model.addAttribute("result", result);
		return "/usermypage/usermypage-pausedetail";
	}
	
	// 사용자 중지/해제 신청
	@PostMapping("/usermypage_pauseapplication")
	public String usermypage_pauseapplication(TB_014VO vo) {
		String user_proper_num = "23091400001";
		vo.setUser_proper_num(user_proper_num);
		System.out.println(vo.getAccept_act_yn());
		int result = userMypageService.usermypage_pauseapplication(vo);
		return "redirect:/usermypage/usermypage_pauselist";
	}

}

