package com.wallet.app.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferHistory implements Serializable {
  private String transferHistoryId;
  private Timestamp transferDate;
  private String debitTransactionId;
  private String creditTransactionId;
}
