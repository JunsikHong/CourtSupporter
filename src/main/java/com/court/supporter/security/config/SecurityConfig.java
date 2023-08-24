package com.court.supporter.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		//기본로그인 방식(form기반), 세션, 베이직 인증 csrf토큰 전부 사용x
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable(); //Authorization : 아이디 형식으로 넘어오는 basic 인증 사용x 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션인증 기반을 사용하지 않고, JWT 사용해서 인증
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); //모든 요청 전부 허용
		
		
		
		return http.build();
	}
	
}
