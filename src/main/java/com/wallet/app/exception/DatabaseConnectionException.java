package com.wallet.app.exception;

public class DatabaseConnectionException extends BadValueException {
  public DatabaseConnectionException(String message) {
    super((message));
  }
}
