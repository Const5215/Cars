package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;

public class UpdateProfile extends AbstractPage {

  private EmployeeRepository employeeRepository;
  private User employee;

  UpdateProfile(User employee) {
    employeeRepository = new EmployeeRepository();
    this.employee = employee;
    choices.add("Name");
    choices.add("Address");
    choices.add("Email address");
    choices.add("Phone number");
    choices.add("Password");
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("# Update Profile");

    while (true) {
      displayChoices();
      Integer choice = getChoiceFromInput();
      switch (getChoiceFromInput()) {
        case 1:
          updateName();
          break;
        case 2:
          updateAddress();
          break;
        case 3:
          updateEmailAddress();
          break;
        case 4:
          updatePhone();
          break;
        case 5:
          updatePassword();
          break;
        case 6:
          goBack();
          break;
      }
      if (choice == 5) break;
    }
  }


  private void updateName() {
    employee.setName(getStringFromInput("Enter new name: "));
  }

  private void updateAddress() {
    employee.setAddress(getStringFromInput("Enter new address: "));
  }

  private void updateEmailAddress() {
    employee.setEmail(getEmailFromInput("Enter new email address: "));
  }

  private void updatePhone() {
    employee.setPhone(getPhoneFromInput("Enter new phone: "));
  }

  private void updatePassword() {
    employee.setPassword(getStringFromInput("Enter new password: "));
  }

  private void goBack() {
    Page profile = new Profile(employee);
    employeeRepository.update(employee);
    profile.run();
  }
}
