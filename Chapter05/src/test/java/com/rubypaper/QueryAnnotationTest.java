package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QueryAnnotationTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testQueryAnnotationTest1() {
		List <Board> boardList = boardRepo.queryAnnotationTest1("title10");
		System.out.println("--> testQueryAnnotationTest1");
		for(Board b : boardList) {
			System.out.println(b);
		}
	}
	
//	@Test
//	@Order(2)
//	public void testQuerryAnnotationTest2() {
//		List<Object[] > boardList = boardRepo.queryAnnotationTest2("title10");
//		System.out.println("-->  testQuerryAnnotationTest2");
//		for(Object[] row : boardList) {
//			System.out.println(Arrays.toString(row));
//		}
//	}
//	
	
//	@Test
//	public void testQuerryAnnotationTest3() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest3("title10");
//		System.out.println("-->  testQuerryAnnotationTest3");
//		for(Object[] row : boardList) {
//			System.out.println(row);
//		}
//	}
}
