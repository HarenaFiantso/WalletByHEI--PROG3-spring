package com.wallet.app.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class TransferHistory {
  @Id private String transferHistoryId;
  private Timestamp transferDate;
  private String debitTransactionId;
  private String creditTransactionId;

  public TransferHistory(
      String transferHistoryId,
      Timestamp transferDate,
      String debitTransactionId,
      String creditTransactionId) {
    this.transferHistoryId = transferHistoryId;
    this.transferDate = transferDate;
    this.debitTransactionId = debitTransactionId;
    this.creditTransactionId = creditTransactionId;
  }

  public TransferHistory() {}

  public String getTransferHistoryId() {
    return transferHistoryId;
  }

  public void setTransferHistoryId(String transferHistoryId) {
    this.transferHistoryId = transferHistoryId;
  }

  public Timestamp getTransferDate() {
    return transferDate;
  }

  public void setTransferDate(Timestamp transferDate) {
    this.transferDate = transferDate;
  }

  public String getDebitTransactionId() {
    return debitTransactionId;
  }

  public void setDebitTransactionId(String debitTransactionId) {
    this.debitTransactionId = debitTransactionId;
  }

  public String getCreditTransactionId() {
    return creditTransactionId;
  }

  public void setCreditTransactionId(String creditTransactionId) {
    this.creditTransactionId = creditTransactionId;
  }
}
