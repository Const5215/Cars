package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

public class ManagerLanding extends AbstractPage {

  private User customer;

  public ManagerLanding(User customer) {
    this.customer = customer;
  }
}
