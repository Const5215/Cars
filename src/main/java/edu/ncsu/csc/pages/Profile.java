package edu.ncsu.csc.pages;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.customer.CustomerLanding;

public class Profile extends AbstractPage {

    private User user;

  public Profile(User user) {
        this.user = user;
        choices.add("View Profile");
        choices.add("Update Profile");
        choices.add("Go back");
    }

    @Override
    public void run() {
        int choice;
        do{
          System.out.println("# Profile");
            displayChoices();
            choice = getChoiceFromInput();
            switch (choice) {
                case 1:
                    viewProfile();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    Page customer = new CustomerLanding(user);
                    customer.run();
            }
        }while(choice != 3);
    }

    private void viewProfile() {
      Page viewProfile = new ViewProfile(user);
      viewProfile.run();
    }

    private void updateProfile() {
        Page updateProfile = new UpdateProfile(user);
        updateProfile.run();
    }
}
