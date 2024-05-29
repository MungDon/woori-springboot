package com.example.sbp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration			// 이 파일이 스프링 환경 설정 파일이라는것을 의미
@EnableWebSecurity	// 모든 요청 URL 이 스프링 시큐리티의 제어를 받도록 만든다
public class SecurityConfig {

	@Bean // 빈객체주입
	// 필터 체인을 정의하는 메서드
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
				// 요청을 허용할지 결정하는 설정
				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
						//모든 요청을 허용한다는 의미
						.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
				.csrf((csrf)->csrf
							.ignoringRequestMatchers(new AntPathRequestMatcher
									("/h2-console/**")))
				.headers((header) -> header
						.addHeaderWriter(new XFrameOptionsHeaderWriter(
								XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
				.formLogin((formLogin) -> formLogin
								.loginPage("/user/login")	
								.passwordParameter("pw") //default = password, username
								.defaultSuccessUrl("/question/list",true))
				;
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
		
	}
}
