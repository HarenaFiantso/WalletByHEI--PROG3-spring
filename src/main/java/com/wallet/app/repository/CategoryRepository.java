package com.wallet.app.repository;

import com.wallet.app.db.entity.Category;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class CategoryRepository implements CrudRepository<Category> {
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
    return null;
  }

  @Override
  public Category update(Category toUpdate) {
    return null;
  }

  @Override
  public Category delete(String toDelete) {
    return null;
  }

  @Override
  public void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {

  }
}
