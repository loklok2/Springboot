package edu.pnu.util;


import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final MemberRepository memRepo;
	private final PasswordEncoder encoder;
	
	@Override //OAuth2 인증이 성공되면 호출되는 메서드
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
		
		log.info("OAuth2SuccessHandler:onAuthenticationSuccess");
		OAuth2User user = (OAuth2User)authentication.getPrincipal(); //인증된 사용자로부터 OAuth2User 객체 가져오기
		//임의의 사용자 만들어서 서버에 저장
		String username = CustomMyUtil.getUsernameFromOAuth2User(user); // 사용자 이름 추출 및 확인
		if (username == null) {
			log.error("onAuthenticationSuccess: Cannot generate username from oauth2user!!");
			throw new ServletException("Cannot generate username from oauth2user!");
		}
		log.info("onAuthenticationSuccess:" + username);
		memRepo.save(Member.builder() //사용자 정보저장
				.username(username)
				.password(encoder.encode("1a2s3d4f"))
				.role(Role.ROLE_MEMBER)
				.enabled(true)
				.build());
		String jwtToken = JWTUtil.getJWT(username); //JWTUtil.java 호출해서 JWT 토큰 생성
		response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken); // 생성된 토큰 응답해더의 AUTHORIZATION필드에 추가해서 전달 
		response.sendRedirect("/loginSuccess");				// 로그인 성공을  핸들러에서 바로 리다이렉트하기
		
	}
}
