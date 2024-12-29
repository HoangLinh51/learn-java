package com.javaproject.project_backend_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Application {


	@GetMapping("/api")
	public String api() {
		return "API is working";
	}
}
