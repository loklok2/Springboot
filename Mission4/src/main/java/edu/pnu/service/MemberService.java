package edu.pnu.service;


import java.util.List;

import edu.pnu.dao.LogDAO;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.LogDTO;
import edu.pnu.domain.MemberDTO;


public class MemberService{
	private MemberDAO memberDAO;
	private LogDAO logDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
		logDAO = new LogDAO();
	}
	
    public List<MemberDTO> getAllMember() {
        return memberDAO.getAllMember();
    }
    
    public MemberDTO getMemberById(Integer id) {
    	return memberDAO.getMemberById(id);
    }
    
    public int addMember(MemberDTO dto) {
    	return memberDAO.addMember(dto);
    }
	
    public int upDateMember(MemberDTO dto) {
    	return memberDAO.upDateMember(dto);
    }
    
    public int deleteMember(MemberDTO dto) {
    	return memberDAO.deleteMember(dto);
    }
    //dblog 조회
    public List<LogDTO> getAlldblog(){
    	return logDAO.getAlldbLog();
    }
}
