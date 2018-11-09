package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

class Invoices extends AbstractPage {
  private User receptionist;

  Invoices(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Go Back");
  }
  /*
    --This page is under rework--
  @Override
  public void run() {
    System.out.println("#invoices");
    String email = getInfo("Enter customer email address: ", MatchType.Email);
    List<ServiceHistory> serviceHistoryList = getServiceHistoryListByCustomerId(getCustomerIdByEmail(email));
    printServiceHistoryList(serviceHistoryList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void printServiceHistoryList(List<ServiceHistory> serviceHistoryList) {
    for (int i = 0; i < serviceHistoryList.size(); i++) {
      ServiceHistory serviceHistory = serviceHistoryList.get(i);
      System.out.printf("Customer has %d service list.\n", serviceHistoryList.size());
      System.out.printf("Service #%d details:\n", i);
      printServiceHistory(serviceHistory);
    }
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }
  */
}
