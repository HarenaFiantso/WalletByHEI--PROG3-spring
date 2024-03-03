package com.wallet.app.repository;

import com.wallet.app.repository.conf.DatabaseConnection;
import com.wallet.app.repository.model.Currency;
import com.wallet.app.repository.model.type.CurrencyCodeType;
import com.wallet.app.repository.model.type.CurrencyNameType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyCrudOp implements CrudOperations<Currency> {
  private static final String CURRENCY_ID_COLUMN = "currency_id";
  private static final String CURRENCY_NAME_COLUMN = "currency_name";
  private static final String CURRENCY_CODE_COLUMN = "currency_code";

  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM currency WHERE currency_id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM currency";
  private static final String INSERT_QUERY =
      "INSERT INTO currency (currency_name, currency_code) VALUES (CAST(? AS currency_name), CAST(?"
          + " AS currency_code)) RETURNING *";
  private static final String UPDATE_QUERY =
      "UPDATE currency SET currency_name = CAST(? AS currency_name), currency_code = CAST(? AS"
          + " currency_code) WHERE currency_id = ? RETURNING *";
  private static final String DELETE_QUERY = "DELETE FROM currency WHERE currency_id = ?";

  @Override
  public Currency findById(Currency toFind) {
    return executeQuery(
        SELECT_BY_ID_QUERY, toFind.getCurrencyId(), "Failed to retrieve currency :");
  }

  @Override
  public List<Currency> findAll() {
    List<Currency> currencies = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DatabaseConnection.getConnection();

      statement = connection.prepareStatement(SELECT_ALL_QUERY);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Currency currency = new Currency();
        currency.setCurrencyId(resultSet.getString(CURRENCY_ID_COLUMN));
        currency.setCurrencyName(
            CurrencyNameType.valueOf(resultSet.getString(CURRENCY_NAME_COLUMN)));
        currency.setCurrencyCode(
            CurrencyCodeType.valueOf(resultSet.getString(CURRENCY_CODE_COLUMN)));

        currencies.add(currency);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to retrieve currencies : " + e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return currencies;
  }

  @Override
  public List<Currency> saveAll(List<Currency> toSave) {
    List<Currency> savedCurrencies = new ArrayList<>();

    for (Currency currency : toSave) {
      Currency savedCurrency = this.save(currency);
      savedCurrencies.add(savedCurrency);
    }

    return savedCurrencies;
  }

  @Override
  public Currency save(Currency toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    String QUERY;
    boolean isNewCurrency = toSave.getCurrencyId() == null;

    try {
      connection = DatabaseConnection.getConnection();
      if (isNewCurrency) {
        QUERY = INSERT_QUERY;
        statement = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, String.valueOf(toSave.getCurrencyName()));
        statement.setString(2, String.valueOf(toSave.getCurrencyCode()));
      } else {
        QUERY = UPDATE_QUERY;
        statement = connection.prepareStatement(QUERY);
        statement.setString(1, String.valueOf(toSave.getCurrencyName()));
        statement.setString(2, String.valueOf(toSave.getCurrencyCode()));
        statement.setString(3, toSave.getCurrencyId());
      }

      boolean isResultSet = statement.execute();

      if (isResultSet) {
        resultSet = statement.getResultSet();

        if (resultSet.next()) {
          Currency savedCurrency = new Currency();
          savedCurrency.setCurrencyName(
              CurrencyNameType.valueOf(resultSet.getString(CURRENCY_NAME_COLUMN)));
          savedCurrency.setCurrencyCode(
              CurrencyCodeType.valueOf(resultSet.getString(CURRENCY_CODE_COLUMN)));

          return savedCurrency;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to save currency : " + e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public Currency delete(Currency toDelete) {
    return executeQuery(DELETE_QUERY, toDelete.getCurrencyId(), "Failed to delete currency");
  }

  @Override
  public Currency executeQuery(String query, String id, String errorMessage) {
    Currency currency = null;

    try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

      statement.setString(1, id);

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          currency = new Currency();
          currency.setCurrencyId(resultSet.getString(CURRENCY_ID_COLUMN));
        }
      }

    } catch (SQLException e) {
      System.err.println("SQL Error: " + e.getMessage());
      throw new RuntimeException(errorMessage + e.getMessage(), e);
    }

    return currency;
  }

  @Override
  public void closeResources(
      Connection connection, PreparedStatement statement, ResultSet resultSet) {
    try {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to close resources " + e.getMessage());
    }
  }
}
