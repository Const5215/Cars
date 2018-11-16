package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Orders extends AbstractPage {

  private User manager;

  Orders(User manager) {
    this.manager = manager;
    choices.add("Order History");
    choices.add("New Order");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        orderHistory();
        break;
      case 2:
        newOrder();
        break;
      case 3:
        goBack();
    }
  }

  private void orderHistory() {
    Page orderHistory = new OrderHistory(manager);
    orderHistory.run();
  }

  private void newOrder() {
    Page newOrder = new NewOrder(manager);
    newOrder.run();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }
}
