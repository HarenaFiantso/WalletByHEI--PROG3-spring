package com.wallet.app.repository;

import java.util.List;

public interface CrudOperations<T> {
  T findById(T toFind);

  List<T> findAll();

  int save(T toSave);

  int update(T toUpdate);

  void delete(T toDelete);
}
