package com.rubypaper;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //생성자 알아서 해서 쓰겠다
//@Component
public class DataInit implements ApplicationRunner{  //서버 구동 시 빈객체부터 다 올라간다음 첫 자동실행 인터페이스
	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for(int i = 0; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					//.writer("member1")
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build());
		}
		
		for(int i = 0; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					//.writer("member2")
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build());
		}
		
	}
		
		
}
