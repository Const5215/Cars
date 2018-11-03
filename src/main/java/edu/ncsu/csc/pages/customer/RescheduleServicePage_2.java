package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class RescheduleServicePage_2 extends AbstractPage {
  private User customer;

  RescheduleServicePage_2(User customer) {
    this.customer = customer;
    choices.add("Reschedule Date");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#rescheduleService(Page 2)");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        rescheduleDate();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page rescheduleServicePage_1 = new RescheduleServicePage_1(customer);
    rescheduleServicePage_1.run();
  }

  private void rescheduleDate() {
    rescheduleExistingService();
    Page service = new Service(customer);
    service.run();
  }

  private void rescheduleExistingService() {

  }
}
