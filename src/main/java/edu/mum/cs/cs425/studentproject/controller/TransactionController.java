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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String getDepositForm(@PathVariable("userId") Long id, Model model, RedirectAttributes redirectAttributes) {
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(id);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		
		if(checkingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "Firstly you need to open a Checking Account to make a deposit");
			return "redirect:/users/details/" + id;
		}
		
		System.out.println(checkingAccount.getAccountType().getAccountTypeName());
		model.addAttribute("account", checkingAccount);
		return "transactions/depositForm";
	}
	
	@PostMapping("/deposit/{accountId}")
	public String depositMoney(@PathVariable("accountId") Long id, @ModelAttribute("account") @Valid Account depositAccount, 
								BindingResult result, @RequestParam("balance") Double inputBalance, Model model) {
		
		if(result.hasErrors()) {
			return "transactions/depositForm";
		}
		Account originalAccount = accountService.getAccountById(id);
		Double originalBalance = originalAccount.getBalance();
		Double newBalance = originalBalance + inputBalance;
		originalAccount.setBalance(newBalance);
		accountService.addAccount(originalAccount);
		return "redirect:/users/details/" + originalAccount.getAccountUser().getUserId();
		
	}
	
}
