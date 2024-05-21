package com.example.sbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	//http://localhost:8080/hello
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello SpringBoot!!!!";
	}
	

}
