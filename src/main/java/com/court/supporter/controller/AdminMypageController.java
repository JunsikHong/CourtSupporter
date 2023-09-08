package com.court.supporter.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.court.supporter.adminmypage.service.AdminMypageService;
import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;
import com.court.supporter.usermypage.service.UserMypageService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/adminmypage")
public class AdminMypageController {

	@Autowired
	@Qualifier("adminMypageService")
	private AdminMypageService adminMypageService;
	
	@Autowired
	@Qualifier("userMypageService")
	private UserMypageService userMypageService;
	
	//관리자 평가 리스트
	@GetMapping("/adminmypage_evaluationlist")
	public String adminmypage_evaluationlist (Model model, Criteria cri) {
		String member_id = "admin";
		String member_role = adminMypageService.adminmypage_authcheck(member_id);
		ArrayList<TB_005VO> list = null;
		int total = 0;
		if(member_role.equals("juris")) {
			list = adminMypageService.adminmypage_juris_getapplicationlist(cri);
			total = adminMypageService.adminmypage_juris_getapplicationtotal(cri);
		} else if(member_role.equals("court")) {
			list = adminMypageService.adminmypage_court_getapplicationlist(cri);
			total = adminMypageService.adminmypage_court_getapplicationtotal(cri);
		} else if(member_role.equals("admin")) {
			list = adminMypageService.adminmypage_admin_getapplicationlist(cri);
			total = adminMypageService.adminmypage_admin_getapplicationtotal(cri);
		}
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("total", total);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("list", list);
		model.addAttribute("auth", member_role);
		return "/adminmypage/adminmypage-evaluationlist";
	}

	//관리자 평가 상세
	@GetMapping("/adminmypage_evaluationdetail")
	public String adminmypage_evaluationdetail (TB_005VO vo, Model model) {
		String member_id = "admin";
		String user_id = "sampleuser";
		vo.setUser_id(user_id);
		TB_001VO tb_001VO = userMypageService.usermypage_getInfo(user_id);
		TB_005VO tb_005VO = userMypageService.usermypage_getapplicationdetail1(vo);
		TB_006VO tb_006VO = userMypageService.usermypage_getapplicationdetail2(vo);
		ArrayList<TB_007VO> tb_007VOlist = userMypageService.usermypage_getapplicationdetail3(vo);
		ArrayList<TB_008VO> tb_008VOlist = userMypageService.usermypage_getapplicationdetail4(vo);
		ArrayList<TB_009VO> tb_009VOlist = userMypageService.usermypage_getapplicationdetail5(vo);
		TB_010VO tb_010VO = userMypageService.usermypage_getapplicationdetail6(vo);
		ArrayList<TB_011VO> tb_011VOlist = userMypageService.usermypage_getapplicationdetail7(vo);
		model.addAttribute("member_id", member_id);
		model.addAttribute("tb_001VO", tb_001VO);
		model.addAttribute("tb_005VO", tb_005VO);
		model.addAttribute("tb_006VO", tb_006VO);
		model.addAttribute("tb_007VOlist", tb_007VOlist);
		model.addAttribute("tb_008VOlist", tb_008VOlist);
		model.addAttribute("tb_009VOlist", tb_009VOlist);
		model.addAttribute("tb_010VO", tb_010VO);
		model.addAttribute("tb_011VOlist", tb_011VOlist);
		return "/adminmypage/adminmypage-evaluationdetail";
	}
	
	//관리자 평가 팝업
	@GetMapping("/adminmypage_evaluation_popup")
	public String adminmypage_evaulation_popup () {
		return "/adminmypage/adminmypage-evaluation-popup";
	}
	
	//관리자 평가
	@GetMapping("/adminmypage_evaluation")
	public String adminmypage_evaulation (Model model) {
		int result = 0;
		model.addAttribute("result", result);
		return "/adminmypage/adminmypage-evaluation-popup";
	}
	
}
