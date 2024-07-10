package com.rubypaper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BoardController {
	
	public BoardController() {
		System.out.println("====> BoardController 생성");
		
		
		log.error("error");
		log.warn("warn");
		log.info("BoardController 생성");
		log.debug("debug");
		log.trace("trace");
	}
	
	
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	@GetMapping("/getBoard")
	public String getBorad(String writer) {
		return "테스터"+ writer;
	}

}
