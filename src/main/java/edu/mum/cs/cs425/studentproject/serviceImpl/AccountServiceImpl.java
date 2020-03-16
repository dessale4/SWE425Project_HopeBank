package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.util.List;

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

	@Override
	public List<Account> getAllAccountList() {
		
		return accountRepository.findAll();
	}

	@Override
	public Double getBankLiquidity() {
		Double bankLiquidity = 0.0;
		List<Account> allAccounts = getAllAccountList();
		for(Account account: allAccounts) {
			if(account.getAccountType().getAccountTypeName().equals("Loan Account")) {
				bankLiquidity -= account.getBalance();
			}else {
				bankLiquidity += account.getBalance();
			}
		}
		return bankLiquidity;
	}

}
