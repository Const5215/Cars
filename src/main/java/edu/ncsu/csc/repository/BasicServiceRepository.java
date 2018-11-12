package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.BasicService;
import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BasicServiceRepository extends AbstractPage {
  public BasicService getBasicServiceByBasicServiceId(long basicServiceId) {
    BasicService basicService = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM BASIC_SERVICE WHERE ID=?");
      preparedStatement.setLong(1, basicServiceId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        basicService = new BasicService();
        basicService.setId(resultSet.getLong("ID"));
        basicService.setName(resultSet.getString("NAME"));
        basicService.setLaborHour(resultSet.getFloat("LABOR_HOUR"));
        basicService.setChargeRate(resultSet.getLong("CHARGE_RATE"));
      } else {
        System.out.println("basic service id not found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return basicService;
  }

  public List<Long> getBasicServiceIdListByServiceHistory(ServiceHistory serviceHistory) {
    List<Long> basicServiceIdList;
    CarRepository carRepository = new CarRepository();
    Car car = carRepository.getCarByCarPlate(serviceHistory.getCarPlate());
    if (serviceHistory.getServiceType() == ServiceType.Repair) {
      RepairRepository repairRepository = new RepairRepository();
      basicServiceIdList = repairRepository.getBasicServiceIdListByDiagnosisId(serviceHistory.getDiagnosisId());
    } else {
      MaintenanceDetailRepository maintenanceDetailRepository = new MaintenanceDetailRepository();
      basicServiceIdList = maintenanceDetailRepository.getBasicServiceIdListByCarModelIdAndMaintenanceType(
          car.getCarModelId(), serviceHistory.getServiceType()
      );
    }
    return basicServiceIdList;
  }
}
