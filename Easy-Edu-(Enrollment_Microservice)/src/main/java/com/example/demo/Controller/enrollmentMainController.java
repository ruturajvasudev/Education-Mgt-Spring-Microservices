package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Enrollment;
import com.example.demo.model.Student;
import com.example.demo.EnrollStudentService;
import com.example.demo.EnrollmentRepository;
@RestController
public class enrollmentMainController {

	@Autowired
	private EnrollmentRepository repo;
	
	@Autowired
	private EnrollStudentService enrollStudentService;
	
	
    @GetMapping("api/enrollment/dashboard")
    public String enrollment_dashboard() {
        return "Enrollment Controller";
    }
    
    @GetMapping("student/{id}")
    public Student getStudentDetails(@PathVariable Long studentId) {
    	return enrollStudentService.fetchStudentDetails(studentId);
    }
    
    @GetMapping("api/enrollment/student/{studentId}")
    public List<Enrollment> getByStudentId(@PathVariable Long studentId){
    	return repo.findByStudentId(studentId);
    }
}
