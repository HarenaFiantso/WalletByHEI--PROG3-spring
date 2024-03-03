package com.wallet.app.repository;

import java.util.List;

public interface CrudOperations<T> {
  T findById(T toFind);

  List<T> findAll();

  List<T> saveAll(List<T> toSave);

  T save(T toSave);

  void delete(T toDelete);
}
