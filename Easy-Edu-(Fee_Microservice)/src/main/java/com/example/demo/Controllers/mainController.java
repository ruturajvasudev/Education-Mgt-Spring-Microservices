package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/fees")
public class mainController {
	
	@GetMapping("/fee_dashboard")
	public String fee_dashboard() {
		return "fee Controller";
	}

}
