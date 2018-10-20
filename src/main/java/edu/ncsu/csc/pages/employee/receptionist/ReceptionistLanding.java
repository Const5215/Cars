package edu.ncsu.csc.pages.employee.receptionist;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

public class ReceptionistLanding extends AbstractPage {

  private User customer;

  public ReceptionistLanding(User customer) {
    this.customer = customer;
  }
}
