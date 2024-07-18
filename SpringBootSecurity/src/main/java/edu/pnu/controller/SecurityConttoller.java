package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //@Controller에서는 리턴이 'View'의 이름을 리턴으로 호출 ex)member.html
public class SecurityConttoller {
	
	@GetMapping({"/","/index"})
	public String index() {
		System.out.println("index 요청");
		return "index";
	}
	
	@GetMapping("/member")
	public void member() {
		System.out.println("Member 요청");
	}
	
	@GetMapping("/manager")
	public void manager() {
		System.out.println("Manager 요청");
	}
	
	@GetMapping("/admin")
	public void admin() {
		System.out.println("Admin 요청");
	}
	
//	@GetMapping("/loginSuccess")
//	public void loginSuccess() {
//		System.out.println("loginSuccess 요청");
//	}
	
}
