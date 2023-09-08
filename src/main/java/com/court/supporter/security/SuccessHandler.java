package com.court.supporter.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.court.supporter.security.jwt.JwtProvider;
import com.court.supporter.security.jwt.JwtToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final JwtProvider jwtProvider;
	
	@Value("${client.url}")
	private String clientUrl;
	
	@Value("${client.endpoint}")
	private String redirectEndPoint;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		DefaultUserDetails defaultUserDetails = (DefaultUserDetails) authentication.getPrincipal();
		JwtToken jwtToken = jwtProvider.createJwtToken(defaultUserDetails);
		// http://localhost:3000/success?token=sjfhvaklwefjagfkjh1467i1dgkuahdkqyfk3i7fyk
		getRedirectStrategy().sendRedirect(request, response, clientUrl + "/" + redirectEndPoint + "?token=" + jwtToken.getAccessToken());
	}

	
}
