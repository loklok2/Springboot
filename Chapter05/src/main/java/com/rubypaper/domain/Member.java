package com.rubypaper.domain;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity //데이터베이스와 1:1 매칭
public class Member {
	@Id //entity인 경우 
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	
	@JsonIgnore
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "member", fetch=FetchType.EAGER, cascade = CascadeType.ALL) //board에 있는 pk와 연결
	private List<Board> boardList = new ArrayList<Board>();

}
