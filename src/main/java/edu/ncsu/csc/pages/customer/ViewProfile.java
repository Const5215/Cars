package edu.ncsu.csc.pages.customer;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;
import edu.ncsu.csc.pages.Page;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewProfile extends AbstractPage {
  private User customer;

  ViewProfile(User customer) {
    this.customer = customer;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#EmployeeViewProfile");
    System.out.println("Customer ID: " + customer.getId());
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Email Address: " + customer.getEmail());
    System.out.println("Phone: " + customer.getPhone());

    List<Car> customerCarList = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * FROM CAR WHERE CUSTOMER_ID=?");
      preparedStatement.setLong(1, customer.getId());
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Car car = getCar();
        customerCarList.add(car);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.printf("You have %d car(s) in total.\n", customerCarList.size());
    displayCarList(customerCarList);
    do {
      displayChoices();
    } while (getChoiceFromInput() != 1);
    Page profile = new Profile(customer);
    profile.run();
  }

}

