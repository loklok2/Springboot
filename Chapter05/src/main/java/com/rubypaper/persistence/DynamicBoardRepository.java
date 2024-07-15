package com.rubypaper.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rubypaper.domain.Board;

// QueryDSL을 사용하기 위해서 QuerydslPredicateExecutor 인터페이스를 추가로 상속
public interface DynamicBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

}
