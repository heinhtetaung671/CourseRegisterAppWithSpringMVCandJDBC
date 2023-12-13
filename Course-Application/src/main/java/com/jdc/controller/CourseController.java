package com.jdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.model.entity.Course;
import com.jdc.model.entity.Course.Level;
import com.jdc.model.repo.CourseRepo;

@Controller
@RequestMapping("course")
public class CourseController {
	
	@Autowired
	CourseRepo repo;
	
	@GetMapping(path = "showList")
	public String ShowList(ModelMap model) {
		
		model.put("courses", repo.getAll());
		return "course-list";
	}
	
	@GetMapping("adding")
	public String adding() {
		return "course-add";
	}
	
	@PostMapping("addCourse")
	public String addCourse(@ModelAttribute Course course) {
		
		
		
		int id = repo.insert(course);
		
		return "redirect:/course/details/"+id;
	}
	
	@RequestMapping("details/{id:\\d+}")
	public String courseDetails(@PathVariable("id") int id, ModelMap model) {
		
		model.put("course", repo.findById(id));
		return "course-details";
	}
	
	@ModelAttribute("levels")
	public Level[] levels(ModelMap model) {
		
		return Level.values();
		
	}
	
	@ModelAttribute
	public void getEditId(@RequestParam(name = "editId", required = false) Integer editId, ModelMap model) {
		
		if(null != editId) {
			
			model.put("course", repo.findById(editId));
			
		}
		
	}

}
