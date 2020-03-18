package edu.mum.cs.cs425.studentproject.service;

import java.util.List;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;

public interface TransactionService {
	
	public Account findAccountByAccountTypeName(List<UserAccountDetail> userAccountTypes, String accountTypeName);

}
