package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.ServiceStatus;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

class Invoices extends AbstractPage {
  private User receptionist;
  private CustomerRepository customerRepository;
  private ServiceHistoryRepository serviceHistoryRepository;
  private ServiceController serviceController;

  Invoices(User receptionist) {
    this.receptionist = receptionist;
    this.customerRepository = new CustomerRepository();
    this.serviceHistoryRepository = new ServiceHistoryRepository();
    this.serviceController = new ServiceController();
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#invoices");

    String email = getEmailFromInput("Enter customer email address: ");
    User customer = customerRepository.getCustomerByEmail(email);
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoriesByCustomerId(customer.getId());
    //filter by status:complete
    for (ServiceHistory serviceHistory : serviceHistoryList) {
      if (serviceHistory.getServiceStatus() != ServiceStatus.Complete)
        serviceHistoryList.remove(serviceHistory);
    }
    printServiceHistoryList(serviceHistoryList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    System.out.printf("Customer has %d service list.\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service #%d details:\n", i);
      serviceController.printServiceHistory(serviceHistory);
    }
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

}
