package com.wallet.app.service;

import com.wallet.app.db.entity.TransferHistory;
import com.wallet.app.repository.TransferHistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferHistoryService {
  private TransferHistoryRepository transferHistoryRepository;

  public TransferHistory retrieveTransferHistoryById(String transferHistoryId) {
    return transferHistoryRepository.findById(transferHistoryId);
  }

  public List<TransferHistory> retrieveAllTransferHistories() {
    return transferHistoryRepository.findAll();
  }

  public TransferHistory createTransferHistory(TransferHistory toSave) {
    return transferHistoryRepository.save(toSave);
  }

  public TransferHistory updateTransferHistory(TransferHistory toUpdate) {
    return transferHistoryRepository.update(toUpdate);
  }

  public TransferHistory deleteTransferHistoryById(String toDelete) {
    return transferHistoryRepository.delete(toDelete);
  }
}
