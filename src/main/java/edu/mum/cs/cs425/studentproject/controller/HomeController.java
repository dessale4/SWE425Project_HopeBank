package edu.mum.cs.cs425.studentproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String welcomePage(){
		return "home/index";
	}
}
