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
    System.out.println("#ViewProfile");
    printProfile();

    List<Car> customerCarList = getCustomerCarList();

    System.out.printf("You have %d car(s) in total.\n", customerCarList.size());
    printCarList(customerCarList);

    displayChoices();
    getChoiceFromInput();
    goBack();
  }

  private List<Car> getCustomerCarList() {
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
    return customerCarList;
  }

  private void printProfile() {
    System.out.println("Customer ID: " + customer.getId());
    System.out.println("Name: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    System.out.println("Email Address: " + customer.getEmail());
    System.out.println("Phone: " + customer.getPhone());
  }

  private void goBack() {
    Page profile = new Profile(customer);
    profile.run();
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

