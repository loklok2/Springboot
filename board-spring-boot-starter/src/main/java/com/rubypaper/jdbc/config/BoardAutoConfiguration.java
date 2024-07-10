package com.rubypaper.jdbc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.jdbc.util.JDBCConnectionManager;

@Configuration
public class BoardAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	JDBCConnectionManager getJdbcConnectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setUrl("jdbc:mysql://localhost:3306/mission");
		manager.setUsername("scott");
		manager.setPassword("tiger");
		return manager;
	}
}
