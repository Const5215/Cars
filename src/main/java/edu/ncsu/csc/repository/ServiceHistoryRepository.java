package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceType;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceHistoryRepository extends AbstractRepository {

  public void add(ServiceHistory serviceHistory) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      if (serviceHistory.getServiceType() == ServiceType.Repair) {
        preparedStatement = connection.prepareStatement(
            "insert into REPAIR_HISTORY values (SERVICE_HISTORY_ID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setLong(4, serviceHistory.getDiagnosisId());
      } else {
        preparedStatement = connection.prepareStatement(
            "insert into MAINTENANCE_HISTORY values (SERVICE_HISTORY_ID_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(4, serviceHistory.getServiceType().ordinal());
      }
      preparedStatement.setLong(1, serviceHistory.getCustomerId());
      preparedStatement.setString(2, serviceHistory.getCarPlate());
      preparedStatement.setLong(3, serviceHistory.getCenterId());
      preparedStatement.setInt(5, serviceHistory.getMileage());
      preparedStatement.setDate(6, new java.sql.Date(serviceHistory.getStartTime().getTime()));
      preparedStatement.setDate(7, new java.sql.Date(serviceHistory.getEndTime().getTime()));
      preparedStatement.setLong(8, serviceHistory.getMechanicId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public ServiceHistory getServiceHistoryById(Long id) {
    ServiceHistory serviceHistory = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        serviceHistory = new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("select * from REPAIR_HISTORY where ID=?");
      preparedStatement.setLong(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        serviceHistory = new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.Repair,
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"),
            resultSet.getLong("DIAGNOSIS_ID"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistory;
  }

  public List<ServiceHistory> getServiceHistoriesByCenterId(Long centerId) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.Repair,
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"),
            resultSet.getLong("DIAGNOSIS_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public List<ServiceHistory> getServiceHistoriesByCustomerId(Long customerId) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.Repair,
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"),
            resultSet.getLong("DIAGNOSIS_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public ServiceHistory getLastServiceHistoryByCarPlate(String carPlate) {
    ServiceHistory serviceHistory = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE_HISTORY where CAR_PLATE=? and END_TIME<sysdate order by END_TIME desc");
      preparedStatement.setString(1, carPlate);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        serviceHistory = new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID")
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistory;
  }

  public List<ServiceHistory> getServiceHistoriesByCarPlate(String carPlate) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement(
              "select * from MAINTENANCE_HISTORY where CAR_PLATE=?");
      preparedStatement.setString(1, carPlate);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CAR_PLATE=?");
      preparedStatement.setString(1, carPlate);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.Repair,
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"),
            resultSet.getLong("DIAGNOSIS_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public List<ServiceHistory> getServiceHistoriesBetweenDate(Date startDate, Date endDate) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where END_TIME > ? and END_TIME < ?");
      preparedStatement.setDate(1, startDate);
      preparedStatement.setDate(2, endDate);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID")));
      }
      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where END_TIME > ? and END_TIME < ?");
      preparedStatement.setDate(1, startDate);
      preparedStatement.setDate(2, endDate);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        serviceHistories.add(new ServiceHistory(
            resultSet.getLong("ID"),
            resultSet.getLong("CUSTOMER_ID"),
            resultSet.getString("CAR_PLATE"),
            resultSet.getLong("CENTER_ID"),
            ServiceType.Repair,
            resultSet.getInt("MILEAGE"),
            resultSet.getDate("START_TIME"),
            resultSet.getDate("END_TIME"),
            resultSet.getLong("MECHANIC_ID"),
            resultSet.getLong("DIAGNOSIS_ID")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistories;
  }
}
