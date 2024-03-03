package com.wallet.app.controller;

import com.wallet.app.db.entity.Currency;
import com.wallet.app.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/currency")
public class CurrencyController {
  private final CurrencyService currencyService;

  @GetMapping("/{id}")
  public ResponseEntity<Currency> getCurrencyById(@PathVariable String id) {
    Currency currency = currencyService.getCurrencyById(id);
    if (currency != null) {
      return ResponseEntity.ok(currency);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
