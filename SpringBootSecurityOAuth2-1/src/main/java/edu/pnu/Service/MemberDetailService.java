package edu.pnu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

//인증정보 제공 클래스
//사용자 정보를 데이터베이스에서 조회하고 시큐리티 인증에 사용할 수 있는 UserDetail 객체로 변환
@Service
public class MemberDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memRepo.findById(username) // MemberRepository를 통해서 데이터베이스에서 사용자 조회
				.orElseThrow(()->new UsernameNotFoundException("Not Found"));
		System.out.println(member);
		
		return new User(member.getUsername(), member.getPassword(),  
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		// 사용자의 역할을 Authority 객체로 변환해서 권한목록 생성하고, 이 목록은 시큐리티에서 권환확인할때 사용
	}
	

}
