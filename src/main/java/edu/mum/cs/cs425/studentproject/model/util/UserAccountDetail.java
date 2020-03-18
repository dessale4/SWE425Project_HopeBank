package edu.mum.cs.cs425.studentproject.model.util;

import edu.mum.cs.cs425.studentproject.model.Account;
import edu.mum.cs.cs425.studentproject.model.AccountType;
import edu.mum.cs.cs425.studentproject.model.User;

public class UserAccountDetail {
	private AccountType actType;
	private Account account;
	private User user;
	
	

	public UserAccountDetail(AccountType actType, Account account, User user) {
		super();
		this.actType = actType;
		this.account = account;
		this.user = user;
	}

	public AccountType getActType() {
		return actType;
	}

	public void setActType(AccountType actType) {
		this.actType = actType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
