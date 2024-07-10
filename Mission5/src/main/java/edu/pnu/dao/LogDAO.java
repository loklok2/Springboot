package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.LogDTO;
import lombok.RequiredArgsConstructor;

@Repository
public class LogDAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mission";
    private String id = "scott";
    private String pwd = "tiger";
    private Connection con;

    public LogDAO() {
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
    
    public List<LogDTO> getAlldbLog() {
    	List<LogDTO> list = new ArrayList<>();
        String query = "SELECT * FROM dblog";
        try{
        	PreparedStatement psmt = con.prepareStatement(query);
        	ResultSet rs = psmt.executeQuery();
        	while (rs.next()) {
        		LogDTO dto = new LogDTO();
        		dto.setId(rs.getInt("id"));
        		dto.setMethod(rs.getString("method"));
        		dto.setSqlstring(rs.getString("sqlstring"));
        		dto.setRegidate(rs.getDate("regidate"));
        		dto.setSuccess(rs.getBoolean("success"));
        		list.add(dto);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return list;
    }
    public void addLog(String method, String sqlString, boolean success) {
    	String query = "INSERT INTO dblog (method, sqlstring, success) VALUES (?, ?, ?)";
    	try {
    			PreparedStatement psmt = con.prepareStatement(query);
    			psmt.setString(1, method);
    			psmt.setString(2, sqlString);
    			psmt.setBoolean(3, success);
    			psmt.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}	
    }
    
        
}
