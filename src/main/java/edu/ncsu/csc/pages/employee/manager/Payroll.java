package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Payroll extends AbstractPage {
  private User manager;

  Payroll(User manager) {
    this.manager = manager;
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("#payroll");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }


}
