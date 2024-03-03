package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepository implements CrudRepository<Currency> {
  private static final Logger logger = LoggerFactory.getLogger(CurrencyRepository.class);

  private static final String CURRENCY_ID_COLUMN = "currency_id";
  private static final String CURRENCY_NAME_COLUMN = "currency_name";
  private static final String CURRENCY_CODE_COLUMN = "currency_code";

  private static final String SELECT_BY_ID_QUERY =
      "SELECT * FROM currency WHERE currency_id::text = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM currency";

  @Override
  public Currency findById(String toFind) {
    Currency currency = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
      statement.setString(1, toFind);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        currency =
            new Currency(
                resultSet.getString(CURRENCY_ID_COLUMN),
                resultSet.getString(CURRENCY_NAME_COLUMN),
                resultSet.getString(CURRENCY_CODE_COLUMN));
      }

      logger.info("Currency retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve currency ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return currency;
  }

  @Override
  public List<Currency> findAll() {
    List<Currency> currencies = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_ALL_QUERY);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Currency currency = new Currency(
            resultSet.getString(CURRENCY_ID_COLUMN),
            resultSet.getString(CURRENCY_NAME_COLUMN),
            resultSet.getString(CURRENCY_CODE_COLUMN)
        );

        currencies.add(currency);
      }
      logger.info("Currencies retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve currencies ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return currencies;
  }

  @Override
  public List<Currency> saveAll(List<Currency> toSave) {
    return null;
  }

  @Override
  public Currency save(String toSave) {
    return null;
  }

  @Override
  public void delete(String toDelete) {
  }

  @Override
  public void closeResources(
      Connection connection, PreparedStatement statement, ResultSet resultSet) {
  }
}
