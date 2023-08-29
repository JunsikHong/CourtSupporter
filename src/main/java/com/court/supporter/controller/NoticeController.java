package com.court.supporter.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.court.supporter.command.faqVO;
import com.court.supporter.command.noticeVO;
import com.court.supporter.faq.faqService;
import com.court.supporter.notice.noticeService;
import com.court.supporter.util.CpCriteria;
import com.court.supporter.util.CpPageVO;

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
	public String noticeList(Model model, CpCriteria cri) {

		// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
		// 임시 로그인
		String writer = "aaa";

		ArrayList<noticeVO> list = noticeService.noticeList(writer, cri);

		int total = noticeService.getTotal(writer, cri);
		CpPageVO pageVO = new CpPageVO(cri, total);

		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);

		System.out.println(pageVO.toString());
		System.out.println(total);

		return "notice/noticeList";
	}

	// 공지사항 글 내용
	@GetMapping("/noticeDetail")
	public String noticeDetail() {

		return "notice/noticeDetail";
	}

	// 공지사항 작성/등록
	@GetMapping("/noticeRegist")
	public String noticeRegist() {

		return "notice/noticeRegist";
	}

	// 공지사항 수정
	@GetMapping("/noticeModify")
	public String noticeModify() {

		return "notice/noticeModify";
	}

	// faq 목록
	@GetMapping("/faqList")
	public String faqList(Model model, CpCriteria cri) {

		// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
		// 임시 로그인
		String writer = "aaa";

		ArrayList<faqVO> list = faqService.faqList(writer, cri);

		int total = faqService.getTotal(writer, cri);
		CpPageVO pageVO = new CpPageVO(cri, total);

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

	// faq 작성/등록
	@GetMapping("/faqRegist")
	public String faqRegist() {

		return "notice/faqRegist";
	}

	// faq 수정
	@GetMapping("/faqModify")
	public String faqModify() {

		return "notice/faqModify";
	}

}
