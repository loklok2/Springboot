package com.rubypaper.domain;

import jakarta.persistence.Entity;

@Entity
public class Board {
	private Long seq;
	private String writer;
	private String content;
	private String title;
	

}
