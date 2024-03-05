package com.wallet.app.repository;

import com.wallet.app.db.DBConnection;
import com.wallet.app.db.entity.Account;
import com.wallet.app.db.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepository implements CrudRepository<Category> {
  private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

  private static final String CATEGORY_ID_COLUMN = "category_id";
  private static final String CATEGORY_NAME_COLUMN = "category_name";

  private static final String INSERT_QUERY =
      "INSERT INTO category (category_name) VALUES (?) RETURNING *";
  private static final String UPDATE_QUERY = "UPDATE category SET category_name = ? WHERE category_id = ?";
  private static final String DELETE_QUERY = "DELETE FROM category WHERE category_id = ?";
  @Override
  public Category findById(String toFind) {
    return null;
  }

  @Override
  public List<Category> findAll() {
    return null;
  }

  @Override
  public Category save(Category toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(INSERT_QUERY);
      statement.setString(1, toSave.getCategoryName());

      resultSet = statement.executeQuery();

      if (resultSet.next()) {
        return new Category(
            resultSet.getString(CATEGORY_ID_COLUMN),
            resultSet.getString(CATEGORY_NAME_COLUMN));
      }
      logger.info("Category saved successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to save category ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return null;
  }

  @Override
  public Category update(Category toUpdate) {
    Category category = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(UPDATE_QUERY);
      statement.setString(1, toUpdate.getCategoryName());

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        category =
            new Category(
                resultSet.getString(CATEGORY_ID_COLUMN),
                resultSet.getString(CATEGORY_NAME_COLUMN));
      }

      logger.info("category updated successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to update category ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return category;
  }

  @Override
  public Category delete(String toDelete) {
    Category category = null;
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = DBConnection.getConnection();

      statement = connection.prepareStatement(DELETE_QUERY);
      statement.setString(1, toDelete);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        category =
            new Category(
                resultSet.getString(CATEGORY_ID_COLUMN),
                resultSet.getString(CATEGORY_NAME_COLUMN));
      }

      logger.info("category deleted successfully ✅");
    } catch (SQLException e) {
      logger.error("Failed to delete category ❌: {}", e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }

    return category;
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
