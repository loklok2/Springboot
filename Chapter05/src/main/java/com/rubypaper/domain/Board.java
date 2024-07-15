package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //데이터베이스와 1:1 매칭
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	//private String writer;
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate; //spring에서는 _ 타입으로 된다
	private Long cnt;

	@ManyToOne //Many는 나 자신 board, one은 Member
	@JoinColumn(name="MEMBER_ID") //Member 테이블의 id 필드와 연결될 필드명
	private Member member; //왜래키
}
