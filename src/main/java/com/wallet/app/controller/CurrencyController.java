package com.wallet.app.controller;

import com.wallet.app.db.entity.Currency;
import com.wallet.app.service.CurrencyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/currencies")
public class CurrencyController {
  private final CurrencyService currencyService;

  @GetMapping("/{id}")
  public ResponseEntity<Currency> getCurrencyById(@PathVariable String id) {
    Currency currency = currencyService.retrieveCurrencyById(id);
    if (currency != null) {
      return ResponseEntity.ok(currency);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Currency>> getAllCurrencies() {
    List<Currency> currencies = currencyService.retrieveAllCurrencies();
    return ResponseEntity.ok(currencies);
  }

  @PostMapping("/create")
  public ResponseEntity<Currency> createCurrency(@RequestBody Currency toSave) {
    Currency savedCurrency = currencyService.createCurrency(toSave);

    if (savedCurrency != null) {
      return ResponseEntity.ok(savedCurrency);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public Currency updateCurrency(@PathVariable String id, @RequestBody Currency toUpdate) {
    toUpdate.setCurrencyId(id);
    return currencyService.updateCurrency(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Currency> deleteCurrency(@PathVariable String id) {
    Currency deletedCurrency = currencyService.deleteCurrencyById(id);
    if (deletedCurrency != null) {
      return ResponseEntity.ok(deletedCurrency);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
