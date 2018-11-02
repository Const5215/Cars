package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.ServiceType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ViewInvoiceDetails extends AbstractPage {
  private User customer;

  ViewInvoiceDetails(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewInvoiceDetails");

    System.out.print("Enter Service ID:");
    long serviceId = Long.parseLong(scanner.nextLine());

    ServiceHistory serviceHistory = getServiceHistory(serviceId);
    printServiceHistory(serviceHistory);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private ServiceHistory getServiceHistory(long serviceId) {
    ServiceHistory serviceHistory = new ServiceHistory();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM SERVICE_HISTORY WHERE ID=?");
      preparedStatement.setLong(1, serviceId);
      resultSet = preparedStatement.executeQuery();
      serviceHistory.setId(resultSet.getLong("ID"));
      serviceHistory.setCarPlate(resultSet.getString("CAR_PLATE"));
      serviceHistory.setServiceType(ServiceType.values()[resultSet.getInt("SERVICE_TYPE")]);
      serviceHistory.setStartTime(resultSet.getDate("START_TIME"));
      serviceHistory.setEndTime(resultSet.getDate("END_TIME"));
      serviceHistory.setTotalLaborHour(resultSet.getLong("TOTAL_LABOR_HOUR"));
      serviceHistory.setServiceStatus(ServiceStatus.values()[resultSet.getInt("STATUS")]);
      serviceHistory.setMechanicId(resultSet.getLong("MECHANIC_ID"));
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
    return serviceHistory;
  }

  private void goBack() {
    Page invoice = new Invoice(customer);
    invoice.run();
  }

}
