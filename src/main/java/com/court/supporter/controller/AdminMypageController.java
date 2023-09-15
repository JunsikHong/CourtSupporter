
package com.court.supporter.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.court.supporter.adminmypage.service.AdminMypageService;
import com.court.supporter.aws.service.EmailService;
import com.court.supporter.command.TB_001VO;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_005VO;
import com.court.supporter.command.TB_006VO;
import com.court.supporter.command.TB_007VO;
import com.court.supporter.command.TB_008VO;
import com.court.supporter.command.TB_009VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_011VO;
import com.court.supporter.command.TB_013VO;
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
		String member_proper_num = "23091400010";
		String member_role = adminMypageService.adminmypage_authcheck(member_proper_num);
		ArrayList<TB_005VO> list = null;
		int total = 0;
		if(member_role.equals("ROLE_JURIS")) {
			list = adminMypageService.adminmypage_juris_getapplicationlist(cri);
			total = adminMypageService.adminmypage_juris_getapplicationtotal(cri);
		} else if(member_role.equals("ROLE_COURT")) {
			list = adminMypageService.adminmypage_court_getapplicationlist(cri);
			total = adminMypageService.adminmypage_court_getapplicationtotal(cri);
		} else if(member_role.equals("ROLE_ADMIN")) {
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
		String member_proper_num = "23091400010";
		String member_role = adminMypageService.adminmypage_authcheck(member_proper_num);
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
		
		model.addAttribute("member_proper_num", member_proper_num);
		model.addAttribute("vo", vo);
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
	public String adminmypage_evaulation_popup (Model model) {
		String member_proper_num = "23091400010";
		
		
		
		//TB_005VO tb_005VO = adminMypageService.adminmypage_getapplicationdetail(aplcn_dtls_proper_num);
		//model.addAttribute("tb_005VO", tb_005VO);
		return "/adminmypage/adminmypage-evaluation-popup";
	}

	//관리자 평가
	@PostMapping("/adminmypage_evaluation")
	public String adminmypage_evaulation (TB_013VO tb_013VO, TB_005VO tb_005VO, RedirectAttributes ra) {
		
		String member_proper_num = "23091400010";
		String member_role = adminMypageService.adminmypage_authcheck(member_proper_num); //권한
		if(member_role == "ROLE_USER") return "/"; //권한이 사용자일 때는 서비스 종료
		String aplicn_dtls_sts = tb_005VO.getAplicn_dtls_sts(); // 신청자 상태
		
		////////////////////////////////////////////////////////////////////
		
		int evaluate_insert_result = adminMypageService.adminmypage_evaluate(tb_013VO);	//평가테이블에 넣기	
		
		////////////////////////////////////////////////////////////////////
		
		int end_date_yn = adminMypageService.adminmypage_checkannounceenddate(tb_005VO); //오늘날짜 공고종료날짜 비교
		int evaluate_complete_yn = adminMypageService.adminmypage_checkevaluatecomplete(tb_005VO);//모든 신청자가 심사완료인지 확인
		if(end_date_yn == 1) { //오늘날짜가 공고종료날짜 이후라면
			
			if(evaluate_complete_yn == 1) { //모든 신청자가 심사중
				if(member_role.equals("ROLE_JURIS")) { //권한이 사법이라면
					if(aplicn_dtls_sts.equals("03")) { //상태가 심사중일 때
						//상대평가 및 1차 합격
					}
				}
			} else if(evaluate_complete_yn == 3) { //모든 신청자가 최종심사중 상태
				if(member_role.equals("ROLE_COURT")) { //권한이 법원이라면
					if(aplicn_dtls_sts.equals("05")) { //상태가 1차합격일 때
						//상대평가 및 조력자 등재				
					}
				}
			}
			
		} else { //오늘날짜가 공고종료날짜 이전이라면
			
			if(member_role.equals("ROLE_JURIS")) { //권한이 사법이라면
				if(aplicn_dtls_sts.equals("02")) { //상태가 신청완료일 때
					adminMypageService.adminmypage_juris_evaluate(tb_013VO, tb_005VO); //사법관리자 평가
				}
			} else if(member_role.equals("ROLE_COURT")) { //권한이 법원이라면
				if(aplicn_dtls_sts.equals("05")) { //상태가 1차합격일 때
					adminMypageService.adminmypage_court_evaluate(tb_013VO, tb_005VO); //법원관리자 평가
				}
			}
			
		}
		
		////////////////////////////////////////////////////////////////////
		
		ra.addFlashAttribute("evaluate_insert_result", evaluate_insert_result);
		return "redirect:/adminmypage/adminmypage_evaluation_popup";
	}

}