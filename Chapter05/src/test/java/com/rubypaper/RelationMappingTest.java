package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RelationMappingTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Test
	@Order(1)
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member2").get();
		System.out.println("===========================");
		System.out.println(member.getName() + "가(이) 저장한 게시글 목록");
		System.out.println("===========================");
		List<Board> list = member.getBoardList();
		for(Board board : list) {
			System.out.println(board);
		}
		
		
	}
	
	
	@Test
	@Order(2)
	public void testManyToOneInsert() {
		Member m1 = Member.builder()
				.id("member1")
				.password("member111")
				.name("둘리")
				.role("User").build();
		//memberRepo.save(m1);
		
		
		Member m2 = Member.builder()
				.id("member2")
				.password("member222")
				.name("도우너")
				.role("Admin").build();
		//memberRepo.save(m2);
		
		for(int i = 0; i <= 100; i++) {
			Board b1 = Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m1)
					.build();
		}
		memberRepo.save(m1);
		
		for(int i = 0; i <= 100; i++) {
			Board b2 = Board.builder()
					.title("title" + i)
					.content("content" + i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.member(m2)
					.build();
		}
		memberRepo.save(m2);
	}
	
	@Test
	@Order(3)
	public void testCascadeDelete() {
		memberRepo.deleteById("member2");
	}
	
	

}
