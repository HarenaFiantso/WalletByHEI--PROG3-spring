package com.wallet.app.db.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Currency implements Serializable {
  private String currencyId;
  private String currencyName;
  private String currencyCode;
}
