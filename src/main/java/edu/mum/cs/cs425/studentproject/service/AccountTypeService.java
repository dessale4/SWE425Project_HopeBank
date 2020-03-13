package edu.mum.cs.cs425.studentproject.service;

import java.util.List;

import edu.mum.cs.cs425.studentproject.model.AccountType;

public interface AccountTypeService {
	
	public void saveAccountType(AccountType accountType);
	public List<AccountType> allAccountTypes();
}
