package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf->csrf.disable()); // csrf보호 비활성화
		
		http.authorizeHttpRequests(auth->auth //요청으로 들어온 사용자에게 저장된 정보를 매치해서 권한 넘겨줌
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.formLogin(frmLogin->frmLogin.disable()); //form을 이용한 로그인을 사용안함 
		http.httpBasic(basic->basic.disable());			//http Basic 인증방식 사용안함
		
		// 세션을 유지 하지 않을 것을 설정 -> url 호출 뒤 응답할 때 까지만 유지되고, 응답 후에 삭제된다는 의미
		// JWT로 로그인하면 세션 자체를 사용안함.
		http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		//스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가한다.
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		return http.build();
	}
}
