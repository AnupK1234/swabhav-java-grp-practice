package com.aurionpro.model;

import java.time.LocalDateTime;

public class Transaction {

	private long transactionId;
	private long accountNo;
	private TransactionType type;
	private double amount;
	private LocalDateTime tDate;
	private String description;
	private double balanceAfterTransaction;

	public double getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}

	public void setBalanceAfterTransaction(double balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime gettDate() {
		return tDate;
	}

	public void settDate(LocalDateTime tDate) {
		this.tDate = tDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Transaction(long transactionId, double balanceAfterTransaction, long accountNo, TransactionType type,
			double amount, LocalDateTime tDate, String description) {
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.type = type;
		this.amount = amount;
		this.tDate = tDate;
		this.description = description;
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	public Transaction(long accountNo, double balanceAfterTransaction, TransactionType type, double amount,
			LocalDateTime tDate, String description) {
		this.accountNo = accountNo;
		this.type = type;
		this.amount = amount;
		this.tDate = tDate;
		this.description = description;
		this.balanceAfterTransaction = balanceAfterTransaction;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNo=" + accountNo + ", type=" + type
				+ ", amount=" + amount + ", tDate=" + tDate + ", description=" + description
				+ ", balanceAfterTransaction=" + balanceAfterTransaction + "]";
	}

}
