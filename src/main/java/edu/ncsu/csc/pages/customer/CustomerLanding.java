package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

public class CustomerLanding extends AbstractPage {

  private User customer;

  public CustomerLanding(User customer) {
    this.customer = customer;
  }
}
