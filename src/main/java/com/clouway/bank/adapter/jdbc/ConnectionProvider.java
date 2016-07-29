package com.clouway.bank.adapter.jdbc;

import com.clouway.bank.core.ConnectionException;
import com.clouway.bank.core.Provider;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Stanislava Kaukova(sisiivanovva@gmail.com)
 */
public class ConnectionProvider implements Provider<Connection> {
  private final String db;
  private final String user;
  private final String password;

  @Inject
  public ConnectionProvider(@Named("db") String db, @Named("user") String user, @Named("password") String password) {
    this.db = db;
    this.user = user;
    this.password = password;
  }

  @Override
  public Connection get() {
    try {
      return DriverManager.getConnection(db, user, password);
    } catch (SQLException e) {
      throw new ConnectionException("Cannot connect to database");
    }
  }
}
