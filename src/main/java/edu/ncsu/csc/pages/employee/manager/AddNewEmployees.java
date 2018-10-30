package edu.ncsu.csc.pages.employee.manager;

import edu.ncsu.csc.entity.Employment;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddNewEmployees extends AbstractPage {
  private User manager;

  AddNewEmployees(User manager) {
    this.manager = manager;
    choices.add("Add");
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#addNewEmployees");

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
      System.out.println("Enter role(receptionist/mechanic):");
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
    } while (true);
    employee.setRole(role);
    employee.setPassword("12345678");
    String strStartDate = getInfo("Enter start date:", MatchType.Date);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Employment employment = new Employment();
    try {
      employment.setStartDate(dateFormat.parse(strStartDate));
    } catch (ParseException e) {
      e.printStackTrace();
      return;
    }
    employment.setEmployeeId(employee.getId());
    employment.setCenterId(manager.getId());
    employment.setPosition(role.ordinal());
    System.out.println("Enter compensation:");
    employment.setCompensation(Float.parseFloat(scanner.nextLine()));
    displayChoices();
    switch (getChoiceFromInput()) {
      case 1:
        addNewEmployee(employee, employment);
      case 2:
        Page managerLanding = new ManagerLanding(manager);
        managerLanding.run();
    }
  }

  private void addNewEmployee(User employee, Employment employment) {
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYEE values (EMPLOYEE_ID_SEQ.nextval, ?, ?, ?, ?, ?)");
      preparedStatement.setString(1, employee.getPassword());
      preparedStatement.setString(2, employee.getName());
      preparedStatement.setString(3, employee.getEmail());
      preparedStatement.setString(4, employee.getPhone());
      preparedStatement.setString(5, employee.getAddress());
      preparedStatement.executeUpdate();
      preparedStatement = connection.prepareStatement("INSERT INTO EMPLOYMENT values (?, ?, ?, ?, ?)");
      preparedStatement.setLong(1, employment.getEmployeeId());
      preparedStatement.setLong(2, employment.getCenterId());
      preparedStatement.setLong(3, employment.getPosition());
      preparedStatement.setFloat(4, employment.getCompensation());
      preparedStatement.setDate(5, new java.sql.Date(employment.getStartDate().getTime()));
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeSqlConnection();
    }
  }
}
