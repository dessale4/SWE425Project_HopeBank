package edu.mum.cs.cs425.studentproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.service.AccountTypeService;

@Controller
@RequestMapping("accountTypes")
public class AccountTypeController {
	
	@Autowired
	AccountTypeService accountTypeService;
	
	@GetMapping("/new")
	public String accountTypeForm(Model model) {
		model.addAttribute("accountType", new AccountType());
		return "accountType/new";
		
	}
	
	@PostMapping("/new")
	public String addAccountType(@RequestParam("accountTypeName") String account_type, 
									@ModelAttribute("accountType") @Valid AccountType accountType, 
									BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("accountType", accountType);
			return "accountType/new";
		}
		try {
			accountTypeService.saveAccountType(accountType);
		}catch(Exception e) {
			ObjectError error = new ObjectError("email","AccontType " +  account_type + " already exists");
			result.addError(error);
			return "accountType/new";
		}

		return "redirect:/accountTypes/new";
		
	}
}
