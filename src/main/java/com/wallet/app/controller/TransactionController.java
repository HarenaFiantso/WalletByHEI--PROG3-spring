package com.wallet.app.controller;

import com.wallet.app.db.entity.Transaction;
import com.wallet.app.service.TransactionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
    Transaction transaction = transactionService.retrieveTransactionById(id);
    if (transaction != null) {
      return ResponseEntity.ok(transaction);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Transaction>> getAllTransactions() {
    List<Transaction> transactions = transactionService.retrieveAllTransactions();
    return ResponseEntity.ok(transactions);
  }

  @PostMapping("/create")
  public ResponseEntity<Transaction> createCategory(@RequestBody Transaction toSave) {
    Transaction savedTransaction = transactionService.createTransaction(toSave);

    if (savedTransaction != null) {
      return ResponseEntity.ok(savedTransaction);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public Transaction udpateTransaction(@PathVariable String id, @RequestBody Transaction toUpdate) {
    toUpdate.setTransactionId(id);
    return transactionService.updateTransaction(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Transaction> deleteTransaction(@PathVariable String id) {
    Transaction deletedTransaction = transactionService.deleteTransactionById(id);
    if (deletedTransaction != null) {
      return ResponseEntity.ok(deletedTransaction);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
