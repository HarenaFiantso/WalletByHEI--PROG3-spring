package com.wallet.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {
  @Id
  private String transactionId;
  private Timestamp transactionDate;
  private String transactionType;
  private Double amount;
  private String label;
  private String accountId;
  private String categoryId;
}
