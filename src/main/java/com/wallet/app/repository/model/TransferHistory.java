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
@Table(name = "transfer_history")
public class TransferHistory {
  @Id private String transferHistoryId;
  private Timestamp transferDate;
  private String debitTransactionId;
  private String creditTransactionId;
}
