package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Maintenance;
import edu.ncsu.csc.entity.ServiceType;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository extends AbstractRepository {

  public void add(Maintenance maintenance) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("insert into MAINTENANCE values (?, ? ,?)");
      preparedStatement.setLong(1, maintenance.getCarModelId());
      preparedStatement.setLong(2, maintenance.getServiceType().ordinal());
      preparedStatement.setLong(3, maintenance.getMileage());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public List<Maintenance> getAllMaintenanceByCarModelId(Long carModelId) {
    List<Maintenance> maintenances = new ArrayList<Maintenance>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE where CAR_MODEL_ID=? order by MAINTENANCE_TYPE");
      preparedStatement.setLong(1, carModelId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        maintenances.add(new Maintenance(
            resultSet.getLong("CAR_MODEL_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return maintenances;
  }

  public Maintenance getMaintenanceByCarModelIdAndMaintenanceType(Long carModelId,
      ServiceType type) {
    Maintenance maintenance = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE where CAR_MODEL_ID=? and MAINTENANCE_TYPE=?");
      preparedStatement.setLong(1, carModelId);
      preparedStatement.setInt(2, type.ordinal());
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        maintenance = new Maintenance(
            resultSet.getLong("CAR_MODEL_ID"),
            ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")],
            resultSet.getInt("MILEAGE"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return maintenance;
  }

  public ServiceType getMaintenanceTypeByCarModelIdAndMileage(Long carModelId, Integer mileage) {
    ServiceType maintenanceType = null;

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE where CAR_MODEL_ID=? and MILEAGE <= ? order by MILEAGE");
      preparedStatement.setLong(1, carModelId);
      preparedStatement.setInt(2, mileage);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        maintenanceType = ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")];
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return maintenanceType;
  }
}
