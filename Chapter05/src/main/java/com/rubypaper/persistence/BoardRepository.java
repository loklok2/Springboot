package com.rubypaper.persistence;  //default로 persistence 패키지 사용함

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {//<엔티티클래스타입, 식별자타입(@id 식별타입)>

}
