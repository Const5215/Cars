package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class EmploymentRepository extends AbstractPage {

  public boolean oneReceptionistCheck(long centerId) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM EMPLOYMENT WHERE CENTER_ID=? AND POSITION=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, Role.Receptionist.ordinal());
      resultSet = preparedStatement.executeQuery();
      return resultSet.next();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return false;
  }

  public long getCenterIdByEmployeeId(long employeeId) {
    long centerId = -1;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT CENTER_ID FROM EMPLOYMENT WHERE EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        centerId = resultSet.getLong("CENTER_ID");
      } else {
        System.out.println("Center Id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return centerId;
  }

  public Employment getEmploymentByEmployeeId(long employeeId) {
    Employment employment = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM EMPLOYMENT WHERE EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employment = new Employment();
        employment.setEmployeeId(resultSet.getLong("EMPLOYEE_ID"));
        employment.setCenterId(resultSet.getLong("CENTER_ID"));
        employment.setPosition(Role.values()[resultSet.getInt("POSITION")]);
        employment.setCompensation(resultSet.getFloat("COMPENSATION"));
        employment.setStartDate(resultSet.getDate("START_DATE"));
      } else {
        System.out.println("employee id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return employment;
  }

  public float getCompensationByEmployeeId(long employeeId) {
    float compensation = 0;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYMENT WHERE EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        compensation = resultSet.getFloat("COMPENSATION");
      } else {
        System.out.println("employee Id not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return compensation;
  }

}
