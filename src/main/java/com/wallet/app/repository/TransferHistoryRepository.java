package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Account;
import com.wallet.app.db.entity.TransferHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransferHistoryRepository implements CrudRepository<TransferHistory> {
  private static final Logger logger = LoggerFactory.getLogger(TransferHistoryRepository.class);
  private static final String TRANSFER_HISTORY_ID_COLUMN = "transfer_history_id";
  private static final String TRANSFER_DATE_COLUMN = "transfer_date";
  private static final String DEBIT_TRANSACTION_ID_COLUMN = "debit_transaction_id";
  private static final String CREDIT_TRANSACTION_ID_COLUMN = "credit_transaction_id";

  private static final String SELECT_BY_ID_QUERY =
      "SELECT * FROM transfer_history WHERE transfer_history_id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM transfer_history";
  private static final String INSERT_QUERY =
      "INSERT INTO transfer_history (transfer_date, debit_transaction_id, credit_transaction_id)"
          + " VALUES (?, ?, ?) RETURNING *";
  private static final String UPDATE_QUERY =
      "UPDATE transfer_history SET transfer_date = ?, debit_transaction_id = ?,"
          + " credit_transaction_id = ? WHERE transfer_history_id = ? RETURNING *";
  private static final String DELETE_QUERY =
      "DELETE FROM transfer_history WHERE transfer_history_id = ?";

  @Override
  public TransferHistory findById(String toFind) {
    return null;
  }

  @Override
  public List<TransferHistory> findAll() {
    return null;
  }

  @Override
  public TransferHistory save(TransferHistory toSave) {
    return null;
  }

  @Override
  public TransferHistory update(TransferHistory toUpdate) {
    return null;
  }

  @Override
  public TransferHistory delete(String toDelete) {
    TransferHistory transferHistory = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        transferHistory =
            new TransferHistory(
                resultSet.getString(TRANSFER_HISTORY_ID_COLUMN),
                resultSet.getTimestamp(TRANSFER_DATE_COLUMN),
                resultSet.getString(DEBIT_TRANSACTION_ID_COLUMN),
                resultSet.getString(CREDIT_TRANSACTION_ID_COLUMN));
      }

      logger.info("Transfer history deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete transfer history ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return transferHistory;
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
