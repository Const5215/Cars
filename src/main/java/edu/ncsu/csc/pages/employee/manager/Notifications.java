package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Notifications extends AbstractPage {
  private User manager;
  public Notifications(User manager) {
    this.manager = manager;
    choices.add("Order ID");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#notifications");

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        viewOrderId();
        break;
      case 2:
        goBack();
    }
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private void viewOrderId() {

    Page notificationDetails = new NotificationsDetail(manager);
    notificationDetails.run();
  }
}
