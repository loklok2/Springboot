package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testFindByTitle() {
		List<Board> list = boardRepo.findByTitle("title10");
		System.out.println("--> testFindbyTitle");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	@Order(2)
	public void testByContentContaining() {
		List<Board> boardlist = boardRepo.findByContentContaining("5");
		System.out.println("--> testByContentContaining");
		for(Board b : boardlist) {
			System.out.println(b);
		}
	}
	
	@Test
	@Order(3)
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("5", "7");
		System.out.println("--> testFindByTitleContainingOrContentContaining");
		for(Board b : boardList) {
			System.out.println(b);
		}
	}
	
	@Test
	@Order(4)
	public void testFindByTitleContainingOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("17");
		System.out.println("--> testFindByTitleContainingOrderBySeqDesc");
		for(Board b : boardList) {
			System.out.println(b);
		}
	}
	
	@Test
	@Order(5)
	public void testFindByTitleContaining() {
		Pageable paging = PageRequest.of(0, 5);
		List <Board> boardList = boardRepo.findByTitleContaining("title", paging);
		System.out.println("--> testFindByTitleContaining");
		for(Board b : boardList) {
			System.out.println(b);
		}
	}
	
	
	
	

}
