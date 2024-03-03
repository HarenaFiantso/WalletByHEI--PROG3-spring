package com.wallet.app.model;

import com.wallet.app.model.type.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
  @Id private String accountId;
  private String accountName;
  private AccountType accountType;
  private String currencyId;

  public Account(String accountId, String accountName, AccountType accountType, String currencyId) {
    this.accountId = accountId;
    this.accountName = accountName;
    this.accountType = accountType;
    this.currencyId = currencyId;
  }

  public Account() {}

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public String getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(String currencyId) {
    this.currencyId = currencyId;
  }
}
