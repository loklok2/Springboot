package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	private String title;
	
	@Column(updatable = false)
	private String writer;
	
	
	private String content;
	
//	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "date default Current_date")
	private Date createDate;
	
//	@Column(insertable = false, updatable = false, columnDefinition = "number default 0")
	@Column(insertable = false, columnDefinition = "long default 0")
	private Long cnt;
}
