package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.CurrencyValue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyValueRepository implements CrudRepository<CurrencyValue> {
  private static final Logger logger = LoggerFactory.getLogger(CurrencyValueRepository.class);

  private static final String CURRENCY_VALUE_ID_COLUMN = "currency_value_id";
  private static final String CURRENCY_VALUE_DATE_COLUMN = "currency_value_date";
  private static final String EXCHANGE_RATE_COLUMN = "exchange_rate";
  private static final String SOURCE_CURRENCY_ID_COLUMN = "source_currency_id";
  private static final String DESTINATION_CURRENCY_ID_COLUMN = "destination_currency_id";

  private static final String SELECT_BY_ID_QUERY =
      "SELECT * FROM currency_value WHERE currency_value_id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM currency_value";
  private static final String INSERT_QUERY =
      "INSERT INTO currency_value (currency_value_date, exchange_rate, source_currency_id,"
          + " destination_currency_id) VALUES (?, ?, ?, ?) RETURNING *";
  private static final String UPDATE_QUERY =
      "UPDATE currency_value SET currency_value_date = ?, exchange_rate = ?, source_currency_id ="
          + " ?, destination_currency_id = ? WHERE currency_value_id = ? RETURNING *";
  private static final String DELETE_QUERY =
      "DELETE FROM currency_value WHERE currency_value_id = ?";

  @Override
  public CurrencyValue findById(String toFind) {
    CurrencyValue currencyValue = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
      statement.setString(1, toFind);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        currencyValue =
            new CurrencyValue(
                resultSet.getString(CURRENCY_VALUE_ID_COLUMN),
                resultSet.getDate(CURRENCY_VALUE_DATE_COLUMN),
                resultSet.getDouble(EXCHANGE_RATE_COLUMN),
                resultSet.getString(SOURCE_CURRENCY_ID_COLUMN),
                resultSet.getString(DESTINATION_CURRENCY_ID_COLUMN));
      }

      logger.info("Currency value retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve currency value ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return currencyValue;
  }

  @Override
  public List<CurrencyValue> findAll() {
    List<CurrencyValue> currencyValues = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_ALL_QUERY);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        CurrencyValue currencyValue =
            new CurrencyValue(
                resultSet.getString(CURRENCY_VALUE_ID_COLUMN),
                resultSet.getDate(CURRENCY_VALUE_DATE_COLUMN),
                resultSet.getDouble(EXCHANGE_RATE_COLUMN),
                resultSet.getString(SOURCE_CURRENCY_ID_COLUMN),
                resultSet.getString(DESTINATION_CURRENCY_ID_COLUMN));

        currencyValues.add(currencyValue);
      }
      logger.info("Currencies values retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve currencies values ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return currencyValues;
  }

  @Override
  public CurrencyValue save(CurrencyValue toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(INSERT_QUERY);
      statement.setDate(1, (Date) toSave.getCurrencyValueDate());
      statement.setDouble(2, toSave.getExchangeRate());
      statement.setString(3, toSave.getSourceCurrencyId());
      statement.setString(4, toSave.getDestinationCurrencyId());

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new CurrencyValue(
            resultSet.getString(CURRENCY_VALUE_ID_COLUMN),
            resultSet.getDate(CURRENCY_VALUE_DATE_COLUMN),
            resultSet.getDouble(EXCHANGE_RATE_COLUMN),
            resultSet.getString(SOURCE_CURRENCY_ID_COLUMN),
            resultSet.getString(DESTINATION_CURRENCY_ID_COLUMN));
      }
      logger.info("Currency value saved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to save currency value ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public CurrencyValue update(CurrencyValue toUpdate) {
    CurrencyValue currencyValue = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(UPDATE_QUERY);
      statement.setDate(1, (Date) toUpdate.getCurrencyValueDate());
      statement.setDouble(2, toUpdate.getExchangeRate());
      statement.setString(3, toUpdate.getSourceCurrencyId());
      statement.setString(4, toUpdate.getDestinationCurrencyId());

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        currencyValue =
            new CurrencyValue(
                resultSet.getString(CURRENCY_VALUE_ID_COLUMN),
                resultSet.getDate(CURRENCY_VALUE_DATE_COLUMN),
                resultSet.getDouble(EXCHANGE_RATE_COLUMN),
                resultSet.getString(SOURCE_CURRENCY_ID_COLUMN),
                resultSet.getString(DESTINATION_CURRENCY_ID_COLUMN));
      }

      logger.info("Currency value deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to update currency value ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return currencyValue;
  }

  @Override
  public CurrencyValue delete(String toDelete) {
    CurrencyValue currencyValue = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        currencyValue =
            new CurrencyValue(
                resultSet.getString(CURRENCY_VALUE_ID_COLUMN),
                resultSet.getDate(CURRENCY_VALUE_DATE_COLUMN),
                resultSet.getDouble(EXCHANGE_RATE_COLUMN),
                resultSet.getString(SOURCE_CURRENCY_ID_COLUMN),
                resultSet.getString(DESTINATION_CURRENCY_ID_COLUMN));
      }

      logger.info("Currency value deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete currency value ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return currencyValue;
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
