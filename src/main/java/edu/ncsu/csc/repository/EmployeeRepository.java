package edu.ncsu.csc.repository;

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

}
