package edu.ncsu.csc.pages.employee;

import edu.ncsu.csc.entity.Car;
import edu.ncsu.csc.entity.MatchType;
import edu.ncsu.csc.entity.Role;
import edu.ncsu.csc.entity.User;
import edu.ncsu.csc.pages.AbstractPage;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewCustomerProfile extends AbstractPage {

  private User employee;

  public ViewCustomerProfile(User employee) {
    this.employee = employee;
    choices.add("Go Back");
  }

  @Override
  public void run() {
    System.out.println("#viewCustomerProfile");
    User customer = null;

    String customerEmail = getInfo("Enter customer email:", MatchType.Email);
    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      preparedStatement = connection.prepareStatement("SELECT * from CUSTOMER WHERE EMAIL=?");
      preparedStatement.setString(1, customerEmail);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        customer = getUser();
      } else {
        System.out.println("Email Address Not Found");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (customer != null) {
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
      printCarList(customerCarList);
    }
    displayChoices();
    getChoiceFromInput();
    Profile.appropriateLandingPage(employee);
  }

  private User getUser() {
    User user = null;
    try {
      user = new User(resultSet.getLong("ID"),
          resultSet.getString("PASSWORD"),
          resultSet.getString("NAME"),
          resultSet.getString("EMAIL"),
          resultSet.getString("PHONE"),
          resultSet.getString("ADDRESS"),
          Role.Customer);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return user;
  }

  private Car getCar() {
    Car car = null;
    try {
      car = new Car(resultSet.getString("PLATE"),
          resultSet.getLong("CUSTOMER_ID"),
          resultSet.getLong("CAR_MODEL_ID"),
          resultSet.getDate("PURCHASE_DATE"),
          resultSet.getLong("LAST_MILEAGE"),
          resultSet.getLong("LAST_SERVICE_TYPE"),
          resultSet.getDate("LAST_SERVICE_DATE"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return car;
  }

}
