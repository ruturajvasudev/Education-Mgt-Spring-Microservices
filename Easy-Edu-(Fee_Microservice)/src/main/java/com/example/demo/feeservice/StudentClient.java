package com.example.demo.feeservice;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Student;

@FeignClient(name="student-service")
public interface StudentClient {
	
	@GetMapping("students/search_api")
	List<Student>displayStudent(@RequestParam("keyword")String keyword);

	@GetMapping("students/get_byId/{id}")
	Student getStudentById(@PathVariable("id")Long id);
}
