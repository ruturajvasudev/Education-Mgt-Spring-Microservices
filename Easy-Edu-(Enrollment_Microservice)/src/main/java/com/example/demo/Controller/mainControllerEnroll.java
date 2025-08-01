
package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Student;
import com.example.demo.EnrollmentRepository;
import com.example.demo.*;
@Controller
@RequestMapping("/enrollment")
public class mainControllerEnroll {
	
	private Student student;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private EnrollmentRepository repo;
	
	@Autowired
	private EnrollStudentService enrollStudent;
	
	@Autowired
	private EnrollCourseService enrollCourse;
	
    @GetMapping("/")
    public String enrollment_dashboard() {
        return "dashboard";
    }
    
    @ResponseBody
    @GetMapping("/student/{id}")
    public Student getStudentDetails(@PathVariable Long id) {
    	return enrollStudent.fetchStudentDetails(id);
    }
    
    @GetMapping("/add")
    public String add_enrollment(Model model) {
    	model.addAttribute("enrollment",new Enrollment());
    	return "add_admission";
    }
    
    @PostMapping("/fetchStudent")
    public String fetchStudent(@RequestParam("studentId") Long id,Model model) {
    	Student student=enrollStudent.fetchStudentDetails(id);
    	Enrollment enrollment=new Enrollment();
    	enrollment.setStudent_id(student.getId());
    	model.addAttribute("student",student);

    	model.addAttribute("enrollment",enrollment);
    	return "add_admission";
    }
    
    @GetMapping("/fetchCourse/{id}")
    @ResponseBody
    public ResponseEntity<Course> fetchCourseDetails(@PathVariable Long id) {
    	try {
    		Course course=enrollCourse.fetchCourseDetails(id);
    		return ResponseEntity.ok(course);
    		
    	}catch(Exception e) {
    	    return ResponseEntity.status(500).build(); // let fallback handle it
    	}
    }
    @PostMapping("/save")
    public String save_addmission(@ModelAttribute Enrollment enrollment) {
    	repo.save(enrollment);
    	student=enrollStudent.fetchStudentDetails(enrollment.getStudent_id());
    	emailService.sendMailConfirmation(student,enrollment);
    	return "redirect:/enrollment/";
    }
    @GetMapping("/view")
    public String view_enrollment(Model model) {
    	model.addAttribute("enrollments",repo.findAll());
    	return "list_enrollments";
    }
    
   
    @ResponseBody
    @GetMapping("/student/{studentId}/courses")
    public List<EnrollmentCourseInfo> getEnrollmentsWithCourses(@PathVariable Long studentId) {
        return repo.findEnrollmentsWithCourseInfo(studentId);
    }

  
    
}
