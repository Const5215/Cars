package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Center;
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
    System.out.println("# ViewProfile");

    EmploymentRepository employmentRepository = new EmploymentRepository();
    CenterRepository centerRepository = new CenterRepository();
    Employment employment = employmentRepository.getEmploymentByEmployeeId(employee.getId());
    Center center = centerRepository.getCenterById(employment.getCenterId());

    System.out.printf("Employee ID: %d\n", employee.getId());
    System.out.printf("Name: %s\n", employee.getName());
    System.out.printf("Address: %s\n", employee.getAddress());
    System.out.printf("Email: %s\n", employee.getEmail());
    System.out.printf("Phone: %s", employee.getPhone());
    System.out.printf("Service center: %s", center.getName());
    System.out.printf("Role: %s\n", employment.getPosition().toString());
    System.out.printf("Start date: %s\n", dateFormat.format(employment.getStartDate()));
    System.out.printf("Compensation: $%.2f per %s\n", employment.getCompensation(),
        employment.getPosition() == Role.Mechanic ? "hour" : "month");

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page profile = new Profile(employee);
    profile.run();
  }
}

