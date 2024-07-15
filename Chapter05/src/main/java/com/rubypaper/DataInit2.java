package com.rubypaper;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //생성자 알아서 해서 쓰겠다
@Component
public class DataInit2 implements ApplicationRunner{  //서버 구동 시 빈객체부터 다 올라간다음 첫 자동실행 인터페이스
	private final BoardRepository boardRepo;
	private final MemberRepository memberRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Member m1 = Member.builder()
				.id("member1")
				.password("member111")
				.name("둘리")
				.role("User").build();
		memberRepo.save(m1);
		
		Member m2 = Member.builder()
				.id("member2")
				.password("member222")
				.name("도우너")
				.role("Admin").build();
		memberRepo.save(m2);
		
		for(int i = 0; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m1)
					.build());
		}
		
		for(int i = 0; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m2)
					.build());
		}
		
	}
		
		
}
