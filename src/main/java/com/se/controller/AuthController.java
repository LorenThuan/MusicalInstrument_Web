package com.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthController {
	@GetMapping("/login")
	public String showPage() {
		return "webapp/auth/login";
	}


	@GetMapping("/index")
	public String HomePage() {
		return "index";
	}
	
	@GetMapping("/list_user")
	public String List() {
		return "users";
	}
	
	@GetMapping(value = "/forgotpassword")
	public String Forgotpassword() {
		return "webapp/auth/forgotpassword-check";
	}
	
}
