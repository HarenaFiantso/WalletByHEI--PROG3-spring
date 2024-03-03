package com.wallet.app.repository.model;

import com.wallet.app.repository.model.type.AccountType;
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
@Table(name = "account")
public class Account {
  @Id private String accountId;
  private String accountName;
  private AccountType accountType;
  private String currencyId;
}
