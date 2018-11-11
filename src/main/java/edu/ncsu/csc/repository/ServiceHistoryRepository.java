package edu.ncsu.csc.repository;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServiceHistoryRepository extends AbstractPage {

  public List<edu.ncsu.csc.entity.ServiceHistory> getServiceHistoryListByCustomerId(Long customerId) {
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM MAINTENANCE_HISTORY WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        edu.ncsu.csc.entity.ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setStartTime(new java.util.Date(resultSet.getDate("START_TIME").getTime()));
        serviceHistory.setEndTime(new java.util.Date(resultSet.getDate("END_TIME").getTime()));
        long timeDiff = serviceHistory.getEndTime().getTime() - serviceHistory.getStartTime().getTime();
        serviceHistory.setTotalLaborHour(TimeUnit.MILLISECONDS.toHours(timeDiff));
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
      preparedStatement = connection.prepareStatement("SELECT * FROM REPAIR_HISTORY WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        edu.ncsu.csc.entity.ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setStartTime(new java.util.Date(resultSet.getDate("START_TIME").getTime()));
        serviceHistory.setEndTime(new java.util.Date(resultSet.getDate("END_TIME").getTime()));
        long timeDiff = serviceHistory.getEndTime().getTime() - serviceHistory.getStartTime().getTime();
        serviceHistory.setTotalLaborHour(TimeUnit.MILLISECONDS.toHours(timeDiff));
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
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistoryList;
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
        long timeDiff = serviceHistory.getEndTime().getTime() - serviceHistory.getStartTime().getTime();
        serviceHistory.setTotalLaborHour(TimeUnit.MILLISECONDS.toHours(timeDiff));
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
          long timeDiff = serviceHistory.getEndTime().getTime() - serviceHistory.getStartTime().getTime();
          serviceHistory.setTotalLaborHour(TimeUnit.MILLISECONDS.toHours(timeDiff));
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
}
