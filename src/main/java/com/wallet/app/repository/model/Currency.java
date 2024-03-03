package com.wallet.app.repository.model;

import com.wallet.app.repository.model.type.CurrencyCodeType;
import com.wallet.app.repository.model.type.CurrencyNameType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "currency")
public class Currency {
  @Id private String currencyId;
  private CurrencyNameType currencyName;
  private CurrencyCodeType currencyCode;
}
