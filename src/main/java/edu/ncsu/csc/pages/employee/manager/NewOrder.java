package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class NewOrder extends AbstractPage {
  private User manager;

  NewOrder(User manager) {
    this.manager = manager;
    choices.add("Place Order");
    choices.add("Go Back");
  }

  @Override
  public void run() {

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        placeOrder();
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page orders = new Orders(manager);
    orders.run();
  }

  private void placeOrder() {

  }

}
