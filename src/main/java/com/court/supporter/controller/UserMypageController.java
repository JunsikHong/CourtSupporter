package com.court.supporter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usermypage")
public class UserMypageController {

	// 사용자 정보 화면
	@GetMapping("/usermypage")
	public String usermypage() {

		return "/usermypage/usermypage";
	}

	// 사용자 정보 수정
	@GetMapping("/usermypage_modify")
	public String usermypage_modify() {

		return "/usermypage/usermypage-modify";
	}

	// 사용자 탈퇴
	@GetMapping("/usermypage_withdrawl")
	public String usermypage_withdrawl() {

		return "/usermypage/usermypage-withdrawl";
	}

	// 사용자 신청 현황 리스트
	@GetMapping("/usermypage_applicationlist")
	public String usermypage_applicationlist() {

		return "/usermypage/usermypage-applicationlist";
	}

	// 사용자 신청 현황 상세
	@GetMapping("/usermypage_applicationdetail")
	public String usermypage_applicationdetail() {

		return "/usermypage/usermypage-applicationdetail";
	}

	// 사용자 활동 내역 리스트
	@GetMapping("/usermypage_activitylist")
	public String usermypage_activitylist() {

		return "/usermypage/usermypage-activitylist";
	}

	// 사용자 활동 내역 상세
	@GetMapping("/usermypage_activitydetail")
	public String usermypage_activitydetail() {

		return "/usermypage/usermypage-activitydetail";
	}

	// 사용자 활동 내역 수정
	@GetMapping("/usermypage_activitymodify")
	public String usermypage_activitymodify() {

		return "/usermypage/usermypage-activitymodify";
	}

	// 사용자 중지
	@GetMapping("/usermypage_pause")
	public String usermypage_pause() {

		return "/usermypage/usermypage-pause";
	}

	// 사용자 중지 상세
	@GetMapping("/usermypage_pausedetail")
	public String usermypage_pausedetail() {

		return "/usermypage/usermypage-pausedetail";
	}

	// 사용자 해제
	@GetMapping("/usermypage_cancel")
	public String usermypage_cancel() {

		return "/usermypage/usermypage-cancel";
	}
}
