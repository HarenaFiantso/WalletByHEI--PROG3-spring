package com.wallet.app.db.entity;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
  private String transactionId;
  private Timestamp transactionDate;
  private String transactionType;
  private Double amount;
  private String label;
  private String accountId;
  private String categoryId;
}
