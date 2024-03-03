package com.wallet.app.repository;

import com.wallet.app.repository.conf.DatabaseConnection;
import com.wallet.app.repository.model.Currency;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    Currency currency = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DatabaseConnection.getConnection();

      statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
      statement.setString(1, toFind.getCurrencyId());

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        currency = new Currency();
        currency.setCurrencyId(resultSet.getString(CURRENCY_ID_COLUMN));
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to retrieve currency : " + e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return currency;
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
  public Currency delete(Currency toDelete) {
    return null;
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
