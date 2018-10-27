package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;
import edu.ncsu.csc.pages.employee.manager.ManagerLanding;
import edu.ncsu.csc.pages.employee.receptionist.ReceptionistLanding;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeViewCustomerProfile extends AbstractPage {

  private User employee;

  public EmployeeViewCustomerProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewCustomerProfile");
    User customer;
    do {
      String customerEmail;
      do {
        customerEmail = getInfo("Enter customer email:", MatchType.Email);
      } while (checkUsedEmail(customerEmail));
      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        preparedStatement = connection.prepareStatement("SELECT * from CUSTOMER WHERE EMAIL=?");
        preparedStatement.setString(1, customerEmail);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
          customer = getUser();
          break;
        } else {
          System.out.println("Email Address Not Found");
          return;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } while (true);

    List<Car> customerCarList = new ArrayList<>();
    try {
      preparedStatement = connection.prepareStatement("SELECT * from CAR WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customer.getId());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Car car = getCar();
        customerCarList.add(car);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.println("Customer ID: " + customer.getId());
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Email Address: " + customer.getEmail());
    System.out.println("Phone Number: " + customer.getPhone());

    System.out.printf("Customer has %d car(s) in total.\n", customerCarList.size());
    displayCarList(customerCarList);

    do {
      displayChoices();
    } while (getChoiceFromInput() != 1);

    switch (employee.getRole()) {
      case Manager:
        Page managerLanding = new ManagerLanding(employee);
        managerLanding.run();
        break;
      case Receptionist:
        Page receptionistLanding = new ReceptionistLanding(employee);
        receptionistLanding.run();
    }
  }
}
