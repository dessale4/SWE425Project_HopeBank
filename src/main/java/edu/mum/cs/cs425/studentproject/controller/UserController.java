package edu.mum.cs.cs425.studentproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.cs.cs425.studentproject.model.User;
import edu.mum.cs.cs425.studentproject.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	UserService userService;
	@GetMapping("/new")
	public String displayUserForm(Model model) {
		model.addAttribute("user", new User());
		return "user/new";
	}
	
	@PostMapping("/new")
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user/new";
		}
		userService.addUser(user);
		return "redirect:/users/new";
	}
	
	@GetMapping("/list")
	public String allUsers(Model model) {
		List<User> usersList = userService.findAllUsers();
		model.addAttribute("users", usersList);
		model.addAttribute("usersCount", usersList.size());
		return ("user/list");
	}
	@GetMapping("/details/{userId}")
	public String userDetail(@PathVariable("userId") Long id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return ("user/details");
	}
}
