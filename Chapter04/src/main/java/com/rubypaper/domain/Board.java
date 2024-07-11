package com.rubypaper.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Entity  //jpa가 db랑 연결시켜줌
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Board {
	@Id //primary key 사용하니까
	@GeneratedValue(strategy= GenerationType.IDENTITY) //autoincrement와 같은 의미// h2,mysql만 사용가능
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE) //시간 or 날짜 관련 설정, db에서는 다양한 date가 존재하여 여기서 설정
	private Date createDate;
	private Long cnt;
	

}
