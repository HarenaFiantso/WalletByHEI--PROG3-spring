package com.wallet.app.service;

import com.wallet.app.db.entity.CurrencyValue;
import com.wallet.app.repository.CurrencyValueRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyValueService {
  private CurrencyValueRepository currencyValueRepository;

  public CurrencyValue retrieveCurrencyValueById(String id) {
    return currencyValueRepository.findById(id);
  }

  public List<CurrencyValue> retrieveAllCurrencyValue() {
    return currencyValueRepository.findAll();
  }

  public CurrencyValue createCurrencyValue(CurrencyValue toSave) {
    return currencyValueRepository.save(toSave);
  }

  public CurrencyValue updateCurrencyValue(CurrencyValue toUpdate) {
    return currencyValueRepository.update(toUpdate);
  }

  public CurrencyValue deleteCurrencyValue(String toDelete) {
    return currencyValueRepository.delete(toDelete);
  }
}
