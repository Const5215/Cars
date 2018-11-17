package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.User;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerRepository extends AbstractRepository {

  public void add(User customer) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("insert into CUSTOMER values (CUSTOMER_ID_SEQ.nextval, ?, ?, ?, ?, ?)");
      preparedStatement.setString(1, customer.getPassword());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.setString(3, customer.getEmail());
      preparedStatement.setString(4, customer.getPhone());
      preparedStatement.setString(5, customer.getAddress());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public void update(User customer) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "update CUSTOMER set PASSWORD=?, NAME=?, EMAIL=?, PHONE=?, ADDRESS=? where ID=?");
      preparedStatement.setString(1, customer.getPassword());
      preparedStatement.setString(2, customer.getName());
      preparedStatement.setString(3, customer.getEmail());
      preparedStatement.setString(4, customer.getPhone());
      preparedStatement.setString(5, customer.getAddress());
      preparedStatement.setLong(6, customer.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public User getCustomerById(Long id) {
    User customer = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CUSTOMER where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customer = new User(
            resultSet.getLong("ID"),
            resultSet.getString("PASSWORD"),
            resultSet.getString("NAME"),
            resultSet.getString("EMAIL"),
            resultSet.getString("PHONE"),
            resultSet.getString("ADDRESS")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return customer;
  }

  public User getCustomerByEmail(String email) {
    User customer = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CUSTOMER where EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customer = new User(
            resultSet.getLong("ID"),
            resultSet.getString("PASSWORD"),
            resultSet.getString("NAME"),
            resultSet.getString("EMAIL"),
            resultSet.getString("PHONE"),
            resultSet.getString("ADDRESS")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return customer;
  }

  public Long getIdByEmail(String email) {
    Long id = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CUSTOMER where EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        id = resultSet.getLong("ID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return id;
  }
}
