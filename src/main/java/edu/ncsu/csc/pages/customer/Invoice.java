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

public class Invoice extends AbstractPage {
  private User customer;

  Invoice(User customer) {
    this.customer = customer;
    choices.add("View Invoice Details");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#Invoice");

    List<ServiceHistory> serviceHistoryList = getServiceHistoryList();
    printServiceHistoryList(serviceHistoryList);
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        viewInvoiceDetails();
        break;
      case 2:
        goBack();
    }
  }

  private void printServiceHistoryList(List<ServiceHistory> serviceHistoryList) {
    try {
      System.out.printf("You have %d completed service(s) in total.\n\n", serviceHistoryList.size());
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM SERVICE_HISTORY_DETAIL WHERE SERVICE_HISTORY_ID=?");
      for (int i = 0; i < serviceHistoryList.size(); i++) {
        ServiceHistory serviceHistory = serviceHistoryList.get(i);
        System.out.printf("Details for service #%d:\n", i);
        float totalServiceCost = getMechanicHourlyWageByEmployeeId(serviceHistory.getMechanicId())
            * serviceHistory.getTotalLaborHour();
        preparedStatement.setLong(1, serviceHistory.getId());
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          totalServiceCost += getPartUnitPriceByPartId(resultSet.getLong("PART_ID"))
              * resultSet.getLong("QUANTITY");
        }
        System.out.println("Service ID: " + serviceHistory.getId());
        System.out.println("Service Start Time: " + serviceHistory.getStartTime());
        System.out.println("Service End Time: " + serviceHistory.getEndTime());
        System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
        System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
        System.out.println("Mechanic Name:" + getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
        System.out.println("Total Service Cost: " + totalServiceCost);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }

  private List<ServiceHistory> getServiceHistoryList() {
    List<ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR, SERVICE_HISTORY, SERVICE_HISTORY_DETAIL " +
              "WHERE CUSTOMER_ID=? AND PLATE = CAR_PLATE AND STATUS=?");
      preparedStatement.setLong(1, customer.getId());
      preparedStatement.setLong(2, ServiceStatus.Complete.ordinal());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setId(resultSet.getLong("ID"));
        serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
        serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("SERVICE_TYPE")]);
        serviceHistory.setStartTime(resultSet.getDate("START_TIME"));
        serviceHistory.setEndTime(resultSet.getDate("END_TIME"));
        serviceHistory.setTotalLaborHour(resultSet.getLong("TOTAL_LABOR_HOUR"));
        serviceHistory.setServiceStatus(ServiceStatus.values()[resultSet.getInt("STATUS")]);
        serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
        serviceHistoryList.add(serviceHistory);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistoryList;
  }

  private void goBack() {
    Page customerLanding = new CustomerLanding(customer);
    customerLanding.run();
  }

  private void viewInvoiceDetails() {
    Page viewInvoiceDetails = new ViewInvoiceDetails(customer);
    viewInvoiceDetails.run();
  }
}
