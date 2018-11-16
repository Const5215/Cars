package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.User;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeRepository extends AbstractRepository {

  public void add(User employee) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement(
              "insert into EMPLOYEE (NAME, EMAIL, PHONE, ADDRESS) values (?, ?, ?, ?)");
      preparedStatement.setString(1, employee.getName());
      preparedStatement.setString(2, employee.getEmail());
      preparedStatement.setString(3, employee.getPhone());
      preparedStatement.setString(4, employee.getAddress());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public void update(User employee) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "update EMPLOYEE set PASSWORD=?, NAME=?, EMAIL=?, PHONE=?, ADDRESS=? where ID=?");
      preparedStatement.setString(1, employee.getPassword());
      preparedStatement.setString(2, employee.getEmail());
      preparedStatement.setString(3, employee.getPhone());
      preparedStatement.setString(4, employee.getAddress());
      preparedStatement.setLong(5, employee.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public User getEmployeeById(Long id) {
    User employee = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from EMPLOYEE where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employee = new User(
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

    return employee;
  }

  public Long getIdByEmail(String email) {
    Long id = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from EMPLOYEE where EMAIL=?");
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

  public String getNameById(Long id) {
    String name = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from EMPLOYEE where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        name = resultSet.getString("NAME");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return name;
  }
}
