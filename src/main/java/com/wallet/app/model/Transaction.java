package com.wallet.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Transaction {
  @Id private String transactionId;
  private Timestamp transactionDate;
  private String transactionType;
  private Double amount;
  private String label;
  private String accountId;
  private String categoryId;

  public Transaction(
      String transactionId,
      Timestamp transactionDate,
      String transactionType,
      Double amount,
      String label,
      String accountId,
      String categoryId) {
    this.transactionId = transactionId;
    this.transactionDate = transactionDate;
    this.transactionType = transactionType;
    this.amount = amount;
    this.label = label;
    this.accountId = accountId;
    this.categoryId = categoryId;
  }

  public Transaction() {}

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Timestamp getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Timestamp transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }
}
