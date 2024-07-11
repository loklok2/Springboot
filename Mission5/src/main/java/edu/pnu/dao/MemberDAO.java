package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberDTO;
import lombok.RequiredArgsConstructor;

@Repository
public class MemberDAO {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mission";
    private String id = "scott";
    private String pwd = "tiger";
    private Connection con;
    private LogDAO logDAO;
    private PreparedStatement psmt;
    private ResultSet rs;

    public MemberDAO() {
    	logDAO = new LogDAO();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, id, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
       
    }

    public void close() {
        try {
            if (con != null) {
            	con.close();            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MemberDTO> getAllMember() {
        List<MemberDTO> list = new ArrayList<>();
        String query = "SELECT * FROM member";
        boolean s = false;
        try {
        	psmt = con.prepareStatement(query);
        	rs = psmt.executeQuery();
            while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.setId(rs.getInt("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString("name"));
                dto.setRegidate(rs.getDate("regidate"));
                list.add(dto);
            }
            s = true;
            query = psmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logDAO.addLog("GET", query, (s == true) ? true : false);
        return list;
    }
    public MemberDTO getMemberById(Integer id) {
    	MemberDTO dto = new MemberDTO();
    	String query = "SELECT * FROM member WHERE id=?";
    	boolean s = false;
    	 try {
    		 psmt = con.prepareStatement(query);
    		 psmt.setInt(1, id);
    		 rs = psmt.executeQuery();
             // 결과 처리
             if (rs.next()) {
             	dto.setId(rs.getInt("id"));
             	dto.setPass(rs.getString("pass"));
 				dto.setName(rs.getString("name"));
 				dto.setRegidate(rs.getDate("regidate"));
             }
             s = true;
             query = psmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
            
         }
         catch (Exception e) {
             e.printStackTrace();
             logDAO.addLog("GET", query, false);
         } finally {
        	try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
         }
    	 logDAO.addLog("GET", query, s);
         return dto; 
     }
    
    
    public int addMember(MemberDTO dto) {
    	int result = 0;
    	String query = "INSERT INTO member (pass, name) "
    			+ "VALUES (?, ?)";
    	boolean s = false;
		try {
			// INSERT 쿼리문 작성 
			psmt = con.prepareStatement(query);  // 동적 쿼리 
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getName());
			result = psmt.executeUpdate();
			query = psmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
		}
		catch (Exception e) {
			e.printStackTrace();
			logDAO.addLog("POST", query, s);
		} finally {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logDAO.addLog("POST", query, true);
		return result;
	}
    
    public int upDateMember(MemberDTO dto) {
    	int result = 0;
    	boolean s = false;
    	String query = "UPDATE member SET "
    					+ "pass=?, name=? "
    					+ "WHERE id=? ";
    	try {
    		psmt = con.prepareStatement(query);
    		psmt.setString(1, dto.getPass());
    		psmt.setString(2, dto.getName());
    		psmt.setInt(3, dto.getId());
    		
    		result = psmt.executeUpdate();
    		query = psmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
    	} catch(Exception e) {
    		e.printStackTrace();
    		logDAO.addLog("PUT", query, s);
    	} finally {
    		try {
				psmt.close();    		
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	logDAO.addLog("PUT", query, true);
    	return result;
    	
    }
    
    public int deleteMember(MemberDTO dto) {
    	int result = 0;
    	boolean s = false;
    	String query = "DELETE FROM member WHERE id=?";
    	
    	try {
    		psmt = con.prepareStatement(query);
    		psmt.setInt(1, dto.getId());
    		result = psmt.executeUpdate();
    		query = psmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
    	} catch (Exception e) {
    		e.printStackTrace();
    		logDAO.addLog("DELETE", query, (s == true) ? true : false);
    	}finally {
    		try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	logDAO.addLog("DELETE", query, true);
    	return result;
    }
}


