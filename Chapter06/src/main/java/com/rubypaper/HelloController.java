package com.rubypaper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HelloController {

//	@GetMapping("/hello1")
//	public void hello() {
//		///WEB-INF/board/hello
//	}
//	
//	@GetMapping("/hello2")
//	public String hello(String name, Model model) {
//		///WEB-INF/board/hello
//		model.addAttribute("name", name);
//		
//		return "hello";
//	}
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프");
	}
	
//	@GetMapping("/hello")
//	public String hello(String name) {
//		ModelAndView mv = new ModelAndView();
//		
//		mv.addObject("name", name);
//		mv.setViewName("hello");
//		return "mv";
//	}
	
	
	
}
