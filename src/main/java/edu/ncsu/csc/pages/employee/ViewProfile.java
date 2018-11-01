package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ViewProfile extends AbstractPage {
  private User employee;

  ViewProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#ViewProfile");
    System.out.println("Employee ID: " + employee.getId());
    System.out.println("Name: " + employee.getName());
    System.out.println("Address: " + employee.getAddress());
    System.out.println("Email: " + employee.getEmail());
    System.out.println("Phone: " + employee.getPhone());

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYMENT WHERE EMPLOYEE_ID=?");
      preparedStatement.setLong(1, employee.getId());
      resultSet = preparedStatement.executeQuery();
      resultSet.next();
      long centerId = resultSet.getLong("CENTER_ID");
      String position = Role.values()[resultSet.getInt("POSITION")] == Role.Mechanic ? "Hourly" : "Monthly";
      Date startDate = resultSet.getDate("START_DATE");
      float compensation = resultSet.getFloat("COMPENSATION");
      preparedStatement = connection.prepareStatement("SELECT * FROM CENTER WHERE ID=?");
      preparedStatement.setLong(1, centerId);
      resultSet = preparedStatement.executeQuery();
      resultSet.next();
      String centerName = resultSet.getString("NAME");
      System.out.println("Service Center: " + centerName);
      System.out.printf("Role: %s\n", employee.getRole());
      System.out.printf("Start date: %s\n", startDate);
      System.out.println("Compensation: " + compensation);
        // with doubt - position refers to what?
      System.out.println("Compensation Frequency: " + position);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    do {
      displayChoices();
    } while (getChoiceFromInput() != 1);
    Page profile = new Profile(employee);
    profile.run();
  }

}

