package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ScheduleService extends AbstractPage {
  private User receptionist, customer;

  ScheduleService(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Schedule Maintenance");
    choices.add("Schedule Repair");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#scheduleService");
    switch (getChoiceFromInput()) {
      case 1:
        scheduleMaintenance();
        break;
      case 2:
        scheduleRepair();
        break;
      case 3:
        goBack();
    }
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

  private void scheduleRepair() {
    Page scheduleRepair = new ScheduleRepairPage_1(receptionist, customer);
    scheduleRepair.run();
  }

  private void scheduleMaintenance() {
    Page scheduleMaintenance = new ScheduleMaintenancePage_1(receptionist, customer);
    scheduleMaintenance.run();
  }
}
