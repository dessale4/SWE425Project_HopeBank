package edu.mum.cs.cs425.studentproject.service;

import java.util.List;


import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.util.UserAccountDetail;

public interface AccountService {
	public void addAccount(Account account);
	public List<Account> getAllAccountList();
	public Double getBankLiquidity();
	public Integer assignAccountNumber();
	public List<UserAccountDetail> findUserAccontDetails(Long userId);
}
