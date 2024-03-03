package com.wallet.app.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public interface CrudOperations<T> {
  T findById(Long toFind);

  List<T> findAll();

  List<T> saveAll(List<T> toSave);

  T save(T toSave);

  void delete(T toDelete);

  void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet);
}
