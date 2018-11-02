package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Invoices extends AbstractPage {
  private User manager;

  Invoices(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#invoices");
    String email = getInfo("Enter customer email address: ", MatchType.Email);
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = getServiceHistoryList(getCustomerByEmail(email));
    printServiceHistoryList(serviceHistoryList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      edu.ncsu.csc.entity.ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Customer has %d service list.\n", serviceHistoryList.size());
      System.out.printf("Service #%d details:\n", i);
      printServiceHistory(serviceHistory);
    }
  }

  private List<edu.ncsu.csc.entity.ServiceHistory> getServiceHistoryList(User customer) {
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement(
          "SELECT * FROM CAR, SERVICE_HISTORY, SERVICE_HISTORY_DETAIL " +
              "WHERE CUSTOMER_ID=? AND PLATE = CAR_PLATE AND STATUS=?");
      preparedStatement.setLong(1, customer.getId());
      preparedStatement.setLong(2, ServiceStatus.Complete.ordinal());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        edu.ncsu.csc.entity.ServiceHistory serviceHistory = new edu.ncsu.csc.entity.ServiceHistory();
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
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }
}
