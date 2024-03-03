package com.wallet.app.service;

import com.wallet.app.db.entity.Currency;
import com.wallet.app.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CurrencyService {
  private CurrencyRepository currencyRepository;

  public Currency getCurrencyById(String currencyId) {
    return currencyRepository.findById(currencyId);
  }

  public List<Currency> getCurrencies() {
    return currencyRepository.findAll();
  }
}
