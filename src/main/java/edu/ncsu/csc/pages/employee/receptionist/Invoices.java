package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.ServiceHistory;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.ServiceHistoryRepository;

import java.util.List;

class Invoices extends AbstractPage {
  private User receptionist;

  Invoices(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#invoices");

    CustomerRepository customerRepository = new CustomerRepository();
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();

    String email = getInfo("Enter customer email address: ", MatchType.Email);
    User customer = customerRepository.getCustomerByEmail(email);
    List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList =
        serviceHistoryRepository.getServiceHistoryListByCustomerId(customer.getId());

    printServiceHistoryList(serviceHistoryList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<edu.ncsu.csc.entity.ServiceHistory> serviceHistoryList) {
    ServiceHistoryRepository serviceHistoryRepository = new ServiceHistoryRepository();

    System.out.printf("Customer has %d service list.\n", serviceHistoryList.size());
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Service #%d details:\n", i);
      serviceHistoryRepository.printServiceHistory(serviceHistory);
    }
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

}
