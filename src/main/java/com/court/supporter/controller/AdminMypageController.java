
package com.court.supporter.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.court.supporter.adminmypage.service.AdminMypageService;
import com.court.supporter.announce.service.AnnounceService;
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
import com.court.supporter.command.TB_018VO;
import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.security.jwt.JwtValidator;
import com.court.supporter.usermypage.service.UserMypageService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/adminmypage")
@RequiredArgsConstructor
public class AdminMypageController {
	
	@Autowired
	@Qualifier("adminMypageService")
	private AdminMypageService adminMypageService;

	@Autowired
	@Qualifier("userMypageService")
	private UserMypageService userMypageService;
	
	@Autowired
	@Qualifier("announceService")
	private AnnounceService announceService;
	
	private final JwtValidator jwtValidator;
	
	//관리자 평가 리스트
	@GetMapping("/adminmypage_evaluationlist")
	public String adminmypage_evaluationlist (Model model, Criteria cri, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String roles = adminMypageService.adminmypage_authcheck(member_proper_num);
			ArrayList<TB_005VO> list = null;
			int total = 0;
			if(Objects.equals(roles, "ROLE_JURIS")) {
				list = adminMypageService.adminmypage_juris_getapplicationlist(cri);
				total = adminMypageService.adminmypage_juris_getapplicationtotal(cri);
			} else if(Objects.equals(roles, "ROLE_COURT")) {
				list = adminMypageService.adminmypage_court_getapplicationlist(cri);
				total = adminMypageService.adminmypage_court_getapplicationtotal(cri);
			} else if(Objects.equals(roles, "ROLE_ADMIN")) {
				list = adminMypageService.adminmypage_admin_getapplicationlist(cri);
				total = adminMypageService.adminmypage_admin_getapplicationtotal(cri);
			} else {
				return "redirect:/";
			}
			PageVO pageVO = new PageVO(cri, total);
			model.addAttribute("total", total);
			model.addAttribute("pageVO", pageVO);
			model.addAttribute("list", list);
			model.addAttribute("auth", roles);
			return "/adminmypage/adminmypage-evaluationlist";
		}
		return "redirect:/";
	}

	//관리자 평가 상세
	@PostMapping("/adminmypage_evaluationdetail")
	public String adminmypage_evaluationdetail (TB_005VO vo, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String roles = adminMypageService.adminmypage_authcheck(member_proper_num);
			String member_id = adminMypageService.adminmypage_getmember_id(member_proper_num);
			
			Map<TB_009VO, String> tb_009VOlist = new HashMap<TB_009VO, String>();
			
			TB_001VO tb_001VO = userMypageService.usermypage_getInfo(vo.getUser_proper_num());
			TB_005VO tb_005VO = userMypageService.usermypage_getapplicationdetail1(vo);
			TB_002VO tb_002VO = announceService.getDetail(tb_005VO.getAnnounce_proper_num());
			ArrayList<TB_006VO> tb_006VOlist = userMypageService.usermypage_getapplicationdetail2(vo);
			ArrayList<TB_007VO> tb_007VOlist = userMypageService.usermypage_getapplicationdetail3(vo);
			ArrayList<TB_008VO> tb_008VOlist = userMypageService.usermypage_getapplicationdetail4(vo);
			ArrayList<TB_009VO> fileVO = userMypageService.usermypage_getapplicationdetail5(vo);
			TB_010VO tb_010VO = userMypageService.usermypage_getapplicationdetail6(vo);
			ArrayList<TB_011VO> tb_011VOlist = userMypageService.usermypage_getapplicationdetail7(vo);
			
			fileVO.removeIf(Objects::isNull);
			for(int i = 0; i < fileVO.size(); i++) {
				String url = URLEncoder.encode(fileVO.get(i).getOriginal_file_name());
				tb_009VOlist.put(fileVO.get(i), fileVO.get(i).getOriginal_file_name());
				fileVO.get(i).setOriginal_file_name(url);
			}	
			
			tb_006VOlist.removeIf(Objects::isNull);
			tb_007VOlist.removeIf(Objects::isNull);
			tb_008VOlist.removeIf(Objects::isNull);
			tb_011VOlist.removeIf(Objects::isNull);
			
			model.addAttribute("member_id", member_id);
			model.addAttribute("member_proper_num", member_proper_num);
			model.addAttribute("member_role", roles);
			model.addAttribute("vo", vo);
			model.addAttribute("tb_001VO", tb_001VO);
			model.addAttribute("tb_002VO", tb_002VO);
			model.addAttribute("tb_005VO", tb_005VO);
			model.addAttribute("tb_006VOlist", tb_006VOlist);
			model.addAttribute("tb_007VOlist", tb_007VOlist);
			model.addAttribute("tb_008VOlist", tb_008VOlist);
			model.addAttribute("tb_009VOlist", tb_009VOlist);
			model.addAttribute("tb_010VO", tb_010VO);
			model.addAttribute("tb_011VOlist", tb_011VOlist);
			return "/adminmypage/adminmypage-evaluationdetail";
		}
		return "redirect:/";
	}

	//관리자 평가 팝업
	@PostMapping("/adminmypage_evaluation_popup")
	public String adminmypage_evaulation_popup (Model model, HttpServletRequest request, TB_005VO vo) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String roles = adminMypageService.adminmypage_authcheck(member_proper_num);
			
			Map<TB_009VO, String> tb_009VOlist = new HashMap<TB_009VO, String>();
			
			TB_005VO tb_005VO = userMypageService.usermypage_getapplicationdetail1(vo);
			TB_001VO tb_001VO = userMypageService.usermypage_getInfo(vo.getUser_proper_num());
			ArrayList<TB_009VO> fileVO = userMypageService.usermypage_getapplicationdetail5(vo);
			
			for(int i = 0; i < fileVO.size(); i++) {
				String url = URLEncoder.encode(fileVO.get(i).getOriginal_file_name());
				tb_009VOlist.put(fileVO.get(i), fileVO.get(i).getOriginal_file_name());
				fileVO.get(i).setOriginal_file_name(url);
			}
			
			model.addAttribute("member_proper_num", member_proper_num);
			model.addAttribute("member_role", roles);
			model.addAttribute("tb_005VO", tb_005VO);
			model.addAttribute("tb_001VO", tb_001VO);
			model.addAttribute("tb_009VOlist", tb_009VOlist);
			
			if(tb_005VO.getAplicn_dtls_sts().equals("02") || tb_005VO.getAplcn_dtls_proper_num().equals("05")) {
				return "/adminmypage/adminmypage-evaluation-popup"; //관리자 평가 화면
			} else {
				model.addAttribute("tb_013VO", adminMypageService.adminmypage_getresult(tb_005VO));
				return "/adminmypage/adminmypage-evaluation-result-info"; //관리자 평가 상세 결과 화면
			}
		}
		return "redirect:/";
	}

	//관리자 평가
	@PostMapping("/adminmypage_evaluation")
	public String adminmypage_evaulation (TB_013VO tb_013VO, TB_005VO tb_005VO, RedirectAttributes ra, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String member_role = adminMypageService.adminmypage_authcheck(member_proper_num); //권한
			if(member_role == "ROLE_USER") return "redirect:/"; //권한이 사용자일 때는 서비스 종료
			String aplicn_dtls_sts = tb_005VO.getAplicn_dtls_sts(); // 신청자 상태
			////////////////////////////////////////////////////////////////////
			
			tb_013VO.setAdmin_proper_num(member_proper_num); //평가자 기록
			int evaluate_insert_result = adminMypageService.adminmypage_evaluate(tb_013VO);	//평가테이블에 넣기	
			
			////////////////////////////////////////////////////////////////////
			
			int end_date_yn = adminMypageService.adminmypage_checkannounceenddate(tb_005VO); //오늘날짜 공고종료날짜 비교
			int evaluate_complete_yn = adminMypageService.adminmypage_checkevaluatecomplete(tb_005VO);//모든 신청자가 심사완료인지 확인
			if(end_date_yn == 1) { //오늘날짜가 공고종료날짜 이후라면
				
				if(evaluate_complete_yn == 1) { //모든 신청자가 심사중
					if(member_role.equals("ROLE_JURIS") || member_role.equals("ROLE_ADMIN")) { //권한이 사법이라면
						if(aplicn_dtls_sts.equals("03")) { //상태가 심사중일 때
							TB_001VO tb_001VO = userMypageService.usermypage_getInfo(tb_005VO.getUser_proper_num());
							adminMypageService.adminmypage_confirm_first_evaluate(tb_013VO, tb_005VO, tb_001VO); //상대평가 및 1차 합격
						}
					}
				} else if(evaluate_complete_yn == 3) { //모든 신청자가 최종심사중 상태
					if(member_role.equals("ROLE_COURT") || member_role.equals("ROLE_ADMIN")) { //권한이 법원이라면
						if(aplicn_dtls_sts.equals("05")) { //상태가 1차합격일 때
							TB_001VO tb_001VO = userMypageService.usermypage_getInfo(tb_005VO.getUser_proper_num());
							adminMypageService.adminmypage_confirm_final_evaluate(tb_013VO, tb_005VO, tb_001VO); //상대평가 및 조력자 등재				
						}
					}
				}
				
			} else if(end_date_yn == 0) { //오늘날짜가 공고종료날짜 이전이라면
				
				if(member_role.equals("ROLE_JURIS") || member_role.equals("ROLE_ADMIN")) { //권한이 사법이라면
					if(aplicn_dtls_sts.equals("02")) { //상태가 신청완료일 때
						TB_001VO tb_001VO = userMypageService.usermypage_getInfo(tb_005VO.getUser_proper_num());
						adminMypageService.adminmypage_juris_evaluate(tb_013VO, tb_005VO, tb_001VO); //사법관리자 평가
					}
				} else if(member_role.equals("ROLE_COURT") || member_role.equals("ROLE_ADMIN")) { //권한이 법원이라면
					if(aplicn_dtls_sts.equals("05")) { //상태가 1차합격일 때
						TB_001VO tb_001VO = userMypageService.usermypage_getInfo(tb_005VO.getUser_proper_num());
						adminMypageService.adminmypage_court_evaluate(tb_013VO, tb_005VO, tb_001VO); //법원관리자 평가
					}
				}
				
			}
			
			////////////////////////////////////////////////////////////////////
			ra.addFlashAttribute("user_name", userMypageService.usermypage_getInfo(tb_005VO.getUser_proper_num()).getUser_name());
			ra.addFlashAttribute("evaluate_insert_result", evaluate_insert_result);
			return "redirect:/adminmypage/adminmypage_evaluation_result";
			
		}
		return "redirect:/";
	}

	//관리자 평가 후 성공 메시지 결과화면
	@GetMapping("adminmypage_evaluation_result")
	public String adminmypage_evaluation_result (HttpServletRequest request, @ModelAttribute("user_name") String user_name) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			return "/adminmypage/adminmypage-evaluation-result";
		}
		return "redirect:/";
	}

	//관리자 권한 관리 목록 화면
	@GetMapping("adminmypage_auth_manage")
	public String adminmypage_auth_manage(HttpServletRequest request, Criteria cri, Model model) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String member_role = adminMypageService.adminmypage_authcheck(member_proper_num); //권한
			if(!member_role.equals("ROLE_ADMIN")) return "redirect:/";
			int total = adminMypageService.adminmypage_getauthtotal(cri);
			ArrayList<TB_018VO> list = adminMypageService.adminmypage_getauthlist(cri);
			PageVO pageVO = new PageVO(cri, total);
			model.addAttribute("total", total);
			model.addAttribute("pageVO", pageVO);
			model.addAttribute("list", list);
			return "/adminmypage/adminmypage-auth-manage";
		}
		return "redirect:/";
	}
	
	//관리자 권한 관리 목록 권한 설정
	@PostMapping("adminmypage_auth_manage_form")
	public String adminmypage_auth_manage_form (HttpServletRequest request, TB_018VO vo, RedirectAttributes ra) {
		HttpSession session = request.getSession();
		String jwt = (String) session.getAttribute("token");
		if (jwt != null) {
			Authentication authentication = jwtValidator.getAuthentication(jwt);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			String member_proper_num = userDetails.getUsername();
			String member_role = adminMypageService.adminmypage_authcheck(member_proper_num); //권한
			if(!member_role.equals("ROLE_ADMIN")) return "redirect:/";
			int result = adminMypageService.adminmypage_updateauth(vo);
			ra.addFlashAttribute("msg", result);
			return "redirect:/adminmypage/adminmypage_auth_manage";
		}
		return "redirect:/";
	}
	
}