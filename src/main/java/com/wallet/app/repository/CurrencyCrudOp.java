package com.wallet.app.repository;

import com.wallet.app.repository.model.Currency;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
