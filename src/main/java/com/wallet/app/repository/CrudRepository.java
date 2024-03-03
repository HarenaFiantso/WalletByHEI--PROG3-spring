package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public interface CrudRepository<T> {
  T findById(String toFind);

  List<T> findAll();

  List<T> saveAll(List<T> toSave);

  T save(String toSave);

  void delete(String toDelete);

  void closeResources(Connection connection, Statement statement, ResultSet resultSet);
}
