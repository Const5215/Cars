package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleService extends AbstractPage {
  private User customer;

  ScheduleService(User customer) {
    this.customer = customer;
    choices.add("Schedule Maintenance");
    choices.add("Schedule Repair");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleService");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        Page scheduleMaintenancePage_1 = new ScheduleMaintenancePage_1(customer);
        scheduleMaintenancePage_1.run();
        break;
      case 2:
        Page scheduleRepairPage_1 = new ScheduleRepairPage_1(customer);
        scheduleRepairPage_1.run();
        break;
      case 3:
        Page customerService = new Service(customer);
        customerService.run();
    }
  }
}
