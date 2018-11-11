package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.Maintenance;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;

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
}
