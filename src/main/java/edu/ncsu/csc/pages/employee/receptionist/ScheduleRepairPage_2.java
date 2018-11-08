package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleRepairPage_2 extends AbstractPage {
  private User receptionist, customer;

  ScheduleRepairPage_2(User receptionist, User customer) {
    this.receptionist = receptionist;
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
        repairOnDate();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page scheduleRepairPage_1 = new ScheduleRepairPage_1(receptionist, customer);
    scheduleRepairPage_1.run();
  }

  private void repairOnDate() {

    Page scheduleService = new ScheduleService(receptionist);
    scheduleService.run();
  }
}
