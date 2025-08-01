package com.example.demo.courseControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.CourseRepository;
import com.example.demo.model.Course;

import lombok.extern.slf4j.Slf4j;

import org.springframework.ui.Model;


@Slf4j
@Controller
@RequestMapping("/courses")
public class courseController {
	
	@Autowired
	private CourseRepository repo;
	
	
	
	@GetMapping("/")
	public String dashboard(Model model) {
		return "coursedashboard";
		
	}
	
	@GetMapping("/add")
	public String addCourse(Model model) {
	    model.addAttribute("course", new Course());  // 💥 This line is crucial

		//model.addAttribute("course",new Course());

		return "addcourse";
	}
	
	@PostMapping("/save")
	public String saveCourse(@ModelAttribute Course course) {
		repo.save(course);
		
		return "redirect:/courses/add?success";
	}
	
	@GetMapping("/view_courses")
	public String viewCourses(Model model) {
		model.addAttribute("courses",repo.findAll());
		return "course_list";
	}
	
	@GetMapping("/edit/{id}")
	public String edit_course(@PathVariable Long id,Model model) {
		Course course=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid course Id:"+id));

		model.addAttribute("course",course);
		return "addcourse";

	}
	
	@ResponseBody
	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable Long id) {
		Course course=repo.findById(id)
				.orElseThrow(()->new IllegalArgumentException("Invalid course id"));
		return course;
		
	}
	

}
