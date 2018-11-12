package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Maintenance;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository extends AbstractPage {
  public void addMaintenance(Maintenance maintenance) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "INSERT INTO MAINTENANCE VALUES (?, ? ,?)");
      preparedStatement.setLong(1, maintenance.getCarModelId());
      preparedStatement.setLong(2, maintenance.getServiceType().ordinal());
      preparedStatement.setLong(3, maintenance.getMile());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

  }

  public List<Maintenance> getMaintenanceListByCarModelId(long carModelId) {
    List<Maintenance> maintenanceList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM MAINTENANCE WHERE CAR_MODEL_ID=?");
      preparedStatement.setLong(1, carModelId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Maintenance maintenance = new Maintenance();
        maintenance.setCarModelId(resultSet.getLong("CAR_MODEL_ID"));
        maintenance.setServiceType(ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")]);
        maintenance.setMile(resultSet.getLong("MILEAGE"));
        maintenanceList.add(maintenance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return maintenanceList;
  }
}
