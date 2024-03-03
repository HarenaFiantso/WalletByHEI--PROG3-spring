package com.wallet.app.repository;

import com.wallet.app.repository.model.Currency;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyCrudOp implements CrudOperations<Currency> {
  @Override
  public Currency findById(Currency toFind) {
    return null;
  }

  @Override
  public List<Currency> findAll() {
    return null;
  }

  @Override
  public List<Currency> saveAll(List<Currency> toSave) {
    return null;
  }

  @Override
  public Currency save(Currency toSave) {
    return null;
  }

  @Override
  public void delete(Currency toDelete) {}
}
