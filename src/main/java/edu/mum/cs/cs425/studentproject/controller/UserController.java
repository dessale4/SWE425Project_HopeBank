package edu.mum.cs.cs425.studentproject.controller;

import java.time.LocalDate;
import java.time.Period;
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

import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.User;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;
import edu.mum.cs.cs425.studentproject.service.AccountService;
import edu.mum.cs.cs425.studentproject.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/new")
	public String displayUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("newUserNumber", userService.assignUserNumber());
		return "user/new";
	}
	
	@PostMapping("/new")
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		model.addAttribute("user", user);
		if(result.hasErrors()) {
			model.addAttribute("newUserNumber", user.getUserNumber());
			return "user/new";
		}
		userService.addUser(user);
		return "redirect:/users/details/"+ user.getUserId();
	}
	
	@GetMapping("/list")
	public String allUsers(Model model) {
		List<User> usersList = userService.findAllUsers();
		model.addAttribute("users", usersList);
		model.addAttribute("usersCount", usersList.size());
		
		return ("user/list");
	}
	@GetMapping("/list/sortField")
	public String allUsersSortedByFirstName(Model model) {
		String sortField = "firstName";
		List<User> usersList = userService.findAllUsers();
		List<User> sortedUsersList = userService.sortUsers(usersList, sortField);
		model.addAttribute("users", sortedUsersList);
		model.addAttribute("usersCount", sortedUsersList.size());
		return ("user/list");
	}
	
	@GetMapping("/list/sortedAdultUsers")
	public String allAdultUsersSortedByFirstName(Model model) {
		String sortField = "firstName";
		List<User> usersList = userService.findAllUsers();
		
		List<User> sortedUsersList = userService.sortUsers(usersList, sortField);
		List<User> aduldUsersList = userService.adultUsersList(sortedUsersList, 18);
		model.addAttribute("users", aduldUsersList);
		model.addAttribute("usersCount", aduldUsersList.size());
		System.out.println("From here");
		return ("user/list");
	}
	
	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable("userId")Long id, Model model) {
		userService.deleteUser(id);
		return "redirect:/users/list";
	}
	@GetMapping("/details/{userId}")
	public String userDetail(@PathVariable("userId") Long id, Model model) {
		User user = userService.findUserById(id);// will be edited later to be combined with UserAccountDetail util instance
		model.addAttribute("user", user);
		List<UserAccountDetail> accountTypes = accountService.findUserAccontDetails(id);
		model.addAttribute("userAccountTypes", accountTypes);
		model.addAttribute("accountTypeCount", accountTypes.size());
		return ("user/details");
	}
	
	@GetMapping("/edit/{userId}")
	public String updateUser(@PathVariable("userId") Long id, Model model) {
		User userTobeUpdated = userService.findUserById(id);
		model.addAttribute("user", userTobeUpdated);
		return "user/edit";
	}
}
