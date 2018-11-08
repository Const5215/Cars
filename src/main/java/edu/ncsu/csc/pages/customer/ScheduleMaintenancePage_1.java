package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleMaintenancePage_1 extends AbstractPage {
  private User customer;

  ScheduleMaintenancePage_1(User customer) {
    this.customer = customer;
    choices.add("Find Service Date");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleMaintenance(Page 1)");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        findServiceDate();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page scheduleService = new ScheduleService(customer);
    scheduleService.run();
  }

  private void findServiceDate() {
    if (serviceDateFound()) {
      Page scheduleMaintenancePage_2 = new ScheduleMaintenancePage_2(customer);
      scheduleMaintenancePage_2.run();
    } else {
      displayErrorMassage();
      Page scheduleService = new ScheduleService(customer);
      scheduleService.run();
    }
  }

  private boolean serviceDateFound() {
    return true;
  }

  private void displayErrorMassage() {

  }
}
