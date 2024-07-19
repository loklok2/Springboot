package edu.pnu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 해당 클래스가 설정 클래스라고 정의//@Bean이랑 세트로 사용한다
@EnableWebSecurity // SpringSecurity에서 필요한 객체를 자동으로 생성
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean // 이 메서드가 리턴하는 객체를 loC 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(security->security  //접근권한 설정
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		
		http.csrf(cf->cf.disable()); //csrf 보호 비활성화(사이트간 요청 위조) 지금은 사용하지 않아서 disable
		
		
//		http.formLogin(form->{});
		http.formLogin(form-> form.loginPage("/login").defaultSuccessUrl("/loginSuccess", true));
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
		
		http.logout(logout->logout
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login"));
		
		
		//헤더를 비활성화하는 설정을 나타냅니다. 
		//이 설정을 적용하면 웹 페이지가 어떤 출처의 페이지에서도 프레임으로 포함될 수 있게 됩니다. 
		//즉, 프레임 내에서 웹 페이지가 표시될 수 있습니다.
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		
		return http.build();
		
	
	}
	
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("{noop}abcd")    //->{noop}: NO operation 비밀번호가 암호화 되어있지 않다는 의미
//		.roles("MANAGER");
//		auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}abcd")
//		.roles("ADMIN");
//	}
}
