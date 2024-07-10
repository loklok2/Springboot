package com.rubypaper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

@Configuration
public class BoardConfiguration {
	
	//@Bean
	JDBCConnectionManager getJdbcConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setUrl("jdbc:mysql://localhost:3306/mission");
		manager.setUsername("scott");
		manager.setPassword("tiger");
		return manager;
	}
	//내가 만든 starter에서 만든 manager가 먼저 빈에 등록함
	// 근데 여기서 또 manager를 만들어서 등록하면 에러 발생

}
