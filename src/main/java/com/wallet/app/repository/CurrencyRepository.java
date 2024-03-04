package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyRepository implements CrudRepository<Currency> {
  private static final Logger logger = LoggerFactory.getLogger(CurrencyRepository.class);

  private static final String CURRENCY_ID_COLUMN = "currency_id";
  private static final String CURRENCY_NAME_COLUMN = "currency_name";
  private static final String CURRENCY_CODE_COLUMN = "currency_code";

  private static final String SELECT_BY_ID_QUERY =
      "SELECT * FROM currency WHERE currency_id::text = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM currency";
  private static final String INSERT_QUERY =
      "INSERT INTO currency (currency_name, currency_code) VALUES (CAST(? AS currency_name), CAST(?"
          + " AS currency_code)) RETURNING *";
  private static final String DELETE_QUERY = "DELETE FROM currency WHERE currency_id::text = ?";

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
        Currency currency =
            new Currency(
                resultSet.getString(CURRENCY_ID_COLUMN),
                resultSet.getString(CURRENCY_NAME_COLUMN),
                resultSet.getString(CURRENCY_CODE_COLUMN));

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
  public Currency save(Currency toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(INSERT_QUERY);
      statement.setString(1, String.valueOf(toSave.getCurrencyName()));
      statement.setString(2, String.valueOf(toSave.getCurrencyCode()));

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Currency(
            resultSet.getString(CURRENCY_ID_COLUMN),
            resultSet.getString(CURRENCY_NAME_COLUMN),
            resultSet.getString(CURRENCY_CODE_COLUMN));
      }
      logger.info("Currency saved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to save currencies ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public Currency delete(String toDelete) {
    Currency currency = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        currency =
            new Currency(
                resultSet.getString(CURRENCY_ID_COLUMN),
                resultSet.getString(CURRENCY_NAME_COLUMN),
                resultSet.getString(CURRENCY_CODE_COLUMN));
      }

      logger.info("Currency deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete currency ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
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
      throw new RuntimeException(e);
    }
  }
}
