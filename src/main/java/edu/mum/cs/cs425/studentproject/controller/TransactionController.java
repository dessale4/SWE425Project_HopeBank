package edu.mum.cs.cs425.studentproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.User;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;
import edu.mum.cs.cs425.studentproject.service.AccountService;
import edu.mum.cs.cs425.studentproject.service.TransactionService;
import edu.mum.cs.cs425.studentproject.service.UserService;


@Controller
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	@Autowired
	AccountService accountService;
	
	@GetMapping("/deposit/{userId}")
	public String getDepositForm(@PathVariable("userId") Long id, Model model) {
//		User user = userService.findUserById(id);
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(id);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		
		if(checkingAccount == null) {
			model.addAttribute("error", "You need to open a Checking Account to make deposit");
			return "redirect:/users/details/" + id;
		}
		else System.out.println("no checking account");
		return "transactions/depositForm";
	}
	
}
