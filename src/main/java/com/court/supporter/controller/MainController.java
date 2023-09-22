package com.court.supporter.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.court.supporter.security.DefaultUserDetails;
import com.court.supporter.security.jwt.JwtValidator;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/")
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
			
			Authentication authentication = jwtValidator.getAuthentication(token);
			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
			
			List<GrantedAuthority> member_role = (List<GrantedAuthority>) userDetails.getAuthorities();
//			System.out.println(member_role.get(0).getAuthority());
			
			
			session.setAttribute("member_role", member_role.get(0).getAuthority());
			
//			Authentication authentication = jwtValidator.getAuthentication(token);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
//		String jwt = (String) session.getAttribute("token");
//
//		//로그인 했을 때(= 토큰이 있을 때)
//		if (jwt != null) {
//			Authentication authentication = jwtValidator.getAuthentication(jwt);
//			DefaultUserDetails userDetails = (DefaultUserDetails) authentication.getPrincipal();
//
//			//token에서 member_proper_num 가져오기
//			String member_proper_num = userDetails.getUsername();
//			//token에서 member_role 가져오기
//			//role은 Collection으로 받아와서 배열로 바꾼 뒤 roles[0]를 가져오면 됩니다 :)
//			Collection<? extends GrantedAuthority> member_role = userDetails.getAuthorities();
//			Object[] roles = member_role.toArray();
//			System.out.println("ROLE_ADMIN".equals(roles[0].toString()));
//		}

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