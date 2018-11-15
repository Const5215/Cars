package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;

import java.text.ParseException;

public class AddNewEmployees extends AbstractPage {
  private User manager;
  private EmploymentRepository employmentRepository;

  AddNewEmployees(User manager) {
    this.manager = manager;
    employmentRepository = new EmploymentRepository();
    choices.add("Add");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#addNewEmployees");

    User employee = getEmployee();
    Employment employment = getEmployment(employee);
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        addNewEmployee(employee, employment);
        System.out.println("Employee added.");
      case 2:
        goBack();
    }
  }

  private Employment getEmployment(User employee) {
    Employment employment = new Employment();
    String strStartDate = getInfo("Enter start date(YYYY-MM-DD):", MatchType.Date);
    try {
      employment.setStartDate(dateFormat.parse(strStartDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    employment.setCenterId(
        employmentRepository.getCenterIdByEmployeeId(manager.getId()));
    employment.setPosition(employee.getRole());
    System.out.print("Enter compensation:");
    employment.setCompensation(Float.parseFloat(scanner.nextLine()));
    return employment;
  }

  private User getEmployee() {
    User employee = new User();
    System.out.print("Enter Name:");
    employee.setName(scanner.nextLine());
    System.out.print("Enter Address:");
    employee.setAddress(scanner.nextLine());
    employee.setEmail(getInfo("Enter email Address:", MatchType.Email));
    employee.setPhone(getInfo("Enter phone number:", MatchType.Phone));
    Role role;
    label:
    do {
      System.out.print("Enter role(receptionist/mechanic):");
      String strRole = scanner.nextLine();
      switch (strRole) {
        case "receptionist":
          role = Role.Receptionist;
          break label;
        case "mechanic":
          role = Role.Mechanic;
          break label;
        default:
          System.out.println("Invalid input");
          break;
      }
      if (employmentRepository.oneReceptionistCheck(
          employmentRepository.getCenterIdByEmployeeId(manager.getId())
      )) {
        System.out.println("This service center already have a receptionist.");
      }
    } while (true);
    employee.setRole(role);
    employee.setPassword("12345678");
    return employee;
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private void addNewEmployee(User employee, Employment employment) {
    EmployeeRepository employeeRepository = new EmployeeRepository();
    employeeRepository.addEmployee(employee);
    employee.setId(employeeRepository.getEmployeeIdByEmail(employee.getEmail()));
    employment.setEmployeeId(employee.getId());
    EmploymentRepository employmentRepository = new EmploymentRepository();
    employmentRepository.addEmployment(employment);
  }

}
