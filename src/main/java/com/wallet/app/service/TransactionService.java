package com.wallet.app.service;

import com.wallet.app.db.entity.Transaction;
import com.wallet.app.repository.TransactionRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {
  private TransactionRepository transactionRepository;

  public Transaction retrieveTransactionById(String transactionId) {
    return transactionRepository.findById(transactionId);
  }

  public List<Transaction> retrieveAllTransactions() {
    return transactionRepository.findAll();
  }

  public Transaction createTransaction(Transaction toSave) {
    return transactionRepository.save(toSave);
  }

  public Transaction updateTransaction(Transaction toUpdate) {
    return transactionRepository.update(toUpdate);
  }

  public Transaction deleteTransactionById(String toDelete) {
    return transactionRepository.delete(toDelete);
  }
}
