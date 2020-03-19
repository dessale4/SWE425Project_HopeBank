package edu.mum.cs.cs425.studentproject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.Transaction;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;
import edu.mum.cs.cs425.studentproject.repository.TransactionRepository;
import edu.mum.cs.cs425.studentproject.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public Account findAccountByAccountTypeName(List<UserAccountDetail> userAccountTypes, String accountTypeName) {
		List<Account> accounts = userAccountTypes.stream().map(account->account.getAccount())
				
				.collect(Collectors.toList());
		for(Account ac: accounts) {
			if(ac.getAccountType().getAccountTypeName().equals(accountTypeName)) return ac;
		}
		return null;
//		Account	desiredAccount=(Account)accounts.stream().filter(account->account.getAccountType().getAccountTypeName().equals(accountTypeName));
//		System.out.println("actttttt"+accounts.toString());
//		return desiredAccount;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
	}
	

}
