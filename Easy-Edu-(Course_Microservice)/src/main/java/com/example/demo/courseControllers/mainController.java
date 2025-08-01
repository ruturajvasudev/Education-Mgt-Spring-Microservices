package com.example.demo.courseControllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication(scanBasePackages = "com.example.demo")

@RestController
@RequestMapping("/courses/api")
public class mainController {

	@GetMapping("/course_dashboard")
	public String course_dashboard() {
		return "Courses Controller";
	}
}
