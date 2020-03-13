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

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.User;
import edu.mum.cs.cs425.studentproject.service.AccountService;
import edu.mum.cs.cs425.studentproject.service.AccountTypeService;
import edu.mum.cs.cs425.studentproject.service.UserService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@GetMapping("/new/{userId}")
	public String displayAccountForm(@PathVariable("userId") Long userId , Model model) {
		User accountUser = userService.findUserById(userId);
		List<AccountType> accountTypes = accountTypeService.allAccountTypes();
		model.addAttribute("account", new Account());
		model.addAttribute("user", accountUser);
		model.addAttribute("accountTypes", accountTypes);
		
		return "account/new";
		
	}
	
	@PostMapping("/new/{userId}")
	public String addAccount(@PathVariable("userId") Long id,  @ModelAttribute("account") @Valid Account account, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			System.out.println("Account creation failed");
			return "redirect:/accounts/new/" + id;
		}
//		model.addAttribute("account", new Account());
		accountService.addAccount(account);
		System.out.println("Account created successfully");
		return "redirect:/accounts/new/" + id;
		
	}
}
