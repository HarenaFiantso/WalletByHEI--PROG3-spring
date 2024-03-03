package com.wallet.app.repository.model;

import com.wallet.app.repository.model.type.CurrencyCodeType;
import com.wallet.app.repository.model.type.CurrencyNameType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency {
  @Id private String currencyId;

  @Column(name = "currency_name")
  private CurrencyNameType currencyName;

  @Column(name = "currency_code")
  private CurrencyCodeType currencyCode;

  public Currency(String currencyId, CurrencyNameType currencyName, CurrencyCodeType currencyCode) {
    this.currencyId = currencyId;
    this.currencyName = currencyName;
    this.currencyCode = currencyCode;
  }

  public Currency(CurrencyNameType currencyName, CurrencyCodeType currencyCode) {
    this.currencyName = currencyName;
    this.currencyCode = currencyCode;
  }

  public Currency() {}

  public String getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(String currencyId) {
    this.currencyId = currencyId;
  }

  public CurrencyNameType getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(CurrencyNameType currencyName) {
    this.currencyName = currencyName;
  }

  public CurrencyCodeType getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(CurrencyCodeType currencyCode) {
    this.currencyCode = currencyCode;
  }

  @Override
  public String toString() {
    return "Currency{"
        + "currencyId='"
        + currencyId
        + '\''
        + ", currencyName="
        + currencyName
        + ", currencyCode="
        + currencyCode
        + '}';
  }
}
