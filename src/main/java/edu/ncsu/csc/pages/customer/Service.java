package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Service extends AbstractPage {
  private User customer;

  Service(User customer) {
    this.customer = customer;
    choices.add("View Service History");
    choices.add("Schedule Service");
    choices.add("Reschedule Service");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#customerService");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        Page viewServiceHistory = new ViewServiceHistory(customer);
        viewServiceHistory.run();
        break;
      case 2:
        Page scheduleService = new ScheduleService(customer);
        scheduleService.run();
        break;
      case 3:
        Page rescheduleService = new RescheduleServicePage_1(customer);
        rescheduleService.run();
        break;
      case 4:
        Page customerLanding = new CustomerLanding(customer);
        customerLanding.run();
    }
  }
}
