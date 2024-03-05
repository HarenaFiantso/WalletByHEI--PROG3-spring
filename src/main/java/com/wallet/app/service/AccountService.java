package com.wallet.app.service;

import com.wallet.app.db.entity.Account;
import com.wallet.app.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
  private AccountRepository accountRepository;

  public Account retrieveAccountById(String accountId) {
    return accountRepository.findById(accountId);
  }

  public List<Account> retrieveAllAccounts() {
    return accountRepository.findAll();
  }

  public Account createAccount(Account toSave) {
    return accountRepository.save(toSave);
  }

  public Account updateAccount(Account toUpdate) {
    return accountRepository.update(toUpdate);
  }

  public Account deleteAccount(String toDelete) {
    return accountRepository.delete(toDelete);
  }
}
