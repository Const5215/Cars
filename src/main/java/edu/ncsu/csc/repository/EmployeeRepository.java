package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeRepository extends AbstractPage {
  public String getEmployeeNameByEmployeeId(long employeeId) {
    String employeeName = "";
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employeeName = resultSet.getString("NAME");
      } else {
        System.out.println("employee Id not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return employeeName;
  }

  public void addEmployee(User employee) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEE values (EMPLOYEE_ID_SEQ.nextval, ?, ?, ?, ?, ?)");
      preparedStatement.setString(1, employee.getPassword());
      preparedStatement.setString(2, employee.getName());
      preparedStatement.setString(3, employee.getEmail());
      preparedStatement.setString(4, employee.getPhone());
      preparedStatement.setString(5, employee.getAddress());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public void updateTable(String type, String val, long employeeId) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      String query = "UPDATE EMPLOYEE SET $type=? WHERE ID=?";
      query = query.replace("$type", type);
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, val);
      preparedStatement.setLong(2, employeeId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public Long getEmployeeIdByEmail(String email) {
    long employeeId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM EMPLOYEE WHERE EMAIL=?");
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employeeId = resultSet.getLong("ID");
      } else {
        System.out.println("employee email not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return employeeId;
  }
}
