package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.MaintenanceDetail;
import edu.ncsu.csc.entity.ServiceType;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDetailRepository extends AbstractRepository {

  public void add(MaintenanceDetail maintenanceDetail) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection
          .prepareStatement("insert into MAINTENANCE_DETAIL values (?, ?, ?)");
      preparedStatement.setLong(1, maintenanceDetail.getCarModelId());
      preparedStatement.setInt(2, maintenanceDetail.getMaintenanceType().ordinal());
      preparedStatement.setLong(3, maintenanceDetail.getBasicServiceId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  public List<BasicService> getAllBasicServicesByCarModelIdAndMaintenanceType(Long carModelId,
      ServiceType maintenanceType) {
    List<BasicService> basicServices = new ArrayList<BasicService>();

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "select * from MAINTENANCE_DETAIL where CAR_MODEL_ID=? and MAINTENANCE_TYPE<=?");
      preparedStatement.setLong(1, carModelId);
      preparedStatement.setLong(2, maintenanceType.ordinal());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        basicServices.add(new BasicService(
            resultSet.getLong("BASIC_SERVICE_ID"),
            resultSet.getString("NAME"),
            resultSet.getFloat("LABOR_HOUR"),
            resultSet.getInt("CHARGE_RATE"))
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }

    return basicServices;
  }
}
