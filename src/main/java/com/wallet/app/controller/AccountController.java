package com.wallet.app.controller;

import com.wallet.app.db.entity.Account;
import com.wallet.app.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
  private final AccountService accountService;

  @GetMapping("/{id}")
  public ResponseEntity<Account> getAccountById(@PathVariable String id) {
    Account account = accountService.retrieveAccountById(id);
    if (account != null) {
      return ResponseEntity.ok(account);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Account>> getAllAccounts() {
    List<Account> accounts = accountService.retrieveAllAccounts();
    return ResponseEntity.ok(accounts);
  }

  @PostMapping("/create")
  public ResponseEntity<Account> createAccount(@RequestBody Account toSave) {
    Account savedAccount = accountService.createAccount(toSave);

    if (savedAccount != null) {
      return ResponseEntity.ok(savedAccount);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public Account udpateAccount(@PathVariable String id, @RequestBody Account toUpdate) {
    toUpdate.setAccountId(id);
    return accountService.updateAccount(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Account> deleteAccount(@PathVariable String id) {
    Account deletedAccount = accountService.deleteAccount(id);
    if (deletedAccount != null) {
      return ResponseEntity.ok(deletedAccount);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
