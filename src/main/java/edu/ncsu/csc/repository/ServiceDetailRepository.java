package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailRepository extends AbstractPage {
  public List<BasicService> getBasicServiceListByDiagnosisId(long diagnosisId) {
    List<BasicService> basicServiceList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM BASIC_SERVICE, REPAIR WHERE DIAGNOSIS_ID=? AND BASIC_SERVICE_ID = ID");
      preparedStatement.setLong(1, diagnosisId);
      resultSet = preparedStatement.executeQuery();
      basicServiceList = getBasicServiceList();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServiceList;
  }

  public List<BasicService> getBasicServiceListByMakeAndModelAndMaintenanceType(String make, String model, ServiceType serviceType) {
    List<BasicService> basicServiceList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      CarModelRepository carModelRepository = new CarModelRepository();
      long carModelId = carModelRepository.getCarModelIdByMakeAndModel(make, model);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM BASIC_SERVICE, MAINTENANCE_DETAIL WHERE CAR_MODEL_ID=? AND MAINTENANCE_TYPE=? AND BASIC_SERVICE_ID = ID");
      preparedStatement.setLong(1, carModelId);
      preparedStatement.setLong(2, serviceType.ordinal());
      resultSet = preparedStatement.executeQuery();
      basicServiceList = getBasicServiceList();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicServiceList;
  }

  private List<BasicService> getBasicServiceList() throws SQLException {
    List<BasicService> basicServiceList = new ArrayList<>();
    while (resultSet.next()) {
      BasicService basicService = new BasicService();
      basicService.setId(resultSet.getLong("ID"));
      basicService.setName(resultSet.getString("NAME"));
      basicService.setLaborHour(resultSet.getFloat("LABOR_HOUR"));
      basicService.setChargeRate(resultSet.getLong("CHARGE_RATE"));
      basicServiceList.add(basicService);
    }
    return basicServiceList;
  }
}
