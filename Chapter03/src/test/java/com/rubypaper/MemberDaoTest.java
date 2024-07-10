package com.rubypaper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberDaoTest {
	
	@Autowired
	private MemberDAO memberDao;
	
	@Test
	public void test() {
		List<MemberDTO> list = memberDao.getAllMember();
		for (MemberDTO m: list)
			System.out.println(m.toString());
	}

}
