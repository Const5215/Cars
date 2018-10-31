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
        viewServiceHistory();
        break;
      case 2:
        scheduleService();
        break;
      case 3:
        rescheduleService();
        break;
      case 4:
        goBack();
    }
  }

  private void goBack() {
    Page customerLanding = new CustomerLanding(customer);
    customerLanding.run();
  }

  private void rescheduleService() {
    Page rescheduleService = new RescheduleServicePage_1(customer);
    rescheduleService.run();
  }

  private void scheduleService() {
    Page scheduleService = new ScheduleService(customer);
    scheduleService.run();
  }

  private void viewServiceHistory() {
    Page viewServiceHistory = new ViewServiceHistory(customer);
    viewServiceHistory.run();
  }
}
