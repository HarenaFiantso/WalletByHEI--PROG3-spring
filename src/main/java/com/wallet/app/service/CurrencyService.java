package com.wallet.app.service;

import com.wallet.app.repository.CurrencyCrudOp;
import com.wallet.app.repository.model.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
  private final CurrencyCrudOp currencyCrudOp;

  public CurrencyService(CurrencyCrudOp currencyCrudOp) {
    this.currencyCrudOp = currencyCrudOp;
  }

  public Currency findCurrencyById(String currencyId) {
    Currency toFind = new Currency();
    toFind.setCurrencyId(currencyId);
    return currencyCrudOp.findById(toFind);
  }

  public List<Currency> findAllCurrencies() {
    return currencyCrudOp.findAll();
  }

  public List<Currency> saveAllCurrencies(List<Currency> currencies) {
    return currencyCrudOp.saveAll(currencies);
  }

  public Currency saveCurrency(Currency currency) {
    return currencyCrudOp.save(currency);
  }

  public Currency deleteCurrency(String currencyId) {
    Currency toDelete = new Currency();
    toDelete.setCurrencyId(currencyId);
    return currencyCrudOp.delete(toDelete);
  }
}
