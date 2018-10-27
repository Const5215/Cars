package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class CustomerProfile extends AbstractPage {

  private User customer;

  public CustomerProfile(User customer) {
    this.customer = customer;
    choices.add("View CustomerProfile");
    choices.add("Update CustomerProfile");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("# CustomerProfile");
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        viewProfile();
        break;
      case 2:
        updateProfile();
        break;
      case 3:
        Page customerLanding = new CustomerLanding(customer);
        customerLanding.run();
    }
  }

  private void viewProfile() {
    Page viewProfile = new CustomerViewProfile(customer);
    viewProfile.run();
  }

  private void updateProfile() {
    Page updateProfile = new CustomerUpdateProfile(customer);
    updateProfile.run();
  }
}
