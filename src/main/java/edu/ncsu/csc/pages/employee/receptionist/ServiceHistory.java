package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

public class ServiceHistory extends AbstractPage {
  private User receptionist;

  ServiceHistory(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    CustomerRepository customerRepository = new CustomerRepository();

    String email = getInfo("Enter customer email address:", MatchType.Email);
    User customer = customerRepository.getCustomerByEmail(email);
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoryListByCustomerId(customer.getId());

    printServiceHistory(serviceHistoryList);
    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistory(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();
    serviceHistoryRepository.printServiceHistoryListWithoutDetail(serviceHistoryList);
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

}
