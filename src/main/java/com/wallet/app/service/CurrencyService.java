package com.wallet.app.service;

import com.wallet.app.db.entity.Currency;
import com.wallet.app.repository.CurrencyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyService {
  private CurrencyRepository currencyRepository;

  public Currency retrieveCurrencyById(String currencyId) {
    return currencyRepository.findById(currencyId);
  }

  public List<Currency> retrieveAllCurrencies() {
    return currencyRepository.findAll();
  }

  public Currency createCurrency(Currency toSave) {
    return currencyRepository.save(toSave);
  }

  public Currency updateCurrency(Currency toUpdate) {
    return currencyRepository.update(toUpdate);
  }

  public Currency deleteCurrencyById(String toDelete) {
    return currencyRepository.delete(toDelete);
  }
}
