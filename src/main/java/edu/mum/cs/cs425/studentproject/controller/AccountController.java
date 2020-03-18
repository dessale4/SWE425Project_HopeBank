package edu.mum.cs.cs425.studentproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
		model.addAttribute("newAccountNumber", accountService.assignAccountNumber());
		
		return "account/new";
		
	}
	
	@PostMapping("/new/{userId}")
	public String addAccount(@PathVariable("userId") Long id,  @ModelAttribute("account") @Valid Account account, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			User accountUser = userService.findUserById(id);
			List<AccountType> accountTypes = accountTypeService.allAccountTypes();
			model.addAttribute("account", account);
			model.addAttribute("user", accountUser);
			model.addAttribute("accountTypes", accountTypes);
			model.addAttribute("newAccountNumber", accountService.assignAccountNumber());
			return "account/new";
		}
		try {
			accountService.addAccount(account);
		}catch(Exception ex) {

			System.out.println("Account creation failed");
			ObjectError error = new ObjectError("error", "You already have a " + account.getAccountType().getAccountTypeName());
			result.addError(error);
			User accountUser = userService.findUserById(id);
			List<AccountType> accountTypes = accountTypeService.allAccountTypes();
			model.addAttribute("account", account);
			model.addAttribute("user", accountUser);
			model.addAttribute("accountTypes", accountTypes);
			model.addAttribute("newAccountNumber", accountService.assignAccountNumber());
			return "account/new";
		}
		return "redirect:/users/details/" + id;
		
	}
	@GetMapping("/list")
	public String allAcounts(Model model) {
		List<Account> accountList = accountService.getAllAccountList();
		
		model.addAttribute("accounts", accountList);
		model.addAttribute("accountCount", accountList.size());
		model.addAttribute("bankLiquidity", accountService.getBankLiquidity());
		return "account/accountList";
	}
}
