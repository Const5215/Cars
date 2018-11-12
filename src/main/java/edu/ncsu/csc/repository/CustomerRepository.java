package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomerRepository extends AbstractPage {

  public User getCustomerByEmail(String email) {
    User customer = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * from CUSTOMER WHERE EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customer = new User();
        customer.setId(resultSet.getLong("ID"));
        customer.setPassword(resultSet.getString("PASSWORD"));
        customer.setName(resultSet.getString("NAME"));
        customer.setEmail(resultSet.getString("EMAIL"));
        customer.setPhone(resultSet.getString("PHONE"));
        customer.setAddress(resultSet.getString("ADDRESS"));
      } else {
        System.out.println("Email Address Not Found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return customer;
  }

  public void updateTable(String type, String val, long customerId) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      String query = "UPDATE CUSTOMER SET $type=? WHERE ID=?";
      query = query.replace("$type", type);
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, val);
      preparedStatement.setLong(2, customerId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public String getCustomerNameByCustomerId(long customerId) {
    String customerName = "";
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customerName = resultSet.getString("NAME");
      } else {
        System.out.println("customer Id not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return customerName;
  }

  public boolean checkUsedEmail(String email) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from CUSTOMER where EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        System.out.println("This email address is already used.");
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return true;
  }
}
