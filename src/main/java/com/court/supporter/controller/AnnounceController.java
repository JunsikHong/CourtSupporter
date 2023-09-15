package com.court.supporter.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.court.supporter.adminmypage.service.AdminMypageService;
import com.court.supporter.announce.service.AnnounceService;
import com.court.supporter.aws.service.AnnounceFileService;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.command.TB_010VO;
import com.court.supporter.command.TB_017VO;
import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.security.jwt.JwtValidator;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Controller
@RequestMapping("/announce")
@RequiredArgsConstructor
public class AnnounceController {
	
	/*
	private final JwtValidator jwtValidator;
	

	public void exam(HttpServletRequest request) {
		
      //session에서 token 불러오기
      HttpSession session = request.getSession();
      String jwt = (String) session.getAttribute("token");
      
      //로그인 했을 때(= 토큰이 있을 때)
      if (jwt != null) {
         Authentication authentication = jwtValidator.getAuthentication(jwt);
         DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
         
         //token에서 member_proper_num 가져오기
           String member_proper_num = userDetails.getUsername();
           //token에서 member_role 가져오기
           //role은 Collection으로 받아와서 배열로 바꾼 뒤 roles[0]를 가져오면 됩니다 :)
           Collection<? extends GrantedAuthority> member_role = userDetails.getAuthorities();
           Object[] roles = member_role.toArray();
      }
	}
	*/
	
	@Autowired
	@Qualifier("announceService")
	private AnnounceService announceService;
	
    @Autowired
    @Qualifier("announceFileService")
    private AnnounceFileService announceFileService;

    @Autowired
    @Qualifier("adminMypageService")
    private AdminMypageService adminMypageService;
    
    @Autowired
    private S3Client s3;
    
    @Value("${aws_bucket_name}")
    private String aws_bucket_name;

    private String uploadPath = "announce/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	
	//공고 등록 화면으로 이동
	@GetMapping("/announceReg")
	public String announceRegist() {
		return "announce/announceReg";
	}	

	//등록시 목록으로 이동
	@PostMapping("/announceRegForm")
	public String regist(TB_002VO vo, TB_010VO tb_010VO, RedirectAttributes ra , @RequestParam("file") List<MultipartFile> list) { //@RequestParam("announce_file") List<MultipartFile> list    MultipartFile file

				
		//임의로 관리자 및 재판조력자 고유번호 설정
		vo.setAdmin_proper_num(1);
		//vo.setTrial_fcltt_proper_num(23);		

		tb_010VO.getTrial_fcltt_clasifi_code();
		tb_010VO.getTrial_fcltt_sbcls_code();	
//		tb_010VO.getTrial_fcltt_proper_num();
		//vo.setTrial_fcltt_proper_num(tb_010VO.getTrial_fcltt_proper_num());		
		
		Integer trialFclttProperNum = announceService.getTrial_flctt_proper_num(tb_010VO);
		
		if(tb_010VO.getTrial_fcltt_sbcls_code() == null) { //소분류가 없다면 중분류만 있다는 것	
			tb_010VO.getTrial_fcltt_clasifi_code(); 
			vo.setTrial_fcltt_proper_num(trialFclttProperNum);
			System.out.println(vo.getTrial_fcltt_proper_num());
		} else {
			tb_010VO.getTrial_fcltt_sbcls_code();
			vo.setTrial_fcltt_proper_num(trialFclttProperNum);
			System.out.println(vo.getTrial_fcltt_proper_num());
		}

		//file upload
		list = list.stream().filter(t -> t.isEmpty() == false).toList();

	      for (MultipartFile file : list) {
	         if (file.getContentType().contains("image") == false &&
	            file.getContentType().contains("application/pdf") == false &&
	            file.getContentType().contains("text/plain") == false &&
	            file.getContentType().contains("application/x-hwp") == false) {
	            
	            //허용되지 않는 MIME타입인 경우 처리            
	            ra.addFlashAttribute("msg", "올바른 파일 형식이 아닙니다");	
	            return "redirect:/announce/announceList";
	         }	         
	      }	  	      
	      
	      List<String> fileList = announceFileService.announceRegist(list);
	      
	      announceService.announceRegist(vo, fileList);		
	      announceService.getTrial_flctt_proper_num(tb_010VO);
	      
		return "redirect:/announce/announceList";
	}
	
	
	//공고 목록 게시판 조회 + 검색
	@GetMapping("/announceList")
	public String announceList( Model model, Criteria cri) { //, @RequestParam(value = "categoryValue", required = false) , @RequestParam("trial_fcltt_proper_num") int trial_fcltt_proper_num
		String member_id = "user100";
		String member_role = adminMypageService.adminmypage_authcheck(member_id);		
		
		List<TB_002VO> list = announceService.announce_getList(cri);				
		int total = announceService.announce_getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);		
			
		//날짜 비교		
		for(TB_002VO vo : list) {
			String endDate = vo.getAnnounce_end_date();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localEndDate = LocalDate.parse(endDate, dateformat); //LocalDate
			LocalDate today = LocalDate.now(); //LocalDate
			if(today.isAfter(localEndDate)) {
				vo.setAnnounceStatus("모집완료");
			} else {
				vo.setAnnounceStatus("모집중");
			}			
		}

		model.addAttribute("list", list);		
		model.addAttribute("pageVO", pageVO); 
		model.addAttribute("member_role", member_role);

		return "announce/announceList";
	}
	
	@GetMapping("/announceTrialList/{category}")
	public String announceTrialList(@PathVariable("category")int category, Criteria cri , Model model, TB_002VO vo) { //, @PathVariable int trial_fcltt_proper_num 
										//int trial_fcltt_proper_num 
		//조력자 메뉴 클릭시 해당 조력자 공고조회			
		List<TB_002VO> trialList = announceService.getTrialList(category);
		
		int total = announceService.announce_getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		vo.getTrial_fcltt_proper_num();
		System.out.println(vo.getTrial_fcltt_proper_num());
		
		model.addAttribute("trialList", trialList);
		model.addAttribute("pageVO", pageVO); 
		//model.addAttribute("vo", vo);
		return "announce/announceTrialList/" + category;
		
	}
		
	
	//조력자 공고 조회
//	@GetMapping("/announceList/{category}")
//	public String announceTrialList(@PathVariable("category")int category, Criteria cri , Model model, TB_002VO vo) { //, @PathVariable int trial_fcltt_proper_num 
//										//int trial_fcltt_proper_num 
//		//조력자 메뉴 클릭시 해당 조력자 공고조회			
//		List<TB_002VO> trialList = announceService.getTrialList(category);
//		
//		int total = announceService.announce_getTotal(cri);
//		PageVO pageVO = new PageVO(cri, total);
//		
//		vo.getTrial_fcltt_proper_num();
//		System.out.println(vo.getTrial_fcltt_proper_num());
//		
//		model.addAttribute("trialList", trialList);
//		model.addAttribute("pageVO", pageVO); 
//		//model.addAttribute("vo", vo);
//		return "redirect:/announce/announceList/" + category;
//		
//	}
	
	
	
	//공고 게시글(공고 상세 화면)
	@GetMapping("/announceDetail") //파일첨부기능도 떠야함
	public String announceDetail(@RequestParam("announce_proper_num") String announce_proper_num, Model model) { //@RequestParam("announce_file_proper_num") int announce_file_proper_num)
//		fileVO.setAnnounce_proper_num(123);
		
		TB_002VO vo = announceService.getDetail(announce_proper_num);
		
		List<TB_017VO> filevo = announceService.getFileDetail(announce_proper_num);
		
		
		 // 이전 글과 다음 글 조회
	    TB_002VO previousPost = announceService.getPrev(announce_proper_num);
	    TB_002VO nextPost = announceService.getNext(announce_proper_num);
	    
	    model.addAttribute("filevo", filevo);
	    model.addAttribute("previousPost", previousPost);
	    model.addAttribute("nextPost", nextPost);
	    model.addAttribute("vo", vo);
				
		return "announce/announceDetail";
	}
	
	
	//공고 수정 화면
	@GetMapping("/announceModify")
	public String announceModify(@RequestParam("announce_proper_num") String announce_proper_num, Model model) {
		
		 TB_002VO vo = announceService.getDetail(announce_proper_num);
		 model.addAttribute("vo", vo); System.out.println(vo.toString());
		
		return "announce/announceModify";
	}
	
	//공고 수정후 게시물로 이동
	@PostMapping("/announceModifyForm")
	public String modify(@ModelAttribute("vo") TB_002VO vo, RedirectAttributes ra) {
		
		System.out.println(vo); //확인
		
		int result = announceService.announceModify(vo);
		String msg = result == 1 ? "수정 완료" : "수정 실패";
		ra.addFlashAttribute("msg", msg);
		return "redirect:/announce/announceList";
		
	}
	
	
	//공고 삭제	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("announce_proper_num") String announce_proper_num, RedirectAttributes ra) {
		
		announceService.announceDelete(announce_proper_num);
		ra.addFlashAttribute("deleteForm", "삭제하시겠습니까?");
		
		return "redirect:/announce/announceList";
	}
	
	
}
