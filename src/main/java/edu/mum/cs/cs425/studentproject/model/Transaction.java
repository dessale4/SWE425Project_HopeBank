package edu.mum.cs.cs425.studentproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@ManyToOne
	@JoinTable(name = "source_account")
	private Account from;
	
	@ManyToOne
	@JoinTable(name ="recieving_account")
	private Account to;
	
	@NotNull
	Double amount;
	
	@Column(name = "reason_for_transaction", nullable = false)
	private String reason;
	
	public Transaction() {
		
	}
	
	
	public Transaction(Account from, Account to, Double amount, String reason) {
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.reason = reason;
	}


	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Account getFrom() {
		return from;
	}


	public void setFrom(Account from) {
		this.from = from;
	}


	public Account getTo() {
		return to;
	}


	public void setTo(Account to) {
		this.to = to;
	}


	public String getReason() {
		return reason;
	}
	
	

	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
