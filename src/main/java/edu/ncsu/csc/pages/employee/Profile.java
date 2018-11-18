package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;

public class Profile extends AbstractPage {

  private User employee;

  public Profile(User employee) {
    this.employee = employee;
    choices.add("View Profile");
    choices.add("Update Profile");
    choices.add("Go Back");
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
        goBack();
    }
  }

  private void viewProfile() {
    Page viewProfile = new ViewProfile(employee);
    viewProfile.run();
  }

  private void updateProfile() {
    Page updateProfile = new UpdateProfile(employee);
    updateProfile.run();
  }

  private void goBack() {
    switch (employee.getRole()) {
      case Manager:
        Page managerLanding = new ManagerLanding(employee);
        managerLanding.run();
        break;
      case Receptionist:
        Page receptionistLanding = new ReceptionistLanding(employee);
        receptionistLanding.run();
        break;
    }
  }
}
