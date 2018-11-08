package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ServiceHistory extends AbstractPage {
  private User receptionist;

  ServiceHistory(User receptionist) {
    this.receptionist = receptionist;
    choices.add("Go Back");
  }

  @Override
  public void run() {

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page receptionistLanding = new ReceptionistLanding(receptionist);
    receptionistLanding.run();
  }

}
