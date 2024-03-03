package com.wallet.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency_value")
public class CurrencyValue {
  @Id
  private String currencyValueId;
  private LocalDateTime currencyValueDate;
  private Double exchangeRate;
  private String sourceCurrencyId;
  private String destinationCurrencyId;
}
