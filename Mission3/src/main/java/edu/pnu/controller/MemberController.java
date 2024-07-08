package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }
    //멤버 테이블 조회
    @GetMapping("/members")
    public List<MemberDTO> getAllMember() {
        return memberService.getAllMember();
    }
    //id로 멤버 조회
    @GetMapping("/member")
    public MemberDTO getMemberById(Integer id) {
    	return memberService.getMemberById(id);
    }
    //멤버 추가
    @PostMapping("/member")
	public int addMember(MemberDTO dto) {
		return memberService.addMember(dto);
	}
    //멤버 정보 업데이트
 	@PutMapping("/member")
 	public int updateMember(MemberDTO dto) {
 		return memberService.upDateMember(dto);
 	}
 	
 	//멤버 id로 조회해서 삭제
 	@DeleteMapping("/member")
 	public int deleteMember(MemberDTO dto) {
 		return memberService.deleteMember(dto);
 	}
    
}