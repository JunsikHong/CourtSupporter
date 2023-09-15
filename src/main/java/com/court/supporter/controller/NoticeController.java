package com.court.supporter.controller;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.court.supporter.notice.service.noticeService;
import com.court.supporter.adminmypage.service.AdminMypageService;
import com.court.supporter.aws.service.NoticeFileService;
import com.court.supporter.command.TB_003VO;
import com.court.supporter.command.TB_016VO;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	@Qualifier("noticeService")
	private noticeService noticeService;
	
	@Autowired
	@Qualifier("noticeFileService")
	private NoticeFileService noticeFileService;
	
	@Autowired
	@Qualifier("adminMypageService")
	private AdminMypageService adminMypageService;

	// 공지사항 목록
	@GetMapping("/noticeList")
	public String noticeList(Model model, Criteria cri) {
		String member_id="admin";
		String member_role = adminMypageService.adminmypage_authcheck(member_id);
		// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
		// 임시 로그인
		String writer = "aaa";
		
		System.out.println(member_role);

		ArrayList<TB_003VO> list = noticeService.noticeList(writer, cri);

		int total = noticeService.getTotal(writer, cri);
		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("total", total);
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		model.addAttribute("member_role",member_role);

		System.out.println(pageVO.toString());
		System.out.println(total);

		return "notice/noticeList";
	}

	// 공지사항 글 내용
	@GetMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("notice_proper_num") String notice_proper_num,
							   Model model) {
		String member_id="admin";
		String member_role = adminMypageService.adminmypage_authcheck(member_id);
		
		TB_003VO vo = noticeService.noticeDetail(notice_proper_num);
		//
		System.out.println(member_role);
		
		System.out.println("스타트");
		
		Map<TB_016VO,String> file = new HashMap<TB_016VO, String>();
		
		List<TB_016VO> filevo = noticeService.noticeFileDetail(notice_proper_num);
		
		for(int i =0; i< filevo.size();i++) {
			String url = URLEncoder.encode(filevo.get(i).getOriginal_file_name());
			file.put(filevo.get(i), filevo.get(i).getOriginal_file_name());
			filevo.get(i).setOriginal_file_name(url);
			

		}
		System.out.println(filevo.toString());
		
		List<String> file_name = new ArrayList<>();
		
//		for(TB_016VO filepath : filevo) {
//			
//		}
		
		
		
		System.out.println(vo.toString());
		
		model.addAttribute("vo", vo);
		model.addAttribute("filevo", filevo);
		
		model.addAttribute("file",file);
		
		model.addAttribute("member_role",member_role);

		return "notice/noticeDetail";
	}

	
	// 공지사항 작성/등록 페이지
	@GetMapping("/noticeRegist")
	public String noticeRegist() {


		return "notice/noticeRegist";
	}

	// 공지사항 등록요청
	@PostMapping("/noticeRegistForm")
	public String noticeRegistForm(TB_003VO vo, RedirectAttributes ra,
								   @RequestParam("file") List<MultipartFile> list) {

		//System.out.println("1"+vo.toString());

		list = list.stream().filter(t -> t.isEmpty() == false).toList();

		for (MultipartFile file : list) {
			if (file.getContentType().contains("image") == false &&
				file.getContentType().contains("application/pdf") == false &&
				file.getContentType().contains("text/plain") == false &&
				file.getContentType().contains("application/x-hwp") == false) {
				
				//허용되지 않는 MIME타입인 경우 처리
				
				ra.addFlashAttribute("msg", "올바른 파일 형식이 아닙니다");

				return "redirect:/notice/noticeList";
			}
		}

		//파일 업로드하고 저장경로를 리스트로 받음
		List<String> filelist = noticeFileService.noticeFileRegist(list);
		
		//파일리스트와 같이 등록 진행
		int result = noticeService.noticeRegist(vo, filelist);
		
		
		System.out.println("filelist = "+filelist);
		
		System.out.println("등록됨 : "+vo.toString());
		
		String msg = result == 1 ? "등록되었습니다." : "등록실패";

		ra.addFlashAttribute("msg", msg);

		return "redirect:/notice/noticeList";
	}

	// 공지사항 수정 페이지
	@GetMapping("/noticeModify")
	public String noticeModify(@RequestParam("notice_proper_num") String notice_proper_num,
							   Model model) {
		
		TB_003VO vo = noticeService.noticeDetail(notice_proper_num);
		
		model.addAttribute("vo",vo);

		return "notice/noticeModify";
	}
	
	//공지사항 수정(업데이트)
	@PostMapping("/noticeUpdateForm")
	public String noticeUpdate(TB_003VO vo, RedirectAttributes ra, @RequestParam("file") List<MultipartFile> list) {
		list = list.stream().filter(t -> t.isEmpty() == false).toList();

		for (MultipartFile file : list) {
			if (file.getContentType().contains("image") == false &&
				file.getContentType().contains("application/pdf") == false &&
				file.getContentType().contains("text/plain") == false &&
				file.getContentType().contains("application/x-hwp") == false) {
				
				//허용되지 않는 MIME타입인 경우 처리
				
				ra.addFlashAttribute("msg", "올바른 파일 형식이 아닙니다");

				return "redirect:/notice/noticeList";
			}
		}
		
		//파일 업로드하고 저장경로를 리스트로 받음
		List<String> filelist = noticeFileService.noticeFileRegist(list);
		
		//파일리스트와 같이 등록 진행
		int result = noticeService.noticeUpdate(vo, filelist);
		
		String msg = "저장되었습니다.";//왜안됨?
		
		System.out.println(vo.toString());
		
		return "redirect:/notice/noticeList";
	}
	
	//공지사항 삭제
	@GetMapping("/noticeDelete")
	public String noticeDelete(@RequestParam("notice_proper_num") String notice_proper_num, 
							   RedirectAttributes ra) {
		
		
		noticeService.noticeDelete(notice_proper_num);
		
		return "redirect:/notice/noticeList";
	}

}
