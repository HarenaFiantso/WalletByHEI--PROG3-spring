package com.wallet.app.config.database;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectToDb {
  private static Connection connection;

  public static Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        String DB_URL = System.getenv("DB_URL");
        String DB_USERNAME = System.getenv("DB_USERNAME");
        String DB_PASSWORD = System.getenv("DB_PASSWORD");

        try {
          connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
          throw new RuntimeException("Failed to connect to Database " + e.getMessage());
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to connect to Database " + e.getMessage());
    }

    return connection;
  }
}
