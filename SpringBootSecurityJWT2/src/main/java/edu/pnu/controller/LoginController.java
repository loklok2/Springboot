package edu.pnu.controller;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import jakarta.servlet.http.HttpServletResponse;


@Controller
//컨트롤러 방식
public class LoginController {

	@Autowired
	private AuthenticationConfiguration authConfiguration;


	@PostMapping("/login")
	public ResponseEntity<?> loginProc(@RequestBody Member member, HttpServletResponse response) {
		try {
			//security에게 자격 증명 요청에 필요한 객체 생성
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());

			//인증 진행 -> UserdetailsService의 loadUserByUsername에서 DB로부터 ㅅ용자 정보를 읽어온 뒤
			// 사용자 입력 정보와 비교한 뒤 자격 증명에 성공하면 Authentication객체를 만들어서 리턴

			Authentication auth = authConfiguration.getAuthenticationManager().authenticate(authToken);
			String token = JWT.create()
					.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10000))
					.withClaim("username", member.getUsername())
					.sign(Algorithm.HMAC256("edu.pnu.jwt"));
			response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			response.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok("success");
		} catch(Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
}
