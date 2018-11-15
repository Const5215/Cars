package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class MaintenanceDetailRepository extends AbstractPage {
  List<Long> getBasicServiceIdListByCarModelIdAndMaintenanceType(long carModelId, ServiceType maintenanceType) {
    List<Long> basicServiceIdList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM MAINTENANCE_DETAIL WHERE CAR_MODEL_ID=? AND MAINTENANCE_TYPE=?");
      preparedStatement.setLong(1, carModelId);
      preparedStatement.setLong(2, maintenanceType.ordinal());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Long basicServiceId = resultSet.getLong("BASIC_SERVICE_ID");
        basicServiceIdList.add(basicServiceId);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServiceIdList;
  }
}
