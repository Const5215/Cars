package edu.ncsu.csc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractRepository {

  protected static final String URL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
  protected static final String USERNAME = "hwang75";
  protected static final String PASSWORD = "-swap255";
  protected Connection connection;
  protected PreparedStatement preparedStatement;
  protected ResultSet resultSet;

  protected void closeSqlConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
