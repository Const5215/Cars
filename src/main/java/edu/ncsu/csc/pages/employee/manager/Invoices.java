package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Invoices extends AbstractPage {
  private User manager;

  public Invoices(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#invoices");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }
}
