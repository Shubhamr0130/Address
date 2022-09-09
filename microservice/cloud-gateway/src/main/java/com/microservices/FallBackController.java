package com.microservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	@GetMapping("/userServiceFallBack")
	public String userFallBack() {
		return"User Service not work";	
	}
	@GetMapping("/departmentServiceFallBack")
	public String departmentFallBack() {
		return"Department Service not work";	
	}


}
