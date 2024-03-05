package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Account;
import com.wallet.app.db.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepository implements CrudRepository<Transaction> {
  private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

  private static final String TRANSACTION_ID_COLUMN = "transaction_id";
  private static final String TRANSACTION_DATE_COLUMN = "transaction_date";
  private static final String TRANSACTION_TYPE_COLUMN = "transaction_type";
  private static final String AMOUNT_COLUMN = "amount";
  private static final String LABEL_COLUMN = "label";
  private static final String ACCOUNT_ID_COLUMN = "account_id";
  private static final String CATEGORY_ID_COLUMN = "category_id";

  private static final String SELECT_BY_ID_QUERY =
      "SELECT * FROM transaction WHERE transaction_id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM transaction";
  private static final String INSERT_QUERY =
      "INSERT INTO transaction (transaction_date, transaction_type, amount, label, account_id, category_id"
          + ") VALUES (?, CAST(? AS transaction_type), ?, ?, ?, ?) RETURNING *";
  private static final String UPDATE_QUERY =
      "UPDATE transaction SET transaction_date = ?, transaction_type = CAST(? AS account_type),"
          + " amount = ?, label = ?, account_id = ?, category_id = ? WHERE transaction_id = ?"
          + " RETURNING *";
  private static final String DELETE_QUERY = "DELETE FROM transaction WHERE transaction_id = ?";

  @Override
  public Transaction findById(String toFind) {
    return null;
  }

  @Override
  public List<Transaction> findAll() {
    return null;
  }

  @Override
  public Transaction save(Transaction toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(INSERT_QUERY);
      statement.setTimestamp(1, toSave.getTransactionDate());
      statement.setString(2, toSave.getTransactionType());
      statement.setDouble(3, toSave.getAmount());
      statement.setString(4, toSave.getLabel());
      statement.setString(5, toSave.getAccountId());
      statement.setString(6, toSave.getCategoryId());

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Transaction(
            resultSet.getString(TRANSACTION_ID_COLUMN),
            resultSet.getTimestamp(TRANSACTION_DATE_COLUMN),
            resultSet.getString(TRANSACTION_TYPE_COLUMN),
            resultSet.getDouble(AMOUNT_COLUMN),
            resultSet.getString(LABEL_COLUMN),
            resultSet.getString(ACCOUNT_ID_COLUMN),
            resultSet.getString(CATEGORY_ID_COLUMN));
      }
      logger.info("Transaction saved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to save transaction ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public Transaction update(Transaction toUpdate) {
    Transaction transaction = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(UPDATE_QUERY);
      statement.setTimestamp(1, toUpdate.getTransactionDate());
      statement.setString(2, toUpdate.getTransactionType());
      statement.setDouble(3, toUpdate.getAmount());
      statement.setString(4, toUpdate.getLabel());
      statement.setString(5, toUpdate.getAccountId());
      statement.setString(6, toUpdate.getCategoryId());
      statement.setString(7, toUpdate.getTransactionId());

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        transaction =
            new Transaction(
                resultSet.getString(TRANSACTION_ID_COLUMN),
                resultSet.getTimestamp(TRANSACTION_DATE_COLUMN),
                resultSet.getString(TRANSACTION_TYPE_COLUMN),
                resultSet.getDouble(AMOUNT_COLUMN),
                resultSet.getString(LABEL_COLUMN),
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(CATEGORY_ID_COLUMN));
      }

      logger.info("Transaction updated successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to update transaction ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return transaction;
  }

  @Override
  public Transaction delete(String toDelete) {
    Transaction transaction = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        transaction =
            new Transaction(
                resultSet.getString(TRANSACTION_ID_COLUMN),
                resultSet.getTimestamp(TRANSACTION_DATE_COLUMN),
                resultSet.getString(TRANSACTION_TYPE_COLUMN),
                resultSet.getDouble(AMOUNT_COLUMN),
                resultSet.getString(LABEL_COLUMN),
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(CATEGORY_ID_COLUMN));
      }

      logger.info("Transaction deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete transaction ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return transaction;
  }

  @Override
  public void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
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
