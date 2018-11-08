package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class RescheduleServicePage_1 extends AbstractPage {
  private User customer;

  RescheduleServicePage_1(User customer) {
    this.customer = customer;
    choices.add("Pick a service");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#rescheduleService(Page 1)");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        pickAService();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page customerService = new Service(customer);
    customerService.run();
  }

  private void pickAService() {
    selectRescheduleService();
    Page rescheduleServicePage_2 = new RescheduleServicePage_2(customer);
    rescheduleServicePage_2.run();
  }

  private void selectRescheduleService() {

  }
}
