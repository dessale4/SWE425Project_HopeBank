package edu.mum.cs.cs425.studentproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "accounts", uniqueConstraints = @UniqueConstraint(columnNames = {"account_Type", "account_User"}))
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	
	@NotNull(message = "account number is a required field")
	private Integer accountNumber;
	
	@NotNull(message ="balance field is a required field")
	private Double balance;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_Type", nullable = false)
	private AccountType accountType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "account_User")
	private User accountUser;

	public Account() {
		
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public User getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(User accountUser) {
		this.accountUser = accountUser;
	}
	
}
