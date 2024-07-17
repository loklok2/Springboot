package com.rubypaper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class DataInitializeTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	
	public void testDataInsert() {
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
		
		for(int i = 0; i <= 3; i++) {
			Board b1 = Board.builder()
					.writer("둘리")
					.title("둘리가 등록한 게시글" + i)
					.content("둘리가 등록한 게시글 내용" + i)
					.build();
			boardRepo.save(b1);
		}
		
		for(int i = 0; i <= 3; i++) {
			Board b2 = Board.builder()
					.writer("도우너")
					.title("도우너가 등록한 게시글" + i)
					.content("도우너가 등록한 게시글 내용" + i)
					.build();
			boardRepo.save(b2);
		}
	}
	

}
