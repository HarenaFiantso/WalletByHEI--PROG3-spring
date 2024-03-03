package com.wallet.app.conf;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wallet.app.repository.conf.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionTest {

  private static DatabaseConnection databaseConnection;

  @BeforeAll
  public static void setUp() {
    databaseConnection = new DatabaseConnection();
  }

  @AfterAll
  public static void tearDown() throws SQLException {
    if (databaseConnection != null) {
      DatabaseConnection.getConnection().close();
    }
  }

  @Test
  public void testConnectionNotNull() throws SQLException {
    Connection connection = DatabaseConnection.getConnection();

    assertNotNull(connection, "Connection should not be null");
  }

  @Test
  public void testConnectionIsOpen() throws SQLException {
    Connection connection = DatabaseConnection.getConnection();

    assertTrue(connection.isValid(5), "Connection should be open and valid");
  }
}
