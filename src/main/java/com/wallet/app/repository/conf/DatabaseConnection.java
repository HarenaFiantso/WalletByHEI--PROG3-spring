package com.wallet.app.repository.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConnection {

  private static HikariDataSource dataSource;

  public DatabaseConnection() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(System.getenv("DB_URL"));
    config.setUsername(System.getenv("DB_USERNAME"));
    config.setPassword(System.getenv("DB_PASSWORD"));

    dataSource = new HikariDataSource(config);
  }

  @Bean
  public static Connection getConnection() throws SQLException {
    if (dataSource == null) {
      new DatabaseConnection();
    }
    return dataSource.getConnection();
  }
}
