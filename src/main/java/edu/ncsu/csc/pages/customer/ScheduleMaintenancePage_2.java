package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleMaintenancePage_2 extends AbstractPage {
  private User customer;

  ScheduleMaintenancePage_2(User customer) {
    this.customer = customer;
    choices.add("Schedule on Date");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleMaintenance(Page 2)");


    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        scheduleOnDate();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page scheduleMaintenancePage_1 = new ScheduleMaintenancePage_1(customer);
    scheduleMaintenancePage_1.run();
  }

  private void scheduleOnDate() {

    Page scheduleService = new ScheduleService(customer);
    scheduleService.run();
  }
}
