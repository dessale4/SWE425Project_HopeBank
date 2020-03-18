package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;
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

	@Override
	public Integer assignAccountNumber() {
		Integer baseAccountNumber = 100;
		List<Account>  accountList = getAllAccountList();
		Integer  accountListSize = getAllAccountList().size();
		Integer lastAccountNumber = accountListSize == 0? 0 : accountList.get(accountListSize-1).getAccountNumber();
		return lastAccountNumber ==0? baseAccountNumber: lastAccountNumber+1;
	}

	@Override
	public List<UserAccountDetail> findUserAccontDetails(Long userId) {
	
		return accountRepository.findUserAccontDetails(userId);
	}

	@Override
	public Account getAccountById(Long accountId) {
		
		return accountRepository.getOne(accountId);
	}

}
