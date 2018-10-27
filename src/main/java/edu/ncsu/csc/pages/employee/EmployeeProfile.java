package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;

public class EmployeeProfile extends AbstractPage {

  private User employee;

  public EmployeeProfile(User employee) {
    this.employee = employee;
    choices.add("View CustomerProfile");
    choices.add("Update CustomerProfile");
    choices.add("Go back");
  }

  @Override
  public void run() {
    int choice;
    System.out.println("# CustomerProfile");
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
        switch (employee.getRole()) {
          case Manager:
            Page managerLanding = new ManagerLanding(employee);
            managerLanding.run();
            break;
          case Receptionist:
            Page receptionistLanding = new ReceptionistLanding(employee);
            receptionistLanding.run();
        }
    }
  }

  private void viewProfile() {
    Page viewProfile = new EmployeeViewProfile(employee);
    viewProfile.run();
  }

  private void updateProfile() {
    Page updateProfile = new EmployeeUpdateProfile(employee);
    updateProfile.run();
  }
}
