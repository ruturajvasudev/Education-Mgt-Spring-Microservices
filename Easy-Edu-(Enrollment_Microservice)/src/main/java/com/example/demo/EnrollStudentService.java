package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.Student;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EnrollStudentService {

	@Autowired
	private RestTemplate restTemplate;
	
	@CircuitBreaker(name="studentServiceBreaker",fallbackMethod="studentFallback")
	public Student fetchStudentDetails(Long studentId) {
		
		String url="http://student-service/students/"+studentId;
		return restTemplate.getForObject(url,Student.class);
	}
	
	public Student studentFallback(Long studentId,Throwable t) {
		Student fallbackStudent=new Student();
		fallbackStudent.setName("Fallback Student");
		fallbackStudent.setId(studentId);
		return fallbackStudent;
		
	}
}
