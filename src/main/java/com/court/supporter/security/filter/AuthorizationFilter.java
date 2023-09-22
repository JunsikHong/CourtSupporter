package com.court.supporter.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/*")
public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
       

        // 요청 경로 확인
        String requestURI = httpRequest.getRequestURI();
        
        if (!requestURI.startsWith("/css/") && !requestURI.startsWith("/img/") && !requestURI.startsWith("/js/")) {
            // 세션에서 사용자 권한 가져오기
            String role = (String) httpRequest.getSession().getAttribute("member_role");
            // 세션에서 토큰 가져오기 (로그인했을 때만 발급)
            String token = (String) httpRequest.getSession().getAttribute("token");
                        
            // 요청 경로 확인
            System.out.println(role);
            System.out.println(requestURI);
            
            //비회원일 때 로그인으로 리다이렉트
            if (token == null) {
            	if(requestURI.contains("/application") || requestURI.contains("usermypage") ||
            		requestURI.equals("/announce/announceReg") || requestURI.equals("/announce/announceRegForm") || requestURI.equals("/announce/announceModify") ||
            		requestURI.equals("/announce/announceModifyForm") || requestURI.equals("/announce/announceDeleteForm") ||
            		requestURI.equals("/notice/noticeRegist") || requestURI.equals("/notice/noticeRegistForm") || requestURI.equals("/notice/noticeModify") ||
            		requestURI.equals("/notice/noticeUpdateForm") || requestURI.equals("/notice/noticeDelete") ||
            		requestURI.equals("/faq/faqRegist") || requestURI.equals("/faq/faqRegistForm") || requestURI.equals("/faq/faqModify") ||
            		requestURI.equals("/faq/faqUpdateForm") || requestURI.equals("/faq/faqDelete") ||
            		requestURI.equals("/adminmypage/adminmypage_auth_manage") || requestURI.equals("/adminmypage/adminmypage_evaluationlist") || requestURI.equals("/adminmypage/adminmypage_evaluationdetail") ||
            		requestURI.equals("/adminmypage/adminmypage_evaluation") || requestURI.equals("/adminmypage/adminmypage_evaluation_popup")) {
            			httpResponse.sendRedirect("http://localhost:3000/login");
            			return;
            	}
            //회원일 때 해당 권한이 아니면 홈으로 리다이렉트 
            } else {
            	//신청 페이지 / 사용자 마이페이지는 사용자가 아닐 때 들어갈 수 없다
            	if ((requestURI.contains("/application") || requestURI.contains("/usermypage")) && !"ROLE_USER".equals(role)) {
            		httpResponse.sendRedirect("/");
            		return;
            	//전체 관리자가 아닐 때 들어갈 수 없다
            	} else if ((requestURI.equals("/announce/announceReg") || requestURI.equals("/announce/announceRegForm") || requestURI.equals("/announce/announceModify") ||
            		requestURI.equals("/announce/announceModifyForm") || requestURI.equals("/announce/announceDeleteForm") ||
            		requestURI.equals("/notice/noticeRegist") || requestURI.equals("/notice/noticeRegistForm") || requestURI.equals("/notice/noticeModify") ||
            		requestURI.equals("/notice/noticeUpdateForm") || requestURI.equals("/notice/noticeDelete") ||
            		requestURI.equals("/faq/faqRegist") || requestURI.equals("/faq/faqRegistForm") || requestURI.equals("/faq/faqModify") ||
            		requestURI.equals("/faq/faqUpdateForm") || requestURI.equals("/faq/faqDelete") ||
            		requestURI.equals("/adminmypage/adminmypage_auth_manage")) && !"ROLE_ADMIN".equals(role)) {
            		httpResponse.sendRedirect("/");
            		return;
            	//관리자가 아닐 때 들어갈 수 없다
            	} else if ((requestURI.equals("/adminmypage/adminmypage_evaluationlist") || requestURI.equals("/adminmypage/adminmypage_evaluationdetail") ||
            		requestURI.equals("/adminmypage/adminmypage_evaluation") || requestURI.equals("/adminmypage/adminmypage_evaluation_popup")) && (!"ROLE_ADMIN".equals(role) || !"ROLE_JURIS".equals(role) || !"ROLE_COURT".equals(role))) {
            		httpResponse.sendRedirect("/");
            		return;
            	}
            	
            }
            
        }
        chain.doFilter(request, response);
	}

}
