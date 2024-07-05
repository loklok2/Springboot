package edu.pnu.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	// 검색 (Read = select)
	@GetMapping("/members")
	public List<MemberVO> getAllMember() {
		return memberService.getAllMember();
	}
	
	// 검색 (Read = select)
	@GetMapping("/member")
	public MemberVO getMemberById(@RequestParam Integer id) {
		return memberService.getMemberById(id);
	}
	
	// 입력(Create - insert)
	@PostMapping("/member")
	public MemberVO addMember(@RequestBody MemberVO memberVO) {
		return memberService.addMember(memberVO);
	}
	
	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMemberVO(@RequestBody MemberVO memberVO) {
		return memberService.updateMember(memberVO);
	}
	
	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public int removeMemberVO(@RequestParam Integer id) {
		return memberService.removeMember(id);
	}
}

	
	