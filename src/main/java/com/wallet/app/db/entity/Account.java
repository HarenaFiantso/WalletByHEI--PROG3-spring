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
public class Account implements Serializable {
  public String accountId;
  public String accountName;
  public String accountType;
  public String currencyId;
}
