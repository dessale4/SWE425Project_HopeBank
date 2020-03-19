package edu.mum.cs.cs425.studentproject.service;

import java.util.List;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.Transaction;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;

public interface TransactionService {
	public List<Transaction> getAllTransactions();
	public void addTransaction(Transaction transaction);
	public Account findAccountByAccountTypeName(List<UserAccountDetail> userAccountTypes, String accountTypeName);

}
