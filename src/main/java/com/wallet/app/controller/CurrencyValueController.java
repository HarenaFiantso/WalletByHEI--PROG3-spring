package com.wallet.app.controller;

import com.wallet.app.db.entity.CurrencyValue;
import com.wallet.app.service.CurrencyValueService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/currencies-values")
public class CurrencyValueController {
  private final CurrencyValueService currencyValueService;

  @GetMapping("/{id}")
  public ResponseEntity<CurrencyValue> getCurrencyValueById(@PathVariable String id) {
    CurrencyValue currencyValue = currencyValueService.retrieveCurrencyValueById(id);
    if (currencyValue != null) {
      return ResponseEntity.ok(currencyValue);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<CurrencyValue>> getAllCurrenciesValues() {
    List<CurrencyValue> currencyValues = currencyValueService.retrieveAllCurrencyValue();
    return ResponseEntity.ok(currencyValues);
  }

  @PostMapping("/create")
  public ResponseEntity<CurrencyValue> createAccount(@RequestBody CurrencyValue toSave) {
    CurrencyValue savedCurrencyValue = currencyValueService.createCurrencyValue(toSave);

    if (savedCurrencyValue != null) {
      return ResponseEntity.ok(savedCurrencyValue);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public CurrencyValue udpateCurrencyValue(
      @PathVariable String id, @RequestBody CurrencyValue toUpdate) {
    toUpdate.setCurrencyValueId(id);
    return currencyValueService.updateCurrencyValue(toUpdate);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CurrencyValue> deleteCurrencyValue(@PathVariable String id) {
    CurrencyValue deletedCurrencyValue = currencyValueService.deleteCurrencyValue(id);
    if (deletedCurrencyValue != null) {
      return ResponseEntity.ok(deletedCurrencyValue);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
