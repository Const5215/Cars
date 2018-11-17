package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmploymentRepository extends AbstractRepository {

  public void add(Employment employment) {
    try {
      preparedStatement.executeUpdate();
      preparedStatement = connection
          .prepareStatement("insert into EMPLOYMENT values (?, ?, ?, ?, ?)");
      preparedStatement.setLong(1, employment.getEmployeeId());
      preparedStatement.setLong(2, employment.getCenterId());
      preparedStatement.setInt(3, employment.getPosition().ordinal());
      preparedStatement.setFloat(4, employment.getCompensation());
      preparedStatement.setDate(5, new java.sql.Date(employment.getStartDate().getTime()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public Employment getEmploymentByEmployeeId(Long employeeId) {
    Employment employment = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from EMPLOYMENT where EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employment = new Employment(
            resultSet.getLong("EMPLOYEE_ID"),
            resultSet.getLong("CENTER_ID"),
            Role.values()[resultSet.getInt("POSITION")],
            resultSet.getFloat("COMPENSATION"),
            resultSet.getDate("START_DATE")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return employment;
  }

  public Employment getReceptionistByCenterId(Long centerId) {
    Employment receptionist = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from EMPLOYMENT where CENTER_ID=? and POSITION=1");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        receptionist = new Employment(
            resultSet.getLong("EMPLOYEE_ID"),
            resultSet.getLong("CENTER_ID"),
            Role.Receptionist,
            resultSet.getFloat("COMPENSATION"),
            resultSet.getDate("START_DATE")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return receptionist;
  }

  public Long getCenterIdByEmployeeId(Long employeeId) {
    Long centerId = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from EMPLOYMENT where EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        centerId = resultSet.getLong("CENTER_ID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return centerId;
  }

  public List<Long> getMechanicsByCenterId(Long centerId) {
    List<Long> mechanicIdList = new ArrayList<Long>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from EMPLOYMENT where CENTER_ID=? AND POSITION=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, Role.Mechanic.ordinal());
      resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        mechanicIdList.add(resultSet.getLong("EMPLOYEE_ID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return mechanicIdList;
  }

}
