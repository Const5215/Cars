package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.CenterRepository;
import edu.ncsu.csc.repository.EmploymentRepository;

public class ViewProfile extends AbstractPage {
  private User employee;

  ViewProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#ViewProfile");
    printProfile();
    displayChoices();
    getChoiceFromInput();
    Page profile = new Profile(employee);
    profile.run();
  }

  private void printProfile() {
    System.out.println("Employee ID: " + employee.getId());
    System.out.println("Name: " + employee.getName());
    System.out.println("Address: " + employee.getAddress());
    System.out.println("Email: " + employee.getEmail());
    System.out.println("Phone: " + employee.getPhone());
    EmploymentRepository employmentRepository = new EmploymentRepository();
    Employment employment = employmentRepository.getEmploymentByEmployeeId(employee.getId());
    float compensation = employmentRepository.getCompensationByEmployeeId(employee.getId());
    CenterRepository centerRepository = new CenterRepository();
    System.out.println("Service Center: " + centerRepository.getCenterNameByCenterId(employment.getCenterId()));
    System.out.printf("Role: %s\n", employment.getPosition().toString());
    System.out.printf("Start date: %s\n", employment.getStartDate());
    System.out.println("Compensation: " + compensation);
    System.out.println("Compensation Frequency: " + (employment.getPosition() == Role.Mechanic ? "Hourly" : "Monthly"));
  }

}

