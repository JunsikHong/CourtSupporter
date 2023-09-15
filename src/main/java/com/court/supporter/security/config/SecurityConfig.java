package com.court.supporter.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.court.supporter.security.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	//비밀번호 암호화 객체
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		
		//기본로그인 방식(form기반), 세션, 베이직 인증 csrf토큰 전부 사용x
		http.httpBasic().disable(); //Authorization : 아이디 형식으로 넘어오는 basic 인증 사용x 
		http.csrf().disable();
		http.formLogin().disable();
		http.cors().configurationSource(corsConfigurationSource());
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //세션인증 기반을 사용하지 않고, JWT 사용해서 인증
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeHttpRequests(auth -> auth.antMatchers("/announce/announceReg", "announce/announceRegForm", "/announce/announceModify",
													   		"/announce/announceModifyForm", "/announce/announceDeleteForm",
													   		"/notice/noticeRegist", "/notice/noticeRegistForm", "/notice/noticeModify",
													   		"/notice/noticeUpdateForm", "/notice/noticeDelete",
													   		"/faq/faqRegist", "/faq/faqRegistForm", "/faq/faqModify",
													   		"/faq/faqUpdateForm", "/faq/faqDelete",
													   		"/adminmypage/adminmypage_auth_manage").hasRole("ADMIN") // 전체 관리자만
											   .antMatchers("/adminmypage/adminmypage_evaluationlist", "/adminmypage/adminmypage_evaluationdetail",
													   		"/adminmypage/adminmypage_evaluation_popup", "/adminmypage/adminmypage_evaluation").hasAnyRole("JURIS", "COURT", "ADMIN") // 관리자
											   .antMatchers("/usermypage/**", "/application/**").hasRole("USER") // 사용자
											   .anyRequest().permitAll()); //모든 요청 전부 허용
		
		http.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("Authorization", "Refresh")
			.permitAll();
		return http.build();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
}