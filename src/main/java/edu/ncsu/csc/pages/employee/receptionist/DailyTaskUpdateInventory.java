package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class DailyTaskUpdateInventory extends AbstractPage {
  private User receptionist;
  public DailyTaskUpdateInventory(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#dailyTaskUpdateInventory");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }
}
