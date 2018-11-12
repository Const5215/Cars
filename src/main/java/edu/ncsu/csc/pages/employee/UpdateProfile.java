package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.EmployeeRepository;

public class UpdateProfile extends AbstractPage {

  private User employee;

  UpdateProfile(User employee) {
    this.employee = employee;
    choices.add("Name");
    choices.add("Address");
    choices.add("Email Address");
    choices.add("Phone Number");
    choices.add("Password");
    choices.add("Go back");
  }

    @Override
    public void run() {
      int choice;
      do {
        System.out.println("#Update Profile");
        displayChoices();
        choice = getChoiceFromInput();
        switch (choice) {
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
            Page profileSubmenu = new Profile(employee);
            profileSubmenu.run();
        }
      } while (choice != 6);
    }

  private void updateEmailAddress() {
    String email;
    CustomerRepository customerRepository = new CustomerRepository();
    do {
      email = getInfo("Enter new email address:", MatchType.Email);
    } while (customerRepository.checkUsedEmail(email));
    employee.setEmail(email);
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateTable("EMAIL", employee.getEmail(), employee.getId());
    System.out.println("Email updated.");
  }

  private void updateAddress() {
    System.out.print("Enter new address: ");
    employee.setAddress(scanner.nextLine());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateTable("ADDRESS", employee.getAddress(), employee.getId());
    System.out.println("Address updated.");
  }

  private void updatePhone() {
    employee.setPhone(getInfo("Enter new phone number (e.g. 123-456-7890): ", MatchType.Phone));
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateTable("PHONE", employee.getPhone(), employee.getId());
    System.out.println("Phone updated.");
  }

  private void updateName() {
    System.out.print("Enter new name: ");
    employee.setName(scanner.nextLine());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateTable("NAME", employee.getName(), employee.getId());
    System.out.println("Name updated.");
  }

  private void updatePassword() {
    System.out.print("Enter new password: ");
    employee.setPassword(scanner.nextLine());
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.updateTable("PASSWORD", employee.getPassword(), employee.getId());
    System.out.println("Password updated.");
  }

}
