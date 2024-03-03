package com.wallet.app.repository;

import com.wallet.app.repository.model.Currency;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyCrudOp implements CrudOperations<Currency> {

  @Autowired private JdbcTemplate jdbcTemplate;

  @Override
  public Currency findById(Currency toFind) {
    return null;
  }

  @Override
  public List<Currency> findAll() {
    return null;
  }

  @Override
  public int save(Currency toSave) {
    return jdbcTemplate.update(
        "INSERT INTO currency (currency_name, currency_code) values (? ,?)",
        toSave.getCurrencyName(), toSave.getCurrencyCode());
  }

  @Override
  public void delete(Currency toDelete) {}
}
