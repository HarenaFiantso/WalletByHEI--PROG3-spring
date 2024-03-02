package com.wallet.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CurrencyValue {
  @Id
  private String currencyValueId;
  private LocalDateTime currencyValueDate;
  private Double exchangeRate;
  private String sourceCurrencyId;
  private String destinationCurrencyId;

  public CurrencyValue(String currencyValueId, LocalDateTime currencyValueDate, Double exchangeRate, String sourceCurrencyId, String destinationCurrencyId) {
    this.currencyValueId = currencyValueId;
    this.currencyValueDate = currencyValueDate;
    this.exchangeRate = exchangeRate;
    this.sourceCurrencyId = sourceCurrencyId;
    this.destinationCurrencyId = destinationCurrencyId;
  }

  public CurrencyValue() {

  }

  public String getCurrencyValueId() {
    return currencyValueId;
  }

  public void setCurrencyValueId(String currencyValueId) {
    this.currencyValueId = currencyValueId;
  }

  public LocalDateTime getCurrencyValueDate() {
    return currencyValueDate;
  }

  public void setCurrencyValueDate(LocalDateTime currencyValueDate) {
    this.currencyValueDate = currencyValueDate;
  }

  public Double getExchangeRate() {
    return exchangeRate;
  }

  public void setExchangeRate(Double exchangeRate) {
    this.exchangeRate = exchangeRate;
  }

  public String getSourceCurrencyId() {
    return sourceCurrencyId;
  }

  public void setSourceCurrencyId(String sourceCurrencyId) {
    this.sourceCurrencyId = sourceCurrencyId;
  }

  public String getDestinationCurrencyId() {
    return destinationCurrencyId;
  }

  public void setDestinationCurrencyId(String destinationCurrencyId) {
    this.destinationCurrencyId = destinationCurrencyId;
  }
}
