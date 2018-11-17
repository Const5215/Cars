package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceType;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

  private List<ServiceHistory> getAllMaintenanceHistoriesByPreparedStatement(
      PreparedStatement preparedStatement) {
    List<ServiceHistory> maintenanceHistories = new ArrayList<ServiceHistory>();

    try {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        maintenanceHistories.add(new ServiceHistory(
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
    }

    return maintenanceHistories;
  }

  private List<ServiceHistory> getAllRepairHistoriesByPreparedStatement(
      PreparedStatement preparedStatement) {
    List<ServiceHistory> repairHistories = new ArrayList<ServiceHistory>();

    try {
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        repairHistories.add(new ServiceHistory(
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
    }

    return repairHistories;
  }

  public List<ServiceHistory> getAllServiceHistoriesByCenterId(Long centerId) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      serviceHistories.addAll(getAllMaintenanceHistoriesByPreparedStatement(preparedStatement));

      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      serviceHistories.addAll(getAllRepairHistoriesByPreparedStatement(preparedStatement));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public List<ServiceHistory> getAllServiceHistoriesByCustomerId(Long customerId) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      serviceHistories.addAll(getAllMaintenanceHistoriesByPreparedStatement(preparedStatement));

      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      serviceHistories.addAll(getAllRepairHistoriesByPreparedStatement(preparedStatement));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public ServiceHistory getLatestMaintenanceHistoryByCarPlate(String carPlate) {
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

  public List<ServiceHistory> getAllServiceHistoriesByCarPlate(String carPlate) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

      preparedStatement = connection
          .prepareStatement("select * from MAINTENANCE_HISTORY where CAR_PLATE=?");
      preparedStatement.setString(1, carPlate);
      serviceHistories.addAll(getAllMaintenanceHistoriesByPreparedStatement(preparedStatement));

      preparedStatement = connection
          .prepareStatement("select * from REPAIR_HISTORY where CAR_PLATE=?");
      preparedStatement.setString(1, carPlate);
      serviceHistories.addAll(getAllRepairHistoriesByPreparedStatement(preparedStatement));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public List<ServiceHistory> getAllServiceHistoriesByCarPlateAndWarranty(String carPlate,
      Integer warranty) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE_HISTORY where CAR_PLATE=? and END_TIME<sysdate and add_months(END_TIME, ?)>sysdate");
      preparedStatement.setString(1, carPlate);
      preparedStatement.setInt(2, warranty);
      serviceHistories.addAll(getAllMaintenanceHistoriesByPreparedStatement(preparedStatement));

      preparedStatement = connection.prepareStatement(
          "select * from REPAIR_HISTORY where CAR_PLATE=? and END_TIME<sysdate and add_months(END_TIME, ?)>sysdate");
      preparedStatement.setString(1, carPlate);
      preparedStatement.setInt(2, warranty);
      serviceHistories.addAll(getAllRepairHistoriesByPreparedStatement(preparedStatement));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }

  public List<ServiceHistory> getAllServiceHistoriesByMechanicIdAndStartTimeAndEndTime(
      Long mechanicId, Date startTime, Date endTime) {
    List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE_HISTORY where MECHANIC_ID=? and START_TIME>? and END_TIME<?");
      preparedStatement.setLong(1, mechanicId);
      preparedStatement.setDate(2, new java.sql.Date(startTime.getTime()));
      preparedStatement.setDate(3, new java.sql.Date(endTime.getTime()));
      serviceHistories.addAll(getAllMaintenanceHistoriesByPreparedStatement(preparedStatement));

      preparedStatement = connection.prepareStatement(
          "select * from REPAIR_HISTORY where MECHANIC_ID=? and START_TIME>=? and END_TIME<?");
      preparedStatement.setLong(1, mechanicId);
      preparedStatement.setDate(2, new java.sql.Date(startTime.getTime()));
      preparedStatement.setDate(3, new java.sql.Date(endTime.getTime()));
      serviceHistories.addAll(getAllRepairHistoriesByPreparedStatement(preparedStatement));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return serviceHistories;
  }
}
