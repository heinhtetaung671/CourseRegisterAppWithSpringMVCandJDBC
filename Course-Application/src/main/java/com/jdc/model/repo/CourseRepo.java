package com.jdc.model.repo;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import com.jdc.model.entity.Course;
import com.jdc.model.entity.Course.Level;


@Component
public class CourseRepo {

	@Autowired
	private SimpleJdbcInsert jdbc;
	
	private RowMapper<Course> mapper;
	
	public CourseRepo() {
		mapper = new BeanPropertyRowMapper<>(Course.class);
	}
	
	public int insert(Course course) {
		
		if(course.getId() != 0) {
			jdbc.getJdbcTemplate().update("update course set name = ?, level = ? , duration = ?, fees = ? where id = ?",
					
					course.getName(), course.getLevel().name(), course.getDuration(), course.getFees(), course.getId()
					
					);
			return course.getId();
			
		}
		
		
		var factory = new PreparedStatementCreatorFactory("insert into course (name,level,duration,fees) values (?,?,?,?)");
		factory.setReturnGeneratedKeys(true);
		var creator = factory.newPreparedStatementCreator(List.of(course.getName(),course.getLevel().name(),course.getDuration(),course.getFees()));
		
		var holder  = new GeneratedKeyHolder();
		
		jdbc.getJdbcTemplate().update(creator, holder);
		
		return holder.getKey().intValue();
		
	}
	
	public Course findById(int id) {
		return jdbc.getJdbcTemplate().query("select * from course where id = ?", mapper , id).stream().findFirst().orElse(null);
	}
	
	public List<Course> getAll(){
		return jdbc.getJdbcTemplate().query("select * from course", mapper);
	}
	
}
