package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController() {
        memberService = new MemberService();
    }
    
    @GetMapping("/members")
    public List<MemberDTO> getAllMember() {
        return memberService.getAllMember();
    }
    
    @GetMapping("/member")
    public MemberDTO getMemberById(Integer id) {
    	return memberService.getMemberById(id);
    }
    
    @PostMapping("/member")
	public MemberDTO addMember(MemberDTO dto) {
		return memberService.addMember(dto);
	}
    
    
}