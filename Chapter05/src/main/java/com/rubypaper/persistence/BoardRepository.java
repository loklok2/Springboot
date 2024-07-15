package com.rubypaper.persistence;  //default로 persistence 패키지 사용함

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {//<엔티티클래스타입, 식별자타입(@id 식별타입)>
	List<Board> findByTitle(String title);
	
	List<Board> findByContentContaining(String searchkeyword);
	
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	List<Board> findByTitleContainingOrderBySeqDesc(String searchkeyword);
		
	List<Board> findByTitleContaining(String searchkeyword, Pageable paging);
	
	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String serchkeyword);
	
	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b "
//			+"WHERE b.title like %?1% "
//			+ "ORDER BY b.seq DESC")
//	List<Object[]> queryAnnotationTest2(@Param("searchkeyword")String serchkeyword);
	
//	@Query(value="select seq, title, writer, createdate "
//			+ "from board where title like '%'\\?1\\'%' "
//			+ "order by desc", nativeQuery = true)
//	List<Object[]> queryAnnotationTest3(String searchekeyword);
}