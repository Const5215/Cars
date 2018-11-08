package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewServiceHistory extends AbstractPage {
  private User customer;

  ViewServiceHistory(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewServiceHistory");
    List<ServiceHistory> serviceHistoryList = getServiceHistoryList();
    printServiceHistory(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private List<ServiceHistory> getServiceHistoryList() {
    List<ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR, SERVICE_HISTORY WHERE " +
              "CUSTOMER_ID=? AND PLATE=CAR_PLATE ");
      preparedStatement.setLong(1, customer.getId());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        ServiceHistory serviceHistory = getServiceHistory();
        serviceHistoryList.add(serviceHistory);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistoryList;
  }

  private void printServiceHistory(List<ServiceHistory> serviceHistoryList) {
    System.out.printf("Service history: %d in total.\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service history #%d:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name: " + getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Service Status: " + serviceHistory.getServiceStatus().toString());
    }
  }

  private ServiceHistory getServiceHistory() {
    ServiceHistory serviceHistory = new ServiceHistory();
    try {
      serviceHistory.setId(resultSet.getLong("ID"));
      serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
      serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("SERVICE_TYPE")]);
      serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
      serviceHistory.setStartTime(resultSet.getDate("START_TIME"));
      serviceHistory.setEndTime(resultSet.getDate("END_TIME"));
      serviceHistory.setServiceStatus(ServiceStatus.values()[resultSet.getInt("STATUS")]);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return serviceHistory;
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }

}
