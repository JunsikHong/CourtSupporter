package com.court.supporter.security.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.court.supporter.security.jwt.JwtValidator;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtValidator jwtValidator;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Optional<String> token = Optional.ofNullable(getTokensFromHeader(request));
		
		token.ifPresent(t -> {
			Authentication authentication = jwtValidator.getAuthentication(t);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		});
		
		filterChain.doFilter(request, response);
	}
	
	private String getTokensFromHeader(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

}
