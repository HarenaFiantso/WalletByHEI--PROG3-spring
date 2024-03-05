package com.wallet.app.db.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class CurrencyValue implements Serializable {
  private String currencyValueId;
  private Date currencyValueDate;
  private Double exchangeRate;
  private String sourceCurrencyId;
  private String destinationCurrencyId;
}
