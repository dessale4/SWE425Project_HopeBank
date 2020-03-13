package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.repository.AccountTypeRepository;
import edu.mum.cs.cs425.studentproject.service.AccountTypeService;

@Service
public class AccountTypeServiceImpl implements AccountTypeService{
	
	@Autowired
	AccountTypeRepository accountTypeRepository;
	
	@Override
	public void saveAccountType(AccountType accountType) {
		accountTypeRepository.save(accountType);
	}

	@Override
	public List<AccountType> allAccountTypes() {
		
		return accountTypeRepository.findAll();
	}
	
}
