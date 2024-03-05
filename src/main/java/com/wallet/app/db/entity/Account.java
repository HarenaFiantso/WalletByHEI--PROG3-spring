package com.wallet.app.db.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
