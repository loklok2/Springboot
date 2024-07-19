package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;


public class BoardUserDetailService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memRepo.findById(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
		
		System.out.println(member);
		
		return new User(member.getUsername(), member.getPass(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		
	}
	

}
