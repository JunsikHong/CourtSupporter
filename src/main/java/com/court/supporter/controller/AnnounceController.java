package com.court.supporter.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.court.supporter.announce.service.AnnounceService;
import com.court.supporter.command.TB_002VO;
import com.court.supporter.util.Criteria;
import com.court.supporter.util.PageVO;

@Controller
@RequestMapping("/announce")
public class AnnounceController {
	
	
	@Autowired
	@Qualifier("announceService")
	private AnnounceService announceService;

	
	//공고 등록 화면으로 이동
	@GetMapping("/announceReg")
	public String announceRegist() {
		return "announce/announceReg";
	}	

	//등록시 목록으로 이동
	@PostMapping("/announceRegForm")
	public String regist(TB_002VO vo) {			
		System.out.println(vo.getAnnounce_start_date());		
		System.out.println(vo.getAnnounce_end_date());
		System.out.println(vo.toString());
		
		//임의로 관리자 및 재판조력자 고유번호 설정
		vo.setAdmin_proper_num(1);
		vo.setTrial_fcltt_proper_num(23);
		
		announceService.announceRegist(vo);
		return "redirect:/announce/announceList";
	}
	
	//공고 목록 게시판 조회 + 검색
	@GetMapping("/announceList")
	public String announceList(Model model, Criteria cri) { //String keyword
	
		//검색어 설정
//		cri.setSearchLocation(keyword);
		
		List<TB_002VO> list = announceService.announce_getList(cri);				
		int total = announceService.announce_getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);   
		
		System.out.println(cri.getSearchLocation());
		//System.out.println(model.toString());
		
		return "announce/announceList";
	}
	
	
	//공고 게시글(공고 상세 화면)
	@GetMapping("/announceDetail")
	public String announceDetail(@RequestParam("announce_proper_num") int announce_proper_num, Model model) {
		
		TB_002VO vo = announceService.getDetail(announce_proper_num);
		model.addAttribute("vo", vo);
		//System.out.println(vo.toString());
		
		return "announce/announceDetail";
	}
	
	//공고 수정 화면
	@GetMapping("/announceModify")
	public String announceModify(/* @RequestParam("announce_proper_num") int announce_proper_num, Model model */) {
		/*
		 * TB_002VO vo = announceService.getDetail(announce_proper_num);
		 * model.addAttribute("vo", vo); System.out.println(vo.toString());
		 */
		return "announce/announceModify";
	}
	
	//공고 수정후 게시물로 이동
	@PostMapping("/modifyForm")
	public String modify(TB_002VO vo, RedirectAttributes ra) {
		
		System.out.println(vo); //확인
		
		int result = announceService.announceModify(vo);
		String msg = result == 1 ? "수정 완료" : "수정 실패";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/announce/announceDetail"; //or List
	}
	
	
	//공고 삭제	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("announce_proper_num") int announce_proper_num, RedirectAttributes ra) {
		
		//announceService.announceDelete(announce_proper_num);
		ra.addFlashAttribute("deleteForm", "삭제하시겠습니까?");
		
		return "redirect:/announce/announceList";
	}
	
	
}
