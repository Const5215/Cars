package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class ViewServiceHistory extends AbstractPage {
  private User customer;

  ViewServiceHistory(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {

    displayChoices();
    getChoiceFromInput();
    Page customerService = new Service(customer);
    customerService.run();
  }
}
