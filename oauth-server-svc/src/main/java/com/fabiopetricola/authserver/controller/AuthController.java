package com.fabiopetricola.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	
	
	@GetMapping("/error")
	public String sendError() {
		return "redirect:/login";
	}
}
