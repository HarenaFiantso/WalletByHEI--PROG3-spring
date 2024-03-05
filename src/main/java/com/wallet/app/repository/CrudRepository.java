package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface CrudRepository<T> {
  T findById(String toFind);

  List<T> findAll();

  T save(T toSave);

  T update(T toUpdate);

  T delete(String toDelete);

  void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet);
}
