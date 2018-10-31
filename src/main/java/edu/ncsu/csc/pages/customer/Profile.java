package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

public class Profile extends AbstractPage {

  private User customer;

  Profile(User customer) {
    this.customer = customer;
    choices.add("View Profile");
    choices.add("Update Profile");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("# Profile");
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
    Page viewProfile = new ViewProfile(customer);
    viewProfile.run();
  }

  private void updateProfile() {
    Page updateProfile = new UpdateProfile(customer);
    updateProfile.run();
  }
}
