package com.court.supporter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.court.supporter.security.jwt.JwtValidator;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

	private final JwtValidator jwtValidator;
	

	@GetMapping( {"","main"} )
	public String main(@CookieValue(name = "Authorization", required = false) String token,
			HttpServletRequest request) {
		// 로그인한 상태
		if (token != null) {
			HttpSession session = request.getSession();
			session.setAttribute("token", token);
		}
		
		

		return "/main";
	}

	@GetMapping("/guide/guideInfo")
	public String guideInfo(@CookieValue(name = "Authorization", required = false) String token,
			HttpServletRequest request) {
		// 로그인한 상태
		if (token != null) {
			HttpSession session = request.getSession();
			session.setAttribute("token", token);
		}

		return "guide/guideInfo";
	}
	
	@GetMapping("/guide/guideProcedure")
	public String guideProcedure(@CookieValue(name = "Authorization", required = false) String token,
			HttpServletRequest request) {
		// 로그인한 상태
		if (token != null) {
			HttpSession session = request.getSession();
			session.setAttribute("token", token);
		}
		
		return "guide/guideProcedure";
	}
}