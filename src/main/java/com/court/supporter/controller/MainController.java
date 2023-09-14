package com.court.supporter.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.court.supporter.security.jwt.JwtValidator;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final JwtValidator jwtValidator;

	@GetMapping("/")
	public String main(@CookieValue(name="Authorization", required=false) String token,
						HttpServletRequest request) {
		//로그인한 상태
		if (token != null) {
			HttpSession session = request.getSession();
			session.setAttribute("token", token);
		}
		
		return "/main";
	}
	
//	@PostMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("token");
//		
//		// 쿠키에서 Authorization과 Refresh 토큰 삭제
//	    Cookie[] cookies = request.getCookies();
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("Authorization") || cookie.getName().equals("Refresh")) {
//	                cookie.setValue("");
//	                cookie.setPath("/");
//	                cookie.setMaxAge(0); // 쿠키 만료 시간을 0으로 설정하여 삭제
//	                response.addCookie(cookie);
//	            }
//	        }
//	    }
//		return "redirect:/";
//	}
}