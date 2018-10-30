package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class OrderHistory extends AbstractPage {
  private User manager;

  OrderHistory(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {

    displayChoices();
    getChoiceFromInput();
    Page orders = new Orders(manager);
    orders.run();
  }
}
