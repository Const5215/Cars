package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleRepairPage_1 extends AbstractPage {
  private User receptionist, customer;

  ScheduleRepairPage_1(User receptionist, User customer) {
    this.receptionist = receptionist;
    this.customer = customer;
    choices.add("Engine knock");
    choices.add("Car drifts in a particular direction");
    choices.add("Battery does not hold charge");
    choices.add("Black/unclean exhaust");
    choices.add("A/CHeater not working");
    choices.add("Head lamps/Tail lamps not working");
    choices.add("Check engine light");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleRepair(Page 1)");

    displayChoices();
    int choice = getChoiceFromInput();
    switch (choice) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        createDiagnosticReport(choice);
        break;
      case 8:
        goBack();
    }
  }

  private void goBack() {
    Page scheduleService = new ScheduleService(receptionist);
    scheduleService.run();
  }

  private void displayErrorMessage() {
  }

  private boolean findRepairDate() {
    return true;
  }

  private void createDiagnosticReport(int choice) {
    if (findRepairDate()) {
      Page scheduleService = new ScheduleRepairPage_2(receptionist, customer);
      scheduleService.run();
    } else {
      displayErrorMessage();
      Page scheduleService = new ScheduleService(receptionist);
      scheduleService.run();
    }
  }
}
