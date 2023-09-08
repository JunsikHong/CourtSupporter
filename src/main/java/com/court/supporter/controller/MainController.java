package com.court.supporter.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

	@GetMapping("/")
	public String main(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println(session);
		
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 System.out.println(authentication);
		 
		    if (authentication != null) {
		        // 인증된 사용자의 세션
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof UserDetails) {
		            UserDetails userDetails = (UserDetails) principal;
		            System.out.println(userDetails.getUsername());
		            // 여기서 userDetails를 사용할 수 있습니다.
		        }
		    }
		return "/main";
	}
}