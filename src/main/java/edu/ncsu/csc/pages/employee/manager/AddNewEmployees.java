package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;

public class AddNewEmployees extends AbstractPage {

  private EmployeeRepository employeeRepository;
  private EmploymentRepository employmentRepository;
  private User manager;
  private User employee;
  private Employment employment;

  AddNewEmployees(User manager) {
    employeeRepository = new EmployeeRepository();
    employmentRepository = new EmploymentRepository();
    this.manager = manager;
    employee = new User();
    employment = new Employment();
    choices.add("Add");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("# Add New Employees");

    employee.setName(getStringFromInput("Enter name: "));
    employee.setAddress(getStringFromInput("Enter address: "));

    while (true) {
      String email = getEmailFromInput("Enter email address: ");
      if (employeeRepository.getIdByEmail(email) == null) {
        employee.setEmail(email);
        break;
      } else {
        System.out.println("This email address is already used");
      }
    }

    employee.setPhone(getPhoneFromInput("Enter phone number (e.g. 123-456-7890): "));
    employee.setRole(getRoleFromInput());
    employment.setStartDate(getDateFromInput("Enter start date (MM/DD/YYYY): "));
    employment.setCompensation(getCompensationFromInput());
    employment.setCenterId(employmentRepository.getCenterIdByEmployeeId(manager.getId()));

    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        add();
      case 2:
        goBack();
    }
  }

  private void add() {
    employeeRepository.add(employee);
    employment.setEmployeeId(employeeRepository.getIdByEmail(employee.getEmail()));
    employmentRepository.add(employment);
    System.out.printf(
        "Successfully add new employee, employee ID is %d\n", employment.getEmployeeId());
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private Role getRoleFromInput() {
    while (true) {
      System.out.print("Enter role (Receptionist/Mechanic): ");
      String role = scanner.nextLine();

      if (role.equals("Receptionist")) {
        Long centerId = employmentRepository.getCenterIdByEmployeeId(manager.getId());
        if (employmentRepository.getReceptionistByCenterId(centerId) == null) {
          return Role.Receptionist;
        } else {
          System.out.println("A center must have only one receptionist");
        }
      } else if (role.equals("Mechanic")) {
        return Role.Mechanic;
      } else {
        System.out.println("Invalid role");
      }
    }
  }

  private Float getCompensationFromInput() {
    Float compensation;

    while (true) {
      System.out.print("Enter compensation: ");

      try {
        compensation = Float.parseFloat(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid user ID");
        continue;
      }

      break;
    }

    return compensation;
  }
}
