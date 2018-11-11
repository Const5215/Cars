package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.*;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServiceHistoryRepository extends AbstractPage {
  public List<edu.ncsu.csc.entity.ServiceHistory> getServiceHistoryListByCenterId(Long centerId) {
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM MAINTENANCE_HISTORY WHERE CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      addMaintenanceHistory(serviceHistoryList);
      preparedStatement = connection.prepareStatement("SELECT * FROM REPAIR_HISTORY WHERE CENTER_ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      addRepairHistory(serviceHistoryList);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistoryList;
  }

  public List<edu.ncsu.csc.entity.ServiceHistory> getServiceHistoryListByCustomerId(Long customerId) {
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM MAINTENANCE_HISTORY WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      addMaintenanceHistory(serviceHistoryList);
      preparedStatement = connection.prepareStatement("SELECT * FROM REPAIR_HISTORY WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      addRepairHistory(serviceHistoryList);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistoryList;
  }

  private void addMaintenanceHistory(List<ServiceHistory> serviceHistoryList) throws SQLException {
    while (resultSet.next()) {
      ServiceHistory serviceHistory = new ServiceHistory();
      serviceHistory.setStartTime(new Date(resultSet.getDate("START_TIME").getTime()));
      serviceHistory.setEndTime(new Date(resultSet.getDate("END_TIME").getTime()));
      serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
      serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
      serviceHistory.setMileage(resultSet.getLong("MILEAGE"));
      serviceHistory.setServiceStatus(
          checkServiceHistoryStatus(serviceHistory.getStartTime(), serviceHistory.getEndTime())
      );
      serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")]);
      serviceHistory.setId(resultSet.getLong("ID"));
      serviceHistory.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
      serviceHistory.setCenterId(resultSet.getLong("CENTER_ID"));
      serviceHistoryList.add(serviceHistory);
    }
  }

  private void addRepairHistory(List<ServiceHistory> serviceHistoryList) throws SQLException {
    while (resultSet.next()) {
      ServiceHistory serviceHistory = new ServiceHistory();
      serviceHistory.setStartTime(new Date(resultSet.getDate("START_TIME").getTime()));
      serviceHistory.setEndTime(new Date(resultSet.getDate("END_TIME").getTime()));
      serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
      serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
      serviceHistory.setMileage(resultSet.getLong("MILEAGE"));
      serviceHistory.setServiceStatus(
          checkServiceHistoryStatus(serviceHistory.getStartTime(), serviceHistory.getEndTime())
      );
      serviceHistory.setServiceType(ServiceType.Repair);
      serviceHistory.setDiagnosisId(resultSet.getLong("DIAGNOSIS_ID"));
      serviceHistory.setId(resultSet.getLong("ID"));
      serviceHistory.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
      serviceHistory.setCenterId(resultSet.getLong("CENTER_ID"));
      serviceHistoryList.add(serviceHistory);
    }
  }

  public ServiceHistory getServiceHistoryByServiceId(long serviceId) {
    ServiceHistory serviceHistory = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM MAINTENANCE_HISTORY WHERE ID=?");
      preparedStatement.setLong(1, serviceId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        serviceHistory = new ServiceHistory();
        serviceHistory.setId(resultSet.getLong("ID"));
        serviceHistory.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
        serviceHistory.setCenterId(resultSet.getLong("CENTER_ID"));
        serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
        serviceHistory.setMileage(resultSet.getLong("MILEAGE"));
        serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("MAINTENANCE_TYPE")]);
        serviceHistory.setStartTime(new java.util.Date(resultSet.getDate("START_TIME").getTime()));
        serviceHistory.setEndTime(new java.util.Date(resultSet.getDate("END_TIME").getTime()));
        serviceHistory.setServiceStatus(checkServiceHistoryStatus(serviceHistory.getStartTime(), serviceHistory.getEndTime()));
        serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
      } else {
        preparedStatement = connection.prepareStatement("SELECT * FROM REPAIR_HISTORY WHERE ID=?");
        preparedStatement.setLong(1, serviceId);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
          serviceHistory = new ServiceHistory();
          serviceHistory.setId(resultSet.getLong("ID"));
          serviceHistory.setCustomerId(resultSet.getLong("CUSTOMER_ID"));
          serviceHistory.setCenterId(resultSet.getLong("CENTER_ID"));
          serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
          serviceHistory.setMileage(resultSet.getLong("MILEAGE"));
          serviceHistory.setServiceType(ServiceType.Repair);
          serviceHistory.setDiagnosisId(resultSet.getLong("DIAGNOSIS_ID"));
          serviceHistory.setStartTime(resultSet.getDate("START_TIME"));
          serviceHistory.setEndTime(resultSet.getDate("END_TIME"));
          serviceHistory.setServiceStatus(
              checkServiceHistoryStatus(serviceHistory.getStartTime(), serviceHistory.getEndTime())
          );
          serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistory;
  }

  private ServiceStatus checkServiceHistoryStatus(Date startTime, Date endTime) {
    ServiceStatus serviceStatus;
    if (endTime.getTime() < new java.util.Date().getTime()) {
      serviceStatus = ServiceStatus.Complete;
    } else if (startTime.getTime() > new java.util.Date().getTime()) {
      serviceStatus = ServiceStatus.Pending;
    } else {
      serviceStatus = ServiceStatus.Ongoing;
    }
    return serviceStatus;
  }

  private Date getLatestServiceHistoryByCustomerIdAndDate(long customerId, Date startTime) {
    Date latestServiceDate = null;
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT START_TIME FROM MAINTENANCE_HISTORY WHERE CUSTOMER_ID=? AND START_TIME<?");
      preparedStatement.setLong(1, customerId);
      preparedStatement.setDate(2, new java.sql.Date(startTime.getTime()));
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Date thsDate = resultSet.getDate("START_TIME");
        if (latestServiceDate == null ||
            latestServiceDate.getTime() < thsDate.getTime()) {
          latestServiceDate = thsDate;
        }
      }
      preparedStatement = connection.prepareStatement(
          "SELECT START_TIME FROM REPAIR_HISTORY WHERE CUSTOMER_ID=? AND START_TIME<?");
      preparedStatement.setLong(1, customerId);
      preparedStatement.setDate(2, new java.sql.Date(startTime.getTime()));
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Date thsDate = resultSet.getDate("START_TIME");
        if (latestServiceDate == null ||
            latestServiceDate.getTime() < thsDate.getTime()) {
          latestServiceDate = thsDate;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return latestServiceDate;
  }

  public float getTotalServiceCost(ServiceHistory serviceHistory) {
    float totalServiceCost = 0;
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    List<Long> basicServiceIdList = basicServiceRepository.getBasicServiceIdListByServiceHistory(serviceHistory);
    for (Long basicServiceId : basicServiceIdList) {
      totalServiceCost += getBasicServiceCost(serviceHistory, basicServiceId);
    }
    return totalServiceCost;
  }

  private float getBasicServiceCost(ServiceHistory serviceHistory, Long basicServiceId) {
    return getPartCost(serviceHistory, basicServiceId) + getLaborCost(serviceHistory, basicServiceId);
  }

  private float getLaborCost(ServiceHistory serviceHistory, Long basicServiceId) {
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    CarRepository carRepository = new CarRepository();

    BasicService basicService = basicServiceRepository.getBasicServiceByBasicServiceId(basicServiceId);
    Car car = carRepository.getCarByCarPlate(serviceHistory.getCarPlate());
    BasicServicePart basicServicePart =
        basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
            basicServiceId, car.getCarModelId()
        );
    PartRepository partRepository = new PartRepository();
    Part part = partRepository.getPartByPartId(basicServicePart.getPartId());
    Date latestServiceDate = getLatestServiceHistoryByCustomerIdAndDate(
        serviceHistory.getCustomerId(), serviceHistory.getStartTime());
    if (latestServiceDate != null) {
      if (part.getWarranty() == 0 ||
          checkWarrantyInvalid(part.getWarranty(), serviceHistory.getStartTime(),
              latestServiceDate)) {
        //invoice cost of parts and appropriate labor charge
        return (basicService.getChargeRate() == 0 ? 50 : 65) * basicService.getLaborHour();
      }
      //else free service
      else {
        return 0;
      }
    } else {
      //invoice only the cost of parts
      return 0;
    }
  }

  private float getPartCost(ServiceHistory serviceHistory, Long basicServiceId) {
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    CarRepository carRepository = new CarRepository();

    Car car = carRepository.getCarByCarPlate(serviceHistory.getCarPlate());
    BasicServicePart basicServicePart =
        basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
            basicServiceId, car.getCarModelId()
        );
    PartRepository partRepository = new PartRepository();
    Part part = partRepository.getPartByPartId(basicServicePart.getPartId());
    Date latestServiceDate = getLatestServiceHistoryByCustomerIdAndDate(
        serviceHistory.getCustomerId(), serviceHistory.getStartTime());
    if (latestServiceDate != null) {
      if (part.getWarranty() == 0 ||
          checkWarrantyInvalid(part.getWarranty(), serviceHistory.getStartTime(),
              latestServiceDate)) {
        //invoice cost of parts and appropriate labor charge
        return part.getUnitPrice() * basicServicePart.getQuantity();
      }
      //else free service
      else {
        return 0;
      }
    } else {
      //invoice only the cost of parts
      return part.getUnitPrice() * basicServicePart.getQuantity();
    }
  }

  private boolean checkWarrantyInvalid(long warranty, Date nowServiceDate, Date LastServiceDate) {
    Calendar nowServiceCal = Calendar.getInstance();
    nowServiceCal.setTime(nowServiceDate);
    Calendar lastServiceCal = Calendar.getInstance();
    lastServiceCal.setTime(LastServiceDate);
    lastServiceCal.add(Calendar.MONTH, (int) warranty);
    return lastServiceCal.getTime().getTime() <= nowServiceCal.getTime().getTime();
  }

  public void printServiceHistory(ServiceHistory serviceHistory) {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    String mechanicName = employeeRepository.getEmployeeNameByEmployeeId(serviceHistory.getMechanicId());
    System.out.println("Service ID: " + serviceHistory.getId());
    System.out.println("Service Start Time: " + serviceHistory.getStartTime());
    System.out.println("Service End Time: " + serviceHistory.getEndTime());
    System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
    System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
    System.out.println("Mechanic Name:" + mechanicName);
    float totalServiceCost = printPartDetails(serviceHistory);
    System.out.println("Total Service Cost: " + totalServiceCost);
  }

  public float printPartDetails(ServiceHistory serviceHistory) {
    CarRepository carRepository = new CarRepository();
    BasicServiceRepository basicServiceRepository = new BasicServiceRepository();
    BasicServicePartRepository basicServicePartRepository = new BasicServicePartRepository();
    PartRepository partRepository = new PartRepository();

    float totalServiceCost = 0;
    List<Long> basicServiceIdList = basicServiceRepository.getBasicServiceIdListByServiceHistory(serviceHistory);
    Car car = carRepository.getCarByCarPlate(serviceHistory.getCarPlate());
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    for (long basicServiceId : basicServiceIdList) {
      BasicServicePart basicServicePart = basicServicePartRepository.getBasicServicePartByBasicServiceIdAndCarModelId(
          basicServiceId, car.getCarModelId()
      );
      BasicService basicService = basicServiceRepository.getBasicServiceByBasicServiceId(basicServiceId);
      Part part = partRepository.getPartByPartId(basicServicePart.getPartId());
      float partCost = serviceHistoryRepository.getPartCost(serviceHistory, basicServiceId);
      float laborCost = serviceHistoryRepository.getLaborCost(serviceHistory, basicServiceId);
      System.out.printf("Part: %s Quantity: %d Part charge: %f/Labor hour:%f Labor charge:%f\n",
          part.getName(),
          basicServicePart.getQuantity(),
          partCost,
          basicService.getLaborHour(),
          laborCost
      );
      totalServiceCost += partCost + laborCost;
    }
    return totalServiceCost;
  }
}
