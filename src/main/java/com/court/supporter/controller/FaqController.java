package com.court.supporter.controller;

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

import com.court.supporter.command.TB_004VO;
import com.court.supporter.faq.service.faqService;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	@Qualifier("faqService")
	private faqService faqService;
	
	// faq 목록
		@GetMapping("/faqList")
		public String faqList(Model model, Criteria cri) {

			// 로그인 기능을 만들어서 계정 분류에 따라 버튼이 다르게 나와야함
			// 임시 로그인
			String writer = "aaa";

			ArrayList<TB_004VO> list = faqService.faqList(writer, cri);

			int total = faqService.getTotal(writer, cri);
			PageVO pageVO = new PageVO(cri, total);

			model.addAttribute("list", list);
			model.addAttribute("pageVO", pageVO);

			System.out.println(pageVO.toString());
			System.out.println(total);

			return "faq/faqList";
		}

		// faq 글 내용
		@GetMapping("/faqDetail")
		public String faqDetail() {

			return "faq/faqDetail";
		}

		// faq 작성/등록 페이지
		@GetMapping("/faqRegist")
		public String faqRegist() {

			return "faq/faqRegist";
		}

		// faq 등록요청
		@PostMapping("/faqRegistForm")
		public String faqRegistForm(TB_004VO vo, RedirectAttributes ra, 
									@RequestParam("file") List<MultipartFile> list) {

			System.out.println(vo.toString());
			System.out.println(ra.toString());

			list = list.stream().filter(t -> t.isEmpty() == false).toList();

			for (MultipartFile file : list) {
				if (file.getContentType().contains("") == false) {

					ra.addFlashAttribute("", "");

					return "redirect:/faq/faqList";
				}
			}

			int result = faqService.faqRegist(vo, list);

			String msg = result == 1 ? "등록되었습니다." : "등록실패";

			ra.addFlashAttribute("msg", msg);

			return "redirect:/faq/faqList";
		}

		// faq 수정 페이지
		@GetMapping("/faqModify")
		public String faqModify() {

			return "faq/faqModify";
		}

}