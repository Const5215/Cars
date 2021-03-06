package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;
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
    System.out.println("# Invoice");

    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    List<ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getAllServiceHistoriesByCustomerId(customer.getId());
    //filter by status:complete
    for (ServiceHistory serviceHistory : serviceHistoryList) {
      if (serviceHistory.getServiceStatus() != ServiceStatus.Complete) {
        serviceHistoryList.remove(serviceHistory);
      }
    }
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
    ServiceController serviceController = new ServiceController();
    EmployeeRepository employeeRepository = new EmployeeRepository();

    System.out.printf("You have %d completed service(s) in total.\n\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      String mechanicName = employeeRepository
          .getNameById(serviceHistory.getMechanicId());
      float totalServiceCost = serviceController.getTotalServiceCost(serviceHistory);
      System.out.printf("Details for service #%d:\n", i);
      System.out.println("Service ID: " + serviceHistory.getId());
      System.out.println("Service Start Time: " + timeFormat.format(serviceHistory.getStartTime()));
      System.out.println("Service End Time: " + timeFormat.format(serviceHistory.getEndTime()));
      System.out.println("Licence Plate: " + serviceHistory.getCarPlate());
      System.out.println("Service Type: " + serviceHistory.getServiceType().toString());
      System.out.println("Mechanic Name:" + mechanicName);
      System.out.println("Total Service Cost: " + totalServiceCost);
    }
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
