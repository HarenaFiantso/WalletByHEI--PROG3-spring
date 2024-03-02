package com.wallet.app.model;

import com.wallet.app.model.type.CurrencyCodeType;
import com.wallet.app.model.type.CurrencyNameType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Currency {
  @Id
  private String currencyId;
  private CurrencyNameType currencyName;
  private CurrencyCodeType currencyCode;

  public Currency(String currencyId, CurrencyNameType currencyName, CurrencyCodeType currencyCode) {
    this.currencyId = currencyId;
    this.currencyName = currencyName;
    this.currencyCode = currencyCode;
  }

  public Currency() {

  }

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
}
