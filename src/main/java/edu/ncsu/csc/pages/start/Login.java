package edu.ncsu.csc.pages.start;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.customer.CustomerLanding;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;
import edu.ncsu.csc.repository.CustomerRepository;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;

public class Login extends AbstractPage {

  private CustomerRepository customerRepository;
  private EmployeeRepository employeeRepository;
  private EmploymentRepository employmentRepository;

  Login() {
    customerRepository = new CustomerRepository();
    employeeRepository = new EmployeeRepository();
    employmentRepository = new EmploymentRepository();
    choices.add("Sign-In");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    Long id;
    String password;

    System.out.println("# Login");

    while (true) {
      System.out.print("Enter user ID: ");

      try {
        id = Long.parseLong(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid user ID");
        continue;
      }

      password = getStringFromInput("Enter password: ");

      displayChoices();

      switch (getChoiceFromInput()) {
        case 1:
          signIn(id, password);
        case 2:
          goBack();
      }
    }
  }

  private void signIn(Long id, String password) {
    User user = employeeRepository.getEmployeeById(id);

    if (user == null) {
      user = customerRepository.getCustomerById(id);
      if (user == null) {
        System.out.println("Login incorrect");
        return;
      } else {
        user.setRole(Role.Customer);
      }
    } else {
      Employment employment = employmentRepository.getEmploymentByEmployeeId(id);
      user.setRole(employment.getPosition());
    }

    if (!user.getPassword().equals(password)) {
      System.out.println("Login incorrect");
      return;
    }

    switch (user.getRole()) {
      case Customer:
        Page customer = new CustomerLanding(user);
        customer.run();
        break;
      case Manager:
        Page manager = new ManagerLanding(user);
        manager.run();
        break;
      case Receptionist:
        Page receptionist = new ReceptionistLanding(user);
        receptionist.run();
        break;
      case Mechanic:
        System.out.println("Permission denied");
        break;
    }
  }

  private void goBack() {
    Page home = new Home();
    home.run();
  }
}
