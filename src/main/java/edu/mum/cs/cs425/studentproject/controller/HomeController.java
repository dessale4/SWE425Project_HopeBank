package edu.mum.cs.cs425.studentproject.controller;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//	@Secured({"ROLE_ADMIN"})
	@GetMapping("/")
	public String welcomePage(){
		return "home/index";
	}
}
