package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.service.MemberService;


@Controller
public class Logincontroller {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public void login() {
		System.out.println("login");
	}
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess");
		
	}
	
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user){
		if(user == null) {
			return ResponseEntity.ok("로그인상태가 아닙니당");
		}
		return ResponseEntity.ok(user);
	}
	
	
	
	

}
