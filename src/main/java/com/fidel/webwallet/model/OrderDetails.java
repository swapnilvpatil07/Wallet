/**
 * 
 */
package com.fidel.webwallet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Swapnil
 *
 */
@Entity
public class OrderDetails implements Comparable<OrderDetails> {

	/**
	 * 
	 */
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	@Id
	/* @OrderBy("orderId ASC") */
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private Double txnAmt;
	private String txnDate;
	private Integer txnSts;
	private String txnType;
	private Integer balance;

	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "cust_id") private UserInfo info;
	 */

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(Double txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public Integer getTxnSts() {
		return txnSts;
	}

	public void setTxnSts(Integer txnSts) {
		this.txnSts = txnSts;
	}

	@Override
	public int compareTo(OrderDetails o) {
		return this.getOrderId().compareTo(o.getOrderId());
	}

}
