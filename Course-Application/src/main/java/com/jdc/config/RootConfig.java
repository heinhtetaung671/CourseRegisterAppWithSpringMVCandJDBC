package com.jdc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
@ComponentScan("com.jdc.model")
public class RootConfig {

	@Bean
	public DataSource dataSource() {
		var ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/course_app");
		ds.setUsername("root");
		ds.setPassword("admin");
		return ds;
	}
	
	@Bean
	public SimpleJdbcInsert simple(DataSource ds) {
		var simple = new SimpleJdbcInsert(ds);
		simple.setTableName("course");
		simple.setGeneratedKeyName("id");
		return simple;
	}
	
}
