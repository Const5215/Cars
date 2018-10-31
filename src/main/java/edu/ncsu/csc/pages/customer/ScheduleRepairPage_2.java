package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleRepairPage_2 extends AbstractPage {
  private User customer;

  ScheduleRepairPage_2(User customer) {
    this.customer = customer;
    choices.add("Repair on Date");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleRepair(Page 2)");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        createServiceRecord();
        Page scheduleService = new ScheduleService(customer);
        scheduleService.run();
        break;
      case 2:
        Page scheduleRepairPage_1 = new ScheduleRepairPage_1(customer);
        scheduleRepairPage_1.run();
    }
  }

  private void createServiceRecord() {

  }
}
