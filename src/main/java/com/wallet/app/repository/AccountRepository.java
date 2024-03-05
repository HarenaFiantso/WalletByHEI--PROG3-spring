package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Account;
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
public class AccountRepository implements CrudRepository<Account> {
  private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

  private static final String ACCOUNT_ID_COLUMN = "account_id";
  private static final String ACCOUNT_NAME_COLUMN = "account_name";
  private static final String ACCOUNT_TYPE_COLUMN = "account_type";
  private static final String CURRENCY_ID_COLUMN = "currency_id";

  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM account WHERE account_id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM account";
  private static final String INSERT_QUERY =
      "INSERT INTO account (account_name, account_type, currency_id) VALUES (?, CAST(? AS"
          + " account_type), ?) RETURNING *";
  private static final String UPDATE_QUERY =
      "UPDATE account SET account_name = ?, account_type = CAST(? AS account_type), currency_id = ?"
          + " WHERE account_id = ? RETURNING *";
  private static final String DELETE_QUERY = "DELETE FROM account WHERE account_id = ?";

  @Override
  public Account findById(String toFind) {
    Account account = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
      statement.setString(1, toFind);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        account =
            new Account(
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(ACCOUNT_NAME_COLUMN),
                resultSet.getString(ACCOUNT_TYPE_COLUMN),
                resultSet.getString(CURRENCY_ID_COLUMN));
      }

      logger.info("Account retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve account ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return account;
  }

  @Override
  public List<Account> findAll() {
    List<Account> accounts = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(SELECT_ALL_QUERY);
      resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Account account =
            new Account(
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(ACCOUNT_NAME_COLUMN),
                resultSet.getString(ACCOUNT_TYPE_COLUMN),
                resultSet.getString(CURRENCY_ID_COLUMN));

        accounts.add(account);
      }
      logger.info("Accounts retrieved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to retrieve accounts ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return accounts;
  }

  @Override
  public Account save(Account toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(INSERT_QUERY);
      statement.setString(1, toSave.getAccountName());
      statement.setString(2, toSave.getAccountType());
      statement.setString(3, toSave.getCurrencyId());

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Account(
            resultSet.getString(ACCOUNT_ID_COLUMN),
            resultSet.getString(ACCOUNT_NAME_COLUMN),
            resultSet.getString(ACCOUNT_TYPE_COLUMN),
            resultSet.getString(CURRENCY_ID_COLUMN));
      }
      logger.info("Account saved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to save account ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public Account update(Account toUpdate) {
    Account account = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(UPDATE_QUERY);
      statement.setString(1, toUpdate.getAccountName());
      statement.setString(2, toUpdate.getAccountType());
      statement.setString(3, toUpdate.getCurrencyId());
      statement.setString(4, toUpdate.getAccountId());

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        account =
            new Account(
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(ACCOUNT_NAME_COLUMN),
                resultSet.getString(ACCOUNT_TYPE_COLUMN),
                resultSet.getString(CURRENCY_ID_COLUMN));
      }

      logger.info("Account updated successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to update account ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return account;
  }

  @Override
  public Account delete(String toDelete) {
    Account account = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        account =
            new Account(
                resultSet.getString(ACCOUNT_ID_COLUMN),
                resultSet.getString(ACCOUNT_NAME_COLUMN),
                resultSet.getString(ACCOUNT_TYPE_COLUMN),
                resultSet.getString(CURRENCY_ID_COLUMN));
      }

      logger.info("Account deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete account ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return account;
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
