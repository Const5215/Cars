package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

class Invoices extends AbstractPage {
  private User manager;

  Invoices(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }
  /*
    --This page is under rework--
  @Override
  public void run() {
    System.out.println("#invoices");
    List<ServiceHistory> serviceHistoryList = getServiceHistoryList(getCenterIdByEmployeeId(manager.getId()));
    printServiceHistoryList(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<ServiceHistory> serviceHistoryList) {
    System.out.printf("Your Center has %d completed service history in total.", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service #%d details:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Customer Name: " + getCustomerNameByCustomerId(
          getCustomerIdByCarPlate(serviceHistory.getCarPlate())));
      System.out.println("Service Start Time: " + serviceHistory.getStartTime());
      System.out.println("Service End Time: " + serviceHistory.getEndTime());
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name: " + getEmployeeNameByEmployeeId(serviceHistory.getMechanicId()));
      List<ServiceHistoryDetail> serviceHistoryDetailList =
          getServiceHistoryDetailListByServiceHistoryId(serviceHistory.getId());
      float totalServiceCost = countPartList(serviceHistoryDetailList);
      System.out.println("Total labor hours: " + serviceHistory.getTotalLaborHour());
      float laborWagePerHour = getMechanicHourlyWageByEmployeeId(serviceHistory.getMechanicId());
      System.out.println("Labor wages per hour: " +
          laborWagePerHour);
      totalServiceCost += serviceHistory.getTotalLaborHour() * laborWagePerHour;
      System.out.println("Total Service Cost: " + totalServiceCost);
    }
  }

  private List<ServiceHistory> getServiceHistoryList(long centerId) {
    List<ServiceHistory> serviceHistoryList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      connection.prepareStatement(
          "SELECT * FROM EMPLOYMENT, SERVICE_HISTORY WHERE CENTER_ID=? AND EMPLOYEE_ID=MECHANIC_ID AND STATUS=?");
      preparedStatement.setLong(1, centerId);
      preparedStatement.setLong(2, ServiceStatus.Complete.ordinal());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setId(resultSet.getLong("ID"));
        serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
        serviceHistory.setStartTime(resultSet.getDate("START_TIME"));
        serviceHistory.setEndTime(resultSet.getDate("END_TIME"));
        serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
        serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("SERVICE_TYPE")]);
        serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
        serviceHistory.setTotalLaborHour(resultSet.getLong("TOTAL_LABOR_HOUR"));
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
  */
}
