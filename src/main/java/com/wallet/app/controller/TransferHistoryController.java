package com.wallet.app.controller;

import com.wallet.app.db.entity.TransferHistory;
import com.wallet.app.service.TransferHistoryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transfer-histories")
public class TransferHistoryController {
  private final TransferHistoryService transferHistoryService;

  @GetMapping("/{id}")
  public ResponseEntity<TransferHistory> getTransferHistoryById(@PathVariable String id) {
    TransferHistory transferHistory = transferHistoryService.retrieveTransferHistoryById(id);
    if (transferHistory != null) {
      return ResponseEntity.ok(transferHistory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<TransferHistory>> getAllTransferHistories() {
    List<TransferHistory> transferHistories = transferHistoryService.retrieveAllTransferHistories();
    return ResponseEntity.ok(transferHistories);
  }

  @PostMapping("/create")
  public ResponseEntity<TransferHistory> createTransferHistory(
      @RequestBody TransferHistory toSave) {
    TransferHistory savedTransferHistory = transferHistoryService.createTransferHistory(toSave);

    if (savedTransferHistory != null) {
      return ResponseEntity.ok(savedTransferHistory);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public TransferHistory updateTransferHistory(
      @PathVariable String id, @RequestBody TransferHistory toUpdate) {
    toUpdate.setTransferHistoryId(id);
    return transferHistoryService.updateTransferHistory(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<TransferHistory> deleteTransferHistory(@PathVariable String id) {
    TransferHistory deletedTransferHistory = transferHistoryService.deleteTransferHistoryById(id);
    if (deletedTransferHistory != null) {
      return ResponseEntity.ok(deletedTransferHistory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
