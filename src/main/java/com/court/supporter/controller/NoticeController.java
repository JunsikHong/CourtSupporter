package com.court.supporter.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.court.supporter.command.faqVO;
import com.court.supporter.command.noticeVO;
import com.court.supporter.faq.faqService;
import com.court.supporter.notice.noticeService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	@Qualifier("noticeService")
	private noticeService noticeService;

	@Autowired
	@Qualifier("faqService")
	private faqService faqService;

	// 공지사항 목록
	@GetMapping("/noticeList")
	public String noticeList(Model model, Criteria cri) {

		// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
		// 임시 로그인
		String writer = "aaa";

		ArrayList<noticeVO> list = noticeService.noticeList(writer, cri);

		int total = noticeService.getTotal(writer, cri);
		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		System.out.println(pageVO.toString());
		System.out.println(total);

		return "notice/noticeList";
	}

	// 공지사항 글 내용
	@GetMapping("/noticeDetail")
	public String noticeDetail(@RequestParam("notice_proper_num") int notice_proper_num,
							   Model model) {
		
		noticeVO vo = noticeService.noticeDetail(notice_proper_num);
		
		
		
		model.addAttribute("vo", vo);

		return "notice/noticeDetail";
	}

	// 공지사항 작성/등록 페이지
	@GetMapping("/noticeRegist")
	public String noticeRegist(Model model) {

		LocalDate date = LocalDate.now();
		model.addAttribute("date", date);

		return "notice/noticeRegist";
	}

	// 공지사항 등록요청
	@PostMapping("/noticeRegistForm")
	public String noticeRegistForm(noticeVO vo, RedirectAttributes ra,
								   @RequestParam("file") List<MultipartFile> list) {

		System.out.println("1"+vo.toString());

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

		System.out.println("2"+vo.toString());
		int result = noticeService.noticeRegist(vo, list);

		System.out.println("3"+vo.toString());
		
		String msg = result == 1 ? "등록되었습니다." : "등록실패";

		ra.addFlashAttribute("msg", msg);

		return "redirect:/notice/noticeList";
	}

	// 공지사항 수정 페이지
	@GetMapping("/noticeModify")
	public String noticeModify() {

		return "notice/noticeModify";
	}

	// ---------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------

	// faq 목록
	@GetMapping("/faqList")
	public String faqList(Model model, Criteria cri) {

		// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
		// 임시 로그인
		String writer = "aaa";

		ArrayList<faqVO> list = faqService.faqList(writer, cri);

		int total = faqService.getTotal(writer, cri);
		PageVO pageVO = new PageVO(cri, total);

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		System.out.println(pageVO.toString());
		System.out.println(total);

		return "notice/faqList";
	}

	// faq 글 내용
	@GetMapping("/faqDetail")
	public String faqDetail() {

		return "notice/faqDetail";
	}

	// faq 작성/등록 페이지
	@GetMapping("/faqRegist")
	public String faqRegist() {

		return "notice/faqRegist";
	}

	// faq 등록요청
	@PostMapping("/faqRegistForm")
	public String faqRegistForm(faqVO vo, RedirectAttributes ra, 
								@RequestParam("file") List<MultipartFile> list) {

		System.out.println(vo.toString());
		System.out.println(ra.toString());

		list = list.stream().filter(t -> t.isEmpty() == false).toList();

		for (MultipartFile file : list) {
			if (file.getContentType().contains("") == false) {

				ra.addFlashAttribute("", "");

				return "redirect:/notice/faqList";
			}
		}

		int result = faqService.faqRegist(vo, list);

		String msg = result == 1 ? "등록되었습니다." : "등록실패";

		ra.addFlashAttribute("msg", msg);

		return "redirect:/notice/faqList";
	}

	// faq 수정 페이지
	@GetMapping("/faqModify")
	public String faqModify() {

		return "notice/faqModify";
	}

}