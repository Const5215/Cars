package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.controller.ServiceController;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

public class ServiceHistory extends AbstractPage {
  private User receptionist;
  private ServiceHistoryRepository serviceHistoryRepository;
  private CustomerRepository customerRepository;
  private ServiceController serviceController;

  ServiceHistory(User receptionist) {
    this.receptionist = receptionist;
    this.serviceHistoryRepository = new ServiceHistoryRepository();
    this.customerRepository = new CustomerRepository();
    this.serviceController = new ServiceController();
    choices.add("Go Back");
  }

  @Override
  public void run() {
    String email = getEmailFromInput("Enter customer email address:");
    User customer = customerRepository.getCustomerByEmail(email);
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoriesByCustomerId(customer.getId());

    printServiceHistory(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistory(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    serviceController.printServiceHistoryListWithoutDetail(serviceHistoryList);
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

}
