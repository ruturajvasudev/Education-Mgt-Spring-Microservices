package com.example.demo.studentControllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.demo")

@RestController
@RequestMapping("/student")
public class mainController {

	
	@GetMapping("/dashboard")//method to request dashboard
	public String hello() {
		return "studentdashboard";//this will open dashboard
		
	}
	
	
	
	
}
