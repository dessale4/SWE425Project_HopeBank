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
	public String displayDepositForm(@PathVariable("userId") Long id, Model model, RedirectAttributes redirectAttributes) {
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(id);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		
		if(checkingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "Firstly you need to open a Checking Account");
			return "redirect:/users/details/" + id;
		}
		
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
	
	@GetMapping("/transfer/{userId}")
	public String displayTransferForm(@PathVariable("userId") Long id, Model model, RedirectAttributes redirectAttributes) {
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(id);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		
		if(checkingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "Firstly you need to open a Checking Account");
			return "redirect:/users/details/" + id;
		}
		model.addAttribute("userId", id);
		return "transactions/transferForm";
	}
	
	@PostMapping("/transfer")
	public String transferMoney(Model model, RedirectAttributes redirectAttributes, 
			@RequestParam("accountType") String accountType, @RequestParam("userId") Long userId, 
			@RequestParam("amount") Double amount) {
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(userId);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		Account recievingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, accountType);
		
		if(checkingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "Firstly you need to open a Checking Account");
			return "redirect:/users/details/" + userId;
		} 
		
		if(recievingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "You don't have a " + accountType + " to make a transfer");
			return "redirect:/accounts/new/" + userId;
		}
		if(amount > checkingAccount.getBalance()) {
			redirectAttributes.addFlashAttribute("error", "You don't have enough balance for this transfer try with less amount");
			return "redirect:/transactions/transfer/" + userId;
		}
		
		Double updatedCheckingAccountBalance = checkingAccount.getBalance() - amount;
		checkingAccount.setBalance(updatedCheckingAccountBalance);
		accountService.addAccount(checkingAccount);
		
		Double updatedRecievingAccountBalance;
		if(accountType.equals("Loan Account")) {
			updatedRecievingAccountBalance = recievingAccount.getBalance() - amount;
		}else {
			updatedRecievingAccountBalance = recievingAccount.getBalance() + amount;
		}
		recievingAccount.setBalance(updatedRecievingAccountBalance);
		accountService.addAccount(recievingAccount);
		return "redirect:/users/details/" + userId;
	}
	
	@GetMapping("/send/{userId}")
	public String displaySendForm(@PathVariable("userId") Long id, Model model, RedirectAttributes redirectAttributes) {
		List <UserAccountDetail> userAccountDetails = accountService.findUserAccontDetails(id);
		
		Account checkingAccount = transactionService.findAccountByAccountTypeName(userAccountDetails, "Checking Account");
		
		if(checkingAccount == null) {
			redirectAttributes.addFlashAttribute("error", "Firstly you need to open a Checking Account");
			return "redirect:/users/details/" + id;
		}
		
		return "transactions/sendForm";
	}
}
