package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.model.Enrollment;
import com.example.demo.feeservice.*;
import com.example.demo.model.*;
@Controller
@RequestMapping("/fees")
public class feesController {
	@Autowired
	private StudentClient studentClient;
	
	
	
	@Autowired
	private EnrollmentClient enrollmentClient;
	
	@GetMapping("/fee_dashboard")
	public String fee_dashboard() {
		
		return "fee Controller";
	}
	
	@GetMapping("/search-student")
	public String searchStudent(@RequestParam(required=false)String keyword,Model model) {
		
		List<Student> students=studentClient.displayStudent(keyword);
		model.addAttribute("students",students);
		model.addAttribute("keyword",keyword);
		return "student_result";
	}

	
	
	@GetMapping("/pay/{studentId}")
	public String showFeeForm(@PathVariable Long studentId,Model model) {
		Student student=studentClient.getStudentById(studentId);
		
		List<Enrollment> enrollments=enrollmentClient.getByStudentId(studentId);
	    List<EnrollmentCourseDTO> enrollcourse= enrollmentClient.getEnrollmentsWithCourses(studentId);

		
	    System.out.println("Enrollments with course info: " + enrollcourse);

		model.addAttribute("student",student);
		model.addAttribute("enrollments",enrollments);
		model.addAttribute("enrollcourse",enrollcourse);
		return "fee_form";
	}
}
