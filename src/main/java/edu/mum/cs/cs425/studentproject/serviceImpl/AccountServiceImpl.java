package edu.mum.cs.cs425.studentproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.repository.AccountRepository;
import edu.mum.cs.cs425.studentproject.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void addAccount(Account account) {
		 accountRepository.save(account);
	}

}
