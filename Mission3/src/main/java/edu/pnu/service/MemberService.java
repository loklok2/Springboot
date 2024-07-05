package edu.pnu.service;


import java.util.List;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberDTO;


public class MemberService{
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
    public List<MemberDTO> getAllMember() {
        return memberDAO.getAllMember();
    }
    
    public MemberDTO getMemberById(Integer id) {
    	return memberDAO.getMemberById(id);
    }
    
    public MemberDTO addMember(MemberDTO dto) {
    	return memberDAO.addMember(dto);
    }
	

}
