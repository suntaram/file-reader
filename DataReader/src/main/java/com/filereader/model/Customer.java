package com.filereader.model;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class Customer {
	
	@CsvBindByName(column = "Reference")
	private long reference;
	@CsvBindByName(column = "AccountNumber")
	private String accountNumber;
	@CsvBindByName(column = "Description")
	private String desc;
	@CsvBindByName(column = "Start Balance")
	private BigDecimal startBal;
	@CsvBindByName(column = "Mutation")
	private BigDecimal mutation;
	@CsvBindByName(column = "End Balance")
	private BigDecimal endBal;
	private String reason;
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getReference() {
		return reference;
	}
	public void setReference(long reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public BigDecimal getStartBal() {
		return startBal;
	}
	public void setStartBal(BigDecimal startBal) {
		this.startBal = startBal;
	}
	public BigDecimal getMutation() {
		return mutation;
	}
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}
	public BigDecimal getEndBal() {
		return endBal;
	}
	public void setEndBal(BigDecimal endBal) {
		this.endBal = endBal;
	}
	
}
	
	
	