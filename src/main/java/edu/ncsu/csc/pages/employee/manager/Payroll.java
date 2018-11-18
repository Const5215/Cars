package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.controller.PayrollController;
import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.repository.EmployeeRepository;
import edu.ncsu.csc.repository.EmploymentRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Payroll extends AbstractPage {

  private User manager;

  Payroll(User manager) {
    this.manager = manager;
    choices.add("Go back");
  }

  @Override
  public void run() {
    System.out.println("# Payroll");

    EmployeeRepository employeeRepository = new EmployeeRepository();
    EmploymentRepository employmentRepository = new EmploymentRepository();
    User employee;

    while (true) {
      employee = employeeRepository.getEmployeeById(getEmployeeIdFromInput());
      if (employee == null) {
        System.out.println("Employee does not exist");
      } else {
        break;
      }
    }

    Employment employment = employmentRepository.getEmploymentByEmployeeId(employee.getId());
    List<edu.ncsu.csc.entity.Payroll> payrolls = PayrollController
        .getAllPayrollsByEmployment(employment);
    Date date = new Date();
    Calendar currentCalendar = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    currentCalendar.setTime(date);
    Float yearToDateEarning = 0f;

    System.out.printf("Employee ID: %d", employee.getId());
    System.out.printf("Employee name: %s", employee.getName());
    System.out.printf("Compensation: %f per %s", employment.getCompensation(),
        employment.getPosition() == Role.Mechanic ? "Hourly" : "Monthly");
    System.out.println("Paycheck Date\tFrom Date\tTo Date\tUnit\tEarning\n");

    for (edu.ncsu.csc.entity.Payroll payroll : payrolls) {
      System.out.printf("%s\t%s\t%s\t%f\t%f\n", dateFormat.format(payroll.getPaycheckDate()),
          dateFormat.format(payroll.getFromDate()), dateFormat.format(payroll.getToDate()),
          payroll.getUnit(), payroll.getEarning());

      calendar.setTime(payroll.getPaycheckDate());
      if (calendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
        yearToDateEarning += payroll.getEarning();
      }
    }

    System.out.printf("Year-to-date earning: %f\n", yearToDateEarning);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private void goBack() {
    Page managerLanding = new ManagerLanding(manager);
    managerLanding.run();
  }

  private Long getEmployeeIdFromInput() {
    Long employeeId;

    while (true) {
      System.out.print("Enter employee ID: ");

      try {
        employeeId = Long.parseLong(scanner.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid employee ID");
        continue;
      }

      return employeeId;
    }
  }
}
