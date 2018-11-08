package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class NotificationsDetail extends AbstractPage {
  private User manager;

  NotificationsDetail(User manager) {
    this.manager = manager;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#notificationsDetail");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page notifications = new Notifications(manager);
    notifications.run();
  }
}
